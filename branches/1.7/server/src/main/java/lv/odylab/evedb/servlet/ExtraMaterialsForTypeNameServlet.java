package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.RamTypeRequirement;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import lv.odylab.evedb.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.rpc.dto.XmlRowsetDto;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ExtraMaterialsForTypeNameServlet extends XmlJsonServlet {
    private static final long serialVersionUID = 4589882782233437361L;

    private RamTypeRequirementDao ramTypeRequirementDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        ramTypeRequirementDao = new RamTypeRequirementDao();
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeName) {
        List<RamTypeRequirement> ramTypeRequirements = ramTypeRequirementDao.getForTypeName(typeName, DUMP_VERSION);
        List<RamTypeRequirementDto> result = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            result.add(dtoMapper.map(ramTypeRequirement));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new XmlRowsetDto((List) object), writer);
    }
}