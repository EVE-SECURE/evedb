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
    private RamTypeRequirementDao ramTypeRequirementDao;
    private DtoMapper mapper;

    @Override
    public void init() throws ServletException {
        ramTypeRequirementDao = getComponent(RamTypeRequirementDao.class);
        mapper = getComponent(DtoMapper.class);
    }

    @Override
    protected Object provideResponse(String typeName) {
        List<RamTypeRequirement> ramTypeRequirements = ramTypeRequirementDao.getForTypeName(typeName);
        List<RamTypeRequirementDto> result = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            result.add(mapper.map(ramTypeRequirement));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new XmlRowsetDto((List) object), writer);
    }
}