package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class JsonXmlDummyResource extends EveDbResource implements ProvidesJson, ProvidesXml {

    @Override
    public void provideJson(String pathInfo, PrintWriter writer) {
        writer.write("json-" + pathInfo);
    }

    @Override
    public void provideXml(String pathInfo, PrintWriter writer) {
        writer.write("xml-" + pathInfo);
    }
}