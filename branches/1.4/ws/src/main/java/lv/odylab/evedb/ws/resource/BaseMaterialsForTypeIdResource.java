package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class BaseMaterialsForTypeIdResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = 1203982236125644984L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public BaseMaterialsForTypeIdResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getInvTypeMaterialsForTypeIdAsJson(Long.parseLong(typeID)));
    }

    @Override
    public void provideXml(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getInvTypeMaterialsForTypeIdAsXml(Long.parseLong(typeID)));
    }
}
