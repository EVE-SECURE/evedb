package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.BlueprintDetailsDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class BlueprintDetailsForTypeNameServlet extends XmlJsonServlet {
    private BlueprintDetailsDao blueprintDetailsDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        blueprintDetailsDao = new BlueprintDetailsDao();
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        return dtoMapper.map(blueprintDetailsDao.getBlueprintDetailsForTypeName(typeName, DUMP_VERSION));
    }
}
