package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class BlueprintTypeByTypeNameResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = 2376670976854157316L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public BlueprintTypeByTypeNameResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String typeName, PrintWriter writer) {
        writer.write(wsFacade.getBlueprintTypeByTypeNameAsJson(typeName));
    }

    @Override
    public void provideXml(String typeName, PrintWriter writer) {
        writer.write(wsFacade.getBlueprintTypeByTypeNameAsXml(typeName));
    }
}