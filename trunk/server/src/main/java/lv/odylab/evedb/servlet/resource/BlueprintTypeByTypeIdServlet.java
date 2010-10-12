package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.servlet.XmlJsonServlet;

import javax.servlet.ServletException;

public class BlueprintTypeByTypeIdServlet extends XmlJsonServlet {
    private InvBlueprintTypeDao invBlueprintTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invBlueprintTypeDao = new InvBlueprintTypeDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeID) {
        return dtoMapper.map(invBlueprintTypeDao.getByTypeID(Long.valueOf(typeID)));
    }
}
