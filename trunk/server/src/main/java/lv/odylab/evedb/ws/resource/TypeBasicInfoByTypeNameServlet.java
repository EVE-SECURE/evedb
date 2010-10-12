package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.domain.inv.type.InvTypeDao;
import lv.odylab.evedb.ws.XmlJsonServlet;

import javax.servlet.ServletException;

public class TypeBasicInfoByTypeNameServlet extends XmlJsonServlet {
    private InvTypeDao invTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        return dtoMapper.map(invTypeDao.getByTypeName(typeName));
    }
}