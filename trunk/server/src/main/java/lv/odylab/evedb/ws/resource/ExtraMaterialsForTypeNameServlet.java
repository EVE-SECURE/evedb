package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.client.rpc.dto.XmlResultContainer;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirementDao;
import lv.odylab.evedb.ws.XmlJsonServlet;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExtraMaterialsForTypeNameServlet extends XmlJsonServlet {
    private RamTypeRequirementDao ramTypeRequirementDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        ramTypeRequirementDao = new RamTypeRequirementDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        List<RamTypeRequirement> ramTypeRequirements = ramTypeRequirementDao.getForTypeName(typeName);
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