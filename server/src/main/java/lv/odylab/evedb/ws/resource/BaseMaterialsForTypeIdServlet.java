package lv.odylab.evedb.ws.resource;

import lv.odylab.evedb.DtoMapper;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.XmlResultContainer;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterialDao;
import lv.odylab.evedb.ws.XmlJsonServlet;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BaseMaterialsForTypeIdServlet extends XmlJsonServlet {
    private InvTypeMaterialDao invTypeMaterialDao;
    private DtoMapper dtoMapper;

    @Override
    public void init() throws ServletException {
        invTypeMaterialDao = new InvTypeMaterialDao(DUMP_VERSION);
        dtoMapper = new DtoMapper();
    }

    @Override
    protected Object provideResponse(String typeID) {
        List<InvTypeMaterial> invTypeMaterials = invTypeMaterialDao.getForTypeID(Long.valueOf(typeID));
        List<InvTypeMaterialDto> result = new ArrayList<InvTypeMaterialDto>();
        for (InvTypeMaterial invTypeMaterial : invTypeMaterials) {
            result.add(dtoMapper.map(invTypeMaterial));
        }
        return result;
    }

    @Override
    protected void writeXml(Object object, PrintWriter writer) throws JAXBException {
        getMarshaller().marshal(new XmlResultContainer((List) object), writer);
    }
}
