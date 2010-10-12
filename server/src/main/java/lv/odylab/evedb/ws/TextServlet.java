package lv.odylab.evedb.ws;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class TextServlet extends EveDbServlet {

    @Override
    protected void writeResponse(String pathInfo, String acceptHeader, HttpServletResponse resp) throws IOException, JAXBException {
        resp.setContentType("text/plain");
        writeText(provideResponse(pathInfo), resp.getWriter());
    }

    protected void writeText(Object text, PrintWriter writer) {
        writer.write(String.valueOf(text));
    }
}
