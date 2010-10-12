package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.servlet.XmlJsonServlet;

import javax.servlet.ServletException;

public class TypeBasicInfoByTypeIdServlet extends XmlJsonServlet {
    private InvTypeDao invTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeID) {
        return dtoMapper.map(invTypeDao.getByTypeID(Long.valueOf(typeID)));
    }
}
