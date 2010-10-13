package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.service.BlueprintService;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class BlueprintDetailsForTypeNameServlet extends XmlJsonServlet {
    private static final long serialVersionUID = 4262225737700759880L;

    private BlueprintService blueprintService;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        blueprintService = new BlueprintService(new InvBlueprintTypeDao(), new InvTypeMaterialDao(), new RamTypeRequirementDao(), DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        return dtoMapper.map(blueprintService.getBlueprintDetailsForTypeName(typeName));
    }
}
