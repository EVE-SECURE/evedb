package lv.odylab.evedb.ws;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EveDbResource extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acceptHeader = req.getHeader("accept");
        List<String> acceptHeaders = new ArrayList<String>();
        if (acceptHeader != null) {
            acceptHeaders.addAll(Arrays.asList(acceptHeader.split(",")));
        }
        String pathInfo = req.getPathInfo() == null ? null : req.getPathInfo().substring(1);
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        if (ProvidesJson.class.isAssignableFrom(getClass()) && acceptHeaders.contains("application/json")) {
            resp.setContentType("application/json");
            ((ProvidesJson) this).provideJson(pathInfo, writer);
        } else if (ProvidesXml.class.isAssignableFrom(getClass()) && (acceptHeaders.contains("text/plain") || acceptHeaders.contains("text/html") || acceptHeaders.contains("application/xml"))) {
            resp.setContentType("application/xml");
            ((ProvidesXml) this).provideXml(pathInfo, writer);
        } else if (ProvidesText.class.isAssignableFrom(getClass()) && (acceptHeaders.contains("text/plain") || acceptHeaders.contains("text/html"))) {
            resp.setContentType("text/plain");
            ((ProvidesText) this).provideText(pathInfo, writer);
        } else {
            resp.sendError(406);
        }
    }
}
