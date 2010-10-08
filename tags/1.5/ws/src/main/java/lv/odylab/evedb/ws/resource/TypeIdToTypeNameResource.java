package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesText;

import java.io.PrintWriter;

@Singleton
public class TypeIdToTypeNameResource extends EveDbResource implements ProvidesText {
    private static final long serialVersionUID = -1314533109389644939L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public TypeIdToTypeNameResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideText(String typeID, PrintWriter writer) {
        writer.write(wsFacade.getTypeNameByTypeID(Long.valueOf(typeID)));
    }
}
