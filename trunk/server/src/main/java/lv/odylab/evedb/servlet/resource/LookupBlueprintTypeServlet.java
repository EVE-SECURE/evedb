package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.XmlResultContainer;
import lv.odylab.evedb.domain.InvType;
import lv.odylab.evedb.domain.InvTypeDao;
import lv.odylab.evedb.servlet.XmlJsonServlet;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LookupBlueprintTypeServlet extends XmlJsonServlet {
    private InvTypeDao invTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String query) {
        List<InvType> invTypes = invTypeDao.findBlueprintByPartialTypeName(query, 50);
        List<InvTypeBasicInfoDto> result = new ArrayList<InvTypeBasicInfoDto>();
        for (InvType invType : invTypes) {
            result.add(dtoMapper.map(invType));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, PrintWriter writer) throws JAXBException {
        getMarshaller().marshal(new XmlResultContainer((List) object), writer);
    }
}