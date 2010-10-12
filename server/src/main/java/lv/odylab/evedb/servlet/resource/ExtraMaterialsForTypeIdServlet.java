package lv.odylab.evedb.servlet.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.client.rpc.dto.XmlResultContainer;
import lv.odylab.evedb.domain.RamTypeRequirement;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.servlet.XmlJsonServlet;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExtraMaterialsForTypeIdServlet extends XmlJsonServlet {
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
    protected void writeXml(Object object, PrintWriter writer) throws JAXBException {
        getMarshaller().marshal(new XmlResultContainer((List) object), writer);
    }
}