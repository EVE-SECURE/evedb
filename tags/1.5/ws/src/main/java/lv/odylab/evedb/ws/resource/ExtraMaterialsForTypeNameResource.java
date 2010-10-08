package lv.odylab.evedb.ws.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.ws.EveDbResource;
import lv.odylab.evedb.ws.EveDbWsFacade;
import lv.odylab.evedb.ws.ProvidesJson;
import lv.odylab.evedb.ws.ProvidesXml;

import java.io.PrintWriter;

@Singleton
public class ExtraMaterialsForTypeNameResource extends EveDbResource implements ProvidesJson, ProvidesXml {
    private static final long serialVersionUID = -7005957430996773568L;

    private final EveDbWsFacade wsFacade;

    @Inject
    public ExtraMaterialsForTypeNameResource(EveDbWsFacade wsFacade) {
        this.wsFacade = wsFacade;
    }

    @Override
    public void provideJson(String typeName, PrintWriter writer) {
        writer.write(wsFacade.getRamTypeRequirementsForTypeNameAsJson(typeName));
    }

    @Override
    public void provideXml(String typeName, PrintWriter writer) {
        writer.write(wsFacade.getRamTypeRequirementsForTypeNameAsXml(typeName));
    }
}