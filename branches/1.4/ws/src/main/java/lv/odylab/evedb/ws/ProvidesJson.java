package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public interface ProvidesJson {

    void provideJson(String pathInfo, PrintWriter writer);

}
