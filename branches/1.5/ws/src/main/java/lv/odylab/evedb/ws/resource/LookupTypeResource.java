package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class LookupTypeResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = 6281077530952825694L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public LookupTypeResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String query, PrintWriter writer) {
        writer.write(wsFacade.findTypeByPartialTypeNameAsJson(query));
    }

    @Override
    public void provideXml(String query, PrintWriter writer) {
        writer.write(wsFacade.findTypeByPartialTypeNameAsXml(query));
    }
}
