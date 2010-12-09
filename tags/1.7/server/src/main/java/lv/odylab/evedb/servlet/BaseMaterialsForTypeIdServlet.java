package lv.odylab.evedb.servlet;

import lv.odylab.evedb.domain.InvTypeMaterial;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.rpc.dto.XmlRowsetDto;
import lv.odylab.evedb.service.DtoMapper;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class BaseMaterialsForTypeIdServlet extends XmlJsonServlet {
    private static final long serialVersionUID = 5400235143915375332L;

    private InvTypeMaterialDao invTypeMaterialDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invTypeMaterialDao = new InvTypeMaterialDao();
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeID) {
        List<InvTypeMaterial> invTypeMaterials = invTypeMaterialDao.getForTypeID(Long.valueOf(typeID), DUMP_VERSION);
        List<InvTypeMaterialDto> result = new ArrayList<InvTypeMaterialDto>();
        for (InvTypeMaterial invTypeMaterial : invTypeMaterials) {
            result.add(dtoMapper.map(invTypeMaterial));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, Writer writer) throws JAXBException {
        getMarshaller().marshal(new XmlRowsetDto((List) object), writer);
    }
}
