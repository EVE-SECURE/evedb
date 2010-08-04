package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class XmlDummyResource extends EveDbResource implements ProvidesXml {

    @Override
    public void provideXml(String pathInfo, PrintWriter writer) {
        writer.write("xml-" + pathInfo);
    }
}