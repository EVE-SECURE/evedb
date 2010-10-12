package lv.odylab.evedb.servlet;

public class VersionServlet extends TextServlet {

    @Override
    protected Object provideResponse(String pathInfo) {
        return "1.6-SNAPSHOT-tyr104";
    }
}