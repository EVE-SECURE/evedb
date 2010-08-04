package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public interface ProvidesXml {

    void provideXml(String pathInfo, PrintWriter writer);

}