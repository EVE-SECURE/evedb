package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.*;
import lv.odylab.evedb.service.BlueprintDetailsCalculationService;
import lv.odylab.evedb.service.DtoMapper;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import javax.servlet.http.HttpServlet;

public abstract class PicoServlet extends HttpServlet {
    private static final String DUMP_VERSION = "inf10";

    private static final MutablePicoContainer picoContainer;

    static {
        picoContainer = new DefaultPicoContainer()
                .addConfig("dumpVersion", DUMP_VERSION)
                .addConfig("lookupResultLimit", 50)
                .addComponent(DtoMapper.class)
                .addComponent(InvBlueprintTypeDao.class)
                .addComponent(InvTypeDao.class)
                .addComponent(InvTypeMaterialDao.class)
                .addComponent(RamTypeRequirementDao.class)
                .addComponent(BlueprintDetailsDao.class)
                .addComponent(PlanetSchematicDao.class)
                .addComponent(BlueprintDetailsCalculationService.class);
    }

    protected <T> T getComponent(Class<T> componentClass) {
        return picoContainer.getComponent(componentClass);
    }

    protected String getDumpVersion() {
        return DUMP_VERSION;
    }
}
