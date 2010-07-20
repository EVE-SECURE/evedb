package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesText;

import java.io.PrintWriter;

@Singleton
public class TypeNameToTypeIdResource extends EveDbResource implements ProvidesText {
    private static final long serialVersionUID = 1892856354264537505L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public TypeNameToTypeIdResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideText(String typeName, PrintWriter writer) {
        writer.write(wsFacade.getTypeIdByTypeName(typeName));
    }
}