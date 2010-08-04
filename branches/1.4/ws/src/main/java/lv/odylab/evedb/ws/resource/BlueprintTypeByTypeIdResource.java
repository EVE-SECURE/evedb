package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class BlueprintTypeByTypeIdResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = 1433506895499151080L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public BlueprintTypeByTypeIdResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getBlueprintTypeByTypeIdAsJson(Long.valueOf(typeID)));
    }

    @Override
    public void provideXml(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getBlueprintTypeByTypeIdAsXml(Long.valueOf(typeID)));
    }
}
