package lv.odylab.evedb.ws.resource.admin;

import com.google.appengine.api.memcache.Stats;
import lv.odylab.evedb.ws.TextServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearCacheServlet extends TextServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object provideResponse(String pathInfo) {
        Stats stats = getMemcacheService().getStatistics();
        long itemCount = stats.getItemCount();
        logger.info("Clearing memcache, item count in cache: {}", itemCount);
        getMemcacheService().clearAll();
        return "OK, " + itemCount;
    }
}
