package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class ExceptionDummyResource extends EveDbResource implements ProvidesText {

    @Override
    public void provideText(String pathInfo, PrintWriter writer) {
        try {
            throw (RuntimeException) Class.forName(pathInfo).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}