package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class BlueprintTypeByTypeIdServlet extends XmlJsonServlet {
    private InvBlueprintTypeDao invBlueprintTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invBlueprintTypeDao = new InvBlueprintTypeDao();
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeID) {
        return dtoMapper.map(invBlueprintTypeDao.getByTypeID(Long.valueOf(typeID), DUMP_VERSION));
    }
}
