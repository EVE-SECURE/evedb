package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintTypeDao;
import lv.odylab.evedb.ws.XmlJsonServlet;

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
