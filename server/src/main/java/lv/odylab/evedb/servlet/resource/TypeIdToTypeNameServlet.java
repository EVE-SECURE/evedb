package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.servlet.TextServlet;

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
