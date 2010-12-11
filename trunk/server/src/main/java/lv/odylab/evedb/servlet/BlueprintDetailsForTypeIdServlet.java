package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.BlueprintDetailsDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;

public class BlueprintDetailsForTypeIdServlet extends XmlJsonServlet {
    private BlueprintDetailsDao blueprintDetailsDao;
    private DtoMapper mapper;

    @Override
    public void init() throws ServletException {
        blueprintDetailsDao = getComponent(BlueprintDetailsDao.class);
        mapper = getComponent(DtoMapper.class);
    }

    @Override
    protected Object provideResponse(String typeName) {
        return mapper.map(blueprintDetailsDao.getBlueprintDetailsForTypeID(Long.valueOf(typeName)));
    }
}
