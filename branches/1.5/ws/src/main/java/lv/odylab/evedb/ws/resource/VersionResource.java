package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesText;

import java.io.PrintWriter;

@Singleton
public class VersionResource extends EveDbResource implements ProvidesText {
    private static final long serialVersionUID = 6592606695797519969L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public VersionResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideText(String pathInfo, PrintWriter writer) {
        writer.write(wsFacade.getVersion());
    }
}