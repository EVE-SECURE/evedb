package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.servlet.TextServlet;

public class VersionServlet extends TextServlet {

    @Override
    protected Object provideResponse(String pathInfo) {
        return "1.6-SNAPSHOT-tyr104";
    }
}