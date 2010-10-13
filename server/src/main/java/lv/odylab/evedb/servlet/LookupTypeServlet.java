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

public class LookupTypeServlet extends XmlJsonServlet {
    private static final long serialVersionUID = -9066097416759112366L;

    private InvTypeDao invTypeDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invTypeDao = new InvTypeDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String query) {
        List<InvType> invTypes = invTypeDao.findByPartialTypeName(query, 50);
        List<InvTypeBasicInfoDto> result = new ArrayList<InvTypeBasicInfoDto>();
        for (InvType invType : invTypes) {
            result.add(dtoMapper.map(invType));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new XmlRowsetDto((List) object), writer);
    }
}
