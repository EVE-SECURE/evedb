package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class BlueprintDetailsForTypeNameResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = -2125545610362246335L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public BlueprintDetailsForTypeNameResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getBlueprintDetailsForTypeNameAsJson(typeID));
    }

    @Override
    public void provideXml(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getBlueprintDetailsForTypeNameAsXml(typeID));
    }
}