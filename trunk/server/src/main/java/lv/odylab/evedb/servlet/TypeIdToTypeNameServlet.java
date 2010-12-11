package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeDao;

import javax.servlet.ServletException;

public class TypeIdToTypeNameServlet extends TextServlet {
    private InvTypeDao invTypeDao;

    @Override
    public void init() throws ServletException {
        invTypeDao = getComponent(InvTypeDao.class);
    }

    @Override
    protected String provideResponse(String typeID) {
        return invTypeDao.getByTypeID(Long.valueOf(typeID)).getTypeName();
    }
}
