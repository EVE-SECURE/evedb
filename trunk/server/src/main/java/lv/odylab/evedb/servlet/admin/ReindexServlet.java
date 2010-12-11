package lv.odylab.evedb.servlet.admin;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.domain.PlanetSchematicDao;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.servlet.PicoServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

public class ReindexServlet extends PicoServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init() throws ServletException {
        logger.info("Initializing servlet: {}", getClass().getSimpleName());

        // TODO this is needed for ObjectifyService.register
        getComponent(InvBlueprintTypeDao.class);
        getComponent(InvTypeDao.class);
        getComponent(InvTypeMaterialDao.class);
        getComponent(RamTypeRequirementDao.class);
        getComponent(PlanetSchematicDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entityClass = req.getParameter("entityClass");
        String dumpVersion = req.getParameter("dumpVersion");
        String bookmark = req.getParameter("bookmark");
        logger.info("Received following parameters: entityClass={}, dumpVersion={}, bookmark={}", new Object[]{entityClass, dumpVersion, bookmark});
        if (entityClass == null || dumpVersion == null) {
            resp.sendError(400, "entityClass and dumpVersion must be present");
            return;
        }

        Class<?> clazz;
        try {
            clazz = Class.forName(entityClass);
        } catch (ClassNotFoundException e) {
            resp.sendError(400, "entityClass could not be found");
            return;
        }

        Objectify objectify = ObjectifyService.begin();
        Query query = objectify.query(clazz).filter("dumpVersion", dumpVersion);
        if (bookmark != null) {
            logger.info("Proceeding query from bookmark: {}", bookmark);
            query.startCursor(Cursor.fromWebSafeString(bookmark));
        }

        QueryResultIterable queryResult = query.fetchKeys();
        QueryResultIterator iterator = queryResult.iterator();
        List<Key<?>> keysToFetch = new ArrayList<Key<?>>();
        while (iterator.hasNext()) {
            keysToFetch.add((Key) iterator.next());

            if (keysToFetch.size() > 999) {
                Map<Key<Object>, Object> map = objectify.get(keysToFetch);
                objectify.put(map.values());

                Cursor cursor = iterator.getCursor();
                Queue queue = QueueFactory.getDefaultQueue();
                String newBookmark = cursor.toWebSafeString();
                queue.add(withUrl("/admin/reindex").method(TaskOptions.Method.POST)
                        .param("entityClass", entityClass)
                        .param("dumpVersion", dumpVersion)
                        .param("bookmark", newBookmark));
                logger.info("100 objects processed, created new task with url: entityClass={}, dumpVersion={}, bookmark: {}", new Object[]{entityClass, dumpVersion, newBookmark});
                resp.getWriter().write("IN PROGRESS");
                return;
            }
        }

        Map<Key<Object>, Object> map = objectify.get(keysToFetch);
        logger.info("Almost done, only {} objects left", map.size());
        objectify.put(map.values());
        logger.info("Done.");
        resp.getWriter().write("DONE");
    }
}
