package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeDao;

import javax.servlet.ServletException;

public class TypeNameToTypeIdServlet extends TextServlet {
    private InvTypeDao invTypeDao;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
    }

    @Override
    protected Object provideResponse(String typeName) {
        return invTypeDao.getByTypeName(typeName).getTypeID();
    }
}