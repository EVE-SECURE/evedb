package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class BlueprintTypeByTypeNameServlet extends XmlJsonServlet {
    private static final long serialVersionUID = -7574761881653601894L;

    private InvBlueprintTypeDao invBlueprintTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invBlueprintTypeDao = new InvBlueprintTypeDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        return dtoMapper.map(invBlueprintTypeDao.getByTypeName(typeName));
    }
}