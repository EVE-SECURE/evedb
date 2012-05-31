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
import lv.odylab.evedb.domain.BlueprintDetails;
import lv.odylab.evedb.domain.BlueprintDetailsDao;
import lv.odylab.evedb.domain.InvBlueprintType;
import lv.odylab.evedb.service.BlueprintDetailsCalculationService;
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

public class CalculateBlueprintDetailsServlet extends PicoServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private BlueprintDetailsCalculationService blueprintDetailsCalculationService;

    @Override
    public void init() throws ServletException {
        logger.info("Initializing servlet: {}", getClass().getSimpleName());
        getComponent(BlueprintDetailsDao.class); // TODO this is needed for ObjectifyService.register
        blueprintDetailsCalculationService = getComponent(BlueprintDetailsCalculationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Objectify objectify = ObjectifyService.begin();
        Query<InvBlueprintType> query = objectify.query(InvBlueprintType.class).filter("dumpVersion", getDumpVersion());
        String bookmark = req.getParameter("bookmark");
        if (bookmark != null) {
            logger.info("Proceeding query from bookmark: {}", bookmark);
            query.startCursor(Cursor.fromWebSafeString(bookmark));
        }

        QueryResultIterable<Key<InvBlueprintType>> queryResult = query.fetchKeys();
        QueryResultIterator<Key<InvBlueprintType>> iterator = queryResult.iterator();
        List<Key<InvBlueprintType>> keysToFetch = new ArrayList<Key<InvBlueprintType>>();
        while (iterator.hasNext()) {
            keysToFetch.add(iterator.next());

            if (keysToFetch.size() > 19) {
                Map<Key<InvBlueprintType>, InvBlueprintType> map = objectify.get(keysToFetch);
                List<BlueprintDetails> blueprintDetailsList = new ArrayList<BlueprintDetails>();
                for (InvBlueprintType invBlueprintType : map.values()) {
                    blueprintDetailsList.add(blueprintDetailsCalculationService.getBlueprintDetailsForTypeID(invBlueprintType.getBlueprintTypeID()));
                }
                objectify.put(blueprintDetailsList);

                Cursor cursor = iterator.getCursor();
                Queue queue = QueueFactory.getDefaultQueue();
                String newBookmark = cursor.toWebSafeString();
                queue.add(withUrl("/admin/calculateBlueprintDetails").method(TaskOptions.Method.POST)
                        .param("bookmark", newBookmark));
                logger.info("20 objects processed, created new task with url: bookmark: {}", newBookmark);
                resp.getWriter().write("IN PROGRESS");
                return;
            }
        }

        Map<Key<InvBlueprintType>, InvBlueprintType> map = objectify.get(keysToFetch);
        logger.info("Almost done, only {} objects left", map.size());
        List<BlueprintDetails> blueprintDetailsList = new ArrayList<BlueprintDetails>();
        for (InvBlueprintType invBlueprintType : map.values()) {
            blueprintDetailsList.add(blueprintDetailsCalculationService.getBlueprintDetailsForTypeID(invBlueprintType.getBlueprintTypeID()));
        }
        objectify.put(blueprintDetailsList);
        logger.info("Done.");
        resp.getWriter().write("DONE");
    }
}
