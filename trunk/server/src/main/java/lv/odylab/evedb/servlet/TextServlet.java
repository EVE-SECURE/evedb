package lv.odylab.evedb.servlet;

import com.google.appengine.api.memcache.MemcacheService;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public abstract class TextServlet extends EveDbServlet {

    @Override
    protected void writeResponse(String pathInfo, String acceptHeader, HttpServletResponse resp) throws IOException, JAXBException {
        MemcacheService memcacheService = getMemcacheService();
        String key = new StringBuilder(getClass().getSimpleName()).append("|").append(pathInfo).append("|").append(DUMP_VERSION).append("|text").toString();
        String result = (String) memcacheService.get(key);
        if (result == null) {
            logger.info("Key was not found in cache: {}, the result will be cached", key);
            result = provideResponse(pathInfo);
            memcacheService.put(key, result);
        } else {
            logger.info("Key was found in cache: {}", key);
        }
        resp.setContentType("text/plain");
        resp.getWriter().write(result);
    }

    protected abstract String provideResponse(String pathInfo);

}
