package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterialDao;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirementDao;
import lv.odylab.evedb.service.BlueprintService;
import lv.odylab.evedb.service.BlueprintServiceImpl;
import lv.odylab.evedb.ws.XmlJsonServlet;

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
