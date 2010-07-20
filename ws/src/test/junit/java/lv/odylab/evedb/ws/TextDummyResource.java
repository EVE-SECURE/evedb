package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class TextDummyResource extends EveDbResource implements ProvidesText {

    @Override
    public void provideText(String pathInfo, PrintWriter writer) {
        writer.write("text-" + pathInfo);
    }
}