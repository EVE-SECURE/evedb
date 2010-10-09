package lv.odylab.evedb.ws.resource.admin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesText;

import java.io.PrintWriter;

@Singleton
public class ClearCacheResource extends EveDbResource implements ProvidesText {
    private static final long serialVersionUID = -1787940503425086639L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public ClearCacheResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideText(String pathInfo, PrintWriter writer) {
        writer.write(wsFacade.clearCache());
    }
}
