package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class JsonDummyResource extends EveDbResource implements ProvidesJson {

    @Override
    public void provideJson(String pathInfo, PrintWriter writer) {
        writer.write("json-" + pathInfo);
    }
}
