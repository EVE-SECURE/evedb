package lv.odylab.evedb.ws.resource.admin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbWsFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ClearCacheResource extends HttpServlet {
    private static final long serialVersionUID = -6261560710862141980L;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EveDbWsFacade eveDbWsFacade;

    @Inject
    public ClearCacheResource(EveDbWsFacade eveDbWsFacade) {
        this.eveDbWsFacade = eveDbWsFacade;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().write(eveDbWsFacade.clearCache());
        } catch (Exception e) {
            logger.error("Application threw exception", e);
            resp.sendError(500);
        }
    }
}
