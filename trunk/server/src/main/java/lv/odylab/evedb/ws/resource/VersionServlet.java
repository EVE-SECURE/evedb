package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.ws.TextServlet;

public class VersionServlet extends TextServlet {

    @Override
    protected Object provideResponse(String pathInfo) {
        return "1.6-SNAPSHOT-tyr104";
    }
}