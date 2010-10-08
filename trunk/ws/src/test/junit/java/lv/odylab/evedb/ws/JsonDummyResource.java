package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class JsonDummyResource extends EveDbResource implements ProvidesJson {
    private static final long serialVersionUID = -4972647192988561159L;

    @Override
    public void provideJson(String pathInfo, PrintWriter writer) {
        writer.write("json-" + pathInfo);
    }
}
