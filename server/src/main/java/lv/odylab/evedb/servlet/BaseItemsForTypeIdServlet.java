package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvType;
import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.rpc.dto.XmlRowsetDto;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class BaseItemsForTypeIdServlet extends XmlJsonServlet {
    private InvTypeDao invTypeDao;
    private DtoMapper mapper;

    @Override
    public void init() throws ServletException {
        invTypeDao = getComponent(InvTypeDao.class);
        mapper = getComponent(DtoMapper.class);
    }

    @Override
    protected Object provideResponse(String typeID) {
        List<InvType> invTypes = invTypeDao.getBaseItemsForTypeID(Long.valueOf(typeID));
        List<InvTypeBasicInfoDto> result = new ArrayList<InvTypeBasicInfoDto>();
        for (InvType invType : invTypes) {
            result.add(mapper.map(invType));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new XmlRowsetDto((List) object), writer);
    }
}

