package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.service.BlueprintService;
import lv.odylab.evedb.service.BlueprintServiceImpl;
import lv.odylab.evedb.servlet.XmlJsonServlet;

import javax.servlet.ServletException;

public class BlueprintDetailsForTypeIdServlet extends XmlJsonServlet {
    private BlueprintService blueprintService;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        blueprintService = new BlueprintServiceImpl(new InvBlueprintTypeDao(DUMP_VERSION), new InvTypeMaterialDao(DUMP_VERSION), new RamTypeRequirementDao(DUMP_VERSION));
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        return dtoMapper.map(blueprintService.getBlueprintDetailsForTypeID(Long.valueOf(typeName)));
    }
}
