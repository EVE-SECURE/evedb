package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.BlueprintDetailsDao;
import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.domain.PlanetSchematicDao;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.service.BlueprintDetailsCalculationService;
import lv.odylab.evedb.service.DtoMapper;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import javax.servlet.http.HttpServlet;

public abstract class PicoServlet extends HttpServlet {
    private static final String DUMP_VERSION = "inc101";

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
