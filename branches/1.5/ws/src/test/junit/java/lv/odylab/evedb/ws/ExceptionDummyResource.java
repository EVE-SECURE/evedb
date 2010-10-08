package lv.odylab.evedb.ws;

import java.io.PrintWriter;

public class ExceptionDummyResource extends EveDbResource implements ProvidesText {
    private static final long serialVersionUID = -6926641972674920367L;

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