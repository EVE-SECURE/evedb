package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.PlanetSchematic;
import lv.odylab.evedb.domain.PlanetSchematicDao;
import lv.odylab.evedb.rpc.dto.PlanetSchematicDto;
import lv.odylab.evedb.rpc.dto.XmlRowsetDto;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PlanetSchematicsForTypeIdServlet extends XmlJsonServlet {
    private PlanetSchematicDao planetSchematicDao;
    private DtoMapper mapper;

    @Override
    public void init() throws ServletException {
        planetSchematicDao = getComponent(PlanetSchematicDao.class);
        mapper = getComponent(DtoMapper.class);
    }

    @Override
    protected Object provideResponse(String typeName) {
        List<PlanetSchematic> planetSchematics = planetSchematicDao.getForTypeID(Long.valueOf(typeName));
        List<PlanetSchematicDto> result = new ArrayList<PlanetSchematicDto>();
        for (PlanetSchematic planetSchematic : planetSchematics) {
            result.add(mapper.map(planetSchematic));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new XmlRowsetDto((List) object), writer);
    }
}
