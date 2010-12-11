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
import lv.odylab.evedb.domain.InvType;
import lv.odylab.evedb.domain.InvTypeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

public class TokenizeInvTypeServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // TODO this is needed for ObjectifyService.register
    private final InvTypeDao invTypeDao = new InvTypeDao();

    @Override
    public void init() throws ServletException {
        logger.info("Initializing servlet: {}", getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dumpVersion = req.getParameter("dumpVersion");
        String bookmark = req.getParameter("bookmark");
        logger.info("Received following parameters: dumpVersion={}, bookmark={}", dumpVersion, bookmark);
        if (dumpVersion == null) {
            resp.sendError(400, "dumpVersion must be present");
            return;
        }

        Objectify objectify = ObjectifyService.begin();
        Query<InvType> query = objectify.query(InvType.class).filter("dumpVersion", dumpVersion);
        if (bookmark != null) {
            logger.info("Proceeding query from bookmark: {}", bookmark);
            query.startCursor(Cursor.fromWebSafeString(bookmark));
        }

        QueryResultIterable<Key<InvType>> queryResult = query.fetchKeys();
        QueryResultIterator<Key<InvType>> iterator = queryResult.iterator();
        List<Key<InvType>> keysToFetch = new ArrayList<Key<InvType>>();
        while (iterator.hasNext()) {
            keysToFetch.add(iterator.next());

            if (keysToFetch.size() > 49) {
                Map<Key<InvType>, InvType> map = objectify.get(keysToFetch);
                for (InvType invType : map.values()) {
                    populateTokens(invType);
                }
                objectify.put(map.values());

                Cursor cursor = iterator.getCursor();
                Queue queue = QueueFactory.getDefaultQueue();
                String newBookmark = cursor.toWebSafeString();
                queue.add(withUrl("/admin/tokenizeInvType").method(TaskOptions.Method.POST)
                        .param("dumpVersion", dumpVersion)
                        .param("bookmark", newBookmark));
                logger.info("50 objects processed, created new task with url: dumpVersion={}, bookmark: {}", new Object[]{dumpVersion, newBookmark});
                resp.getWriter().write("IN PROGRESS");
                return;
            }
        }

        Map<Key<InvType>, InvType> map = objectify.get(keysToFetch);
        logger.info("Almost done, only {} objects left", map.size());
        objectify.put(map.values());
        logger.info("Done.");
        resp.getWriter().write("DONE");
    }

    private void populateTokens(InvType invType) {
        String typeName = invType.getTypeName();
        if (typeName == null) {
            logger.warn("typeName is null for InvType with id: {}", invType.getId());
            return;
        }
        String[] words = typeName.toUpperCase().split(" ");
        List<String> typeNameTokens = new ArrayList<String>();
        for (String word : words) {
            if (word.length() > 0) {
                typeNameTokens.add(word);
            }
        }
        invType.setTypeNameTokens(typeNameTokens);
    }
}