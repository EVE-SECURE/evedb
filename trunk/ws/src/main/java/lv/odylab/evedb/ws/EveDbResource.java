package lv.odylab.evedb.ws;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public abstract class EveDbResource extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> acceptHeaders = Arrays.asList(req.getHeader("accept").split(","));
        String pathInfo = req.getPathInfo() == null ? null : req.getPathInfo().substring(1);
        PrintWriter writer = resp.getWriter();

        if (ProvidesJson.class.isAssignableFrom(getClass()) && acceptHeaders.contains("application/json")) {
            resp.setContentType("application/json; charset=UTF-8");
            ((ProvidesJson) this).provideJson(pathInfo, writer);
        } else if (ProvidesXml.class.isAssignableFrom(getClass()) && (acceptHeaders.contains("text/html") || acceptHeaders.contains("application/xml"))) {
            resp.setContentType("application/xml; charset=UTF-8");
            ((ProvidesXml) this).provideXml(pathInfo, writer);
        } else if (ProvidesText.class.isAssignableFrom(getClass())) {
            resp.setContentType("text/plain; charset=UTF-8");
            ((ProvidesText) this).provideText(pathInfo, writer);
        } else {
            resp.sendError(406);
        }
    }
}
