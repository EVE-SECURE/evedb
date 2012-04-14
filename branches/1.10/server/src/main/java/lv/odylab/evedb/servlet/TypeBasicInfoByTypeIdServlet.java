package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class TypeBasicInfoByTypeIdServlet extends XmlJsonServlet {
    private InvTypeDao invTypeDao;
    private DtoMapper mapper;

    @Override
    public void init() throws ServletException {
        invTypeDao = getComponent(InvTypeDao.class);
        mapper = getComponent(DtoMapper.class);
    }

    @Override
    protected Object provideResponse(String typeID) {
        return mapper.map(invTypeDao.getByTypeID(Long.valueOf(typeID)));
    }
}
