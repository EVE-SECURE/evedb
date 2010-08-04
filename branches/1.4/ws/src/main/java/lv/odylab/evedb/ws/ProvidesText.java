package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public interface ProvidesText {

    void provideText(String pathInfo, PrintWriter writer);

}