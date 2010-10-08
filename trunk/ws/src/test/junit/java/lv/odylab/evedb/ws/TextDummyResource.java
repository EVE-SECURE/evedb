package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class TextDummyResource extends EveDbResource implements ProvidesText {
    private static final long serialVersionUID = 5400319395134546057L;

    @Override
    public void provideText(String pathInfo, PrintWriter writer) {
        writer.write("text-" + pathInfo);
    }
}