package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.domain.inv.type.InvTypeDao;
import lv.odylab.evedb.ws.TextServlet;

import javax.servlet.ServletException;

public class TypeIdToTypeNameServlet extends TextServlet {
    private InvTypeDao invTypeDao;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
    }

    @Override
    protected Object provideResponse(String typeID) {
        return invTypeDao.getByTypeID(Long.valueOf(typeID)).getTypeName();
    }
}
