package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class TypeBasicInfoByTypeIdServlet extends XmlJsonServlet {
    private static final long serialVersionUID = 2325461574195997131L;

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
