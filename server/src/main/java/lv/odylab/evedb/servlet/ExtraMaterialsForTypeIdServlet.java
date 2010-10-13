package lv.odylab.evedb.servlet;

import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.client.rpc.dto.Rowset;
import lv.odylab.evedb.domain.RamTypeRequirement;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ExtraMaterialsForTypeIdServlet extends XmlJsonServlet {
    private static final long serialVersionUID = 4585710318931331719L;

    private RamTypeRequirementDao ramTypeRequirementDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        ramTypeRequirementDao = new RamTypeRequirementDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeID) {
        List<RamTypeRequirement> ramTypeRequirements = ramTypeRequirementDao.getForTypeID(Long.valueOf(typeID));
        List<RamTypeRequirementDto> result = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            result.add(dtoMapper.map(ramTypeRequirement));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new Rowset((List) object), writer);
    }
}