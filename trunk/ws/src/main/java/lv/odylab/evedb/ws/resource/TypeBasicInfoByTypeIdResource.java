package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class TypeBasicInfoByTypeIdResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = 1485432693094694188L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public TypeBasicInfoByTypeIdResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getTypeBasicInfoByTypeIdAsJson(Long.valueOf(typeID)));
    }

    @Override
    public void provideXml(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getTypeBasicInfoByTypeIdAsXml(Long.valueOf(typeID)));
    }
}
