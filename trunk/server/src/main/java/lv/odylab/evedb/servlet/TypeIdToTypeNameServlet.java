package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeDao;

import javax.servlet.ServletException;

public class TypeIdToTypeNameServlet extends TextServlet {
    private static final long serialVersionUID = -1641727690563023377L;

    private InvTypeDao invTypeDao;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
    }

    @Override
    protected String provideResponse(String typeID) {
        return invTypeDao.getByTypeID(Long.valueOf(typeID)).getTypeName();
    }
}
