package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class BlueprintTypeByTypeIdServlet extends XmlJsonServlet {
    private InvBlueprintTypeDao invBlueprintTypeDao;
    private DtoMapper mapper;

    @Override
    public void init() throws ServletException {
        invBlueprintTypeDao = getComponent(InvBlueprintTypeDao.class);
        mapper = getComponent(DtoMapper.class);
    }

    @Override
    protected Object provideResponse(String typeID) {
        return mapper.map(invBlueprintTypeDao.getByTypeID(Long.valueOf(typeID)));
    }
}
