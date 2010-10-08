package lv.odylab.evedb.ws;

import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;
import lv.odylab.evedb.domain.TooShortPartialNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class EveDbResource extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acceptHeader = req.getHeader("accept");
        String pathInfo = req.getPathInfo() == null ? null : req.getPathInfo().substring(1);
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        try {
            if (ProvidesJson.class.isAssignableFrom(getClass()) && acceptHeader != null && acceptHeader.contains("application/json")) {
                resp.setContentType("application/json");
                ((ProvidesJson) this).provideJson(pathInfo, writer);
            } else if (ProvidesXml.class.isAssignableFrom(getClass()) && acceptHeader != null && (acceptHeader.contains("text/plain") || acceptHeader.contains("text/html") || acceptHeader.contains("application/xml"))) {
                resp.setContentType("application/xml");
                ((ProvidesXml) this).provideXml(pathInfo, writer);
            } else if (ProvidesText.class.isAssignableFrom(getClass()) && acceptHeader != null && (acceptHeader.contains("text/plain") || acceptHeader.contains("text/html"))) {
                resp.setContentType("text/plain");
                ((ProvidesText) this).provideText(pathInfo, writer);
            } else {
                logger.warn("Unable to find appropriate resource for accept header: {}", acceptHeader);
                resp.sendError(406);
            }
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            resp.sendError(400, e.getMessage());
        } catch (IdNotFoundException e) {
            logger.warn(e.getMessage());
            resp.sendError(400, e.getMessage());
        } catch (NameNotFoundException e) {
            logger.warn(e.getMessage());
            resp.sendError(400, e.getMessage());
        } catch (TooShortPartialNameException e) {
            logger.warn(e.getMessage());
            resp.sendError(400, e.getMessage());
        } catch (Exception e) {
            logger.error("Internal error", e);
            resp.sendError(500);
        }
    }
}
