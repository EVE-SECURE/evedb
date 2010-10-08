package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class XmlDummyResource extends EveDbResource implements ProvidesXml {
    private static final long serialVersionUID = -6186469133177173786L;

    @Override
    public void provideXml(String pathInfo, PrintWriter writer) {
        writer.write("xml-" + pathInfo);
    }
}