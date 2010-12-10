package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeDao;

import javax.servlet.ServletException;

public class TypeNameToTypeIdServlet extends TextServlet {
    private InvTypeDao invTypeDao;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao();
    }

    @Override
    protected String provideResponse(String typeName) {
        return String.valueOf(invTypeDao.getByTypeName(typeName, DUMP_VERSION).getTypeID());
    }
}