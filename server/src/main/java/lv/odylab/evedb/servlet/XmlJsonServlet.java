package lv.odylab.evedb.servlet;

import com.google.gson.Gson;
import lv.odylab.evedb.client.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.client.rpc.dto.XmlResultContainer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class XmlJsonServlet extends EveDbServlet {
    private Gson gson;
    private Marshaller marshaller;

    @Override
    protected void writeResponse(String pathInfo, String acceptHeader, HttpServletResponse resp) throws IOException, JAXBException {
        if ("application/json".equals(acceptHeader)) {
            resp.setContentType("application/json");
            writeJson(provideResponseFromCache(pathInfo), resp.getWriter());
        } else {
            resp.setContentType("application/xml");
            writeXml(provideResponseFromCache(pathInfo), resp.getWriter());
        }
    }

    protected void writeJson(Object object, PrintWriter writer) {
        getGson().toJson(object, writer);
    }

    protected void writeXml(Object object, PrintWriter writer) throws JAXBException {
        getMarshaller().marshal(object, writer);
    }

    protected Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    protected Marshaller getMarshaller() throws JAXBException {
        if (marshaller == null) {
            JAXBContext jaxbContext = JAXBContext.newInstance(InvBlueprintTypeDto.class, InvTypeMaterialDto.class, InvTypeBasicInfoDto.class, RamTypeRequirementDto.class, BlueprintDetailsDto.class, XmlResultContainer.class);
            marshaller = jaxbContext.createMarshaller();
        }
        return marshaller;
    }
}
