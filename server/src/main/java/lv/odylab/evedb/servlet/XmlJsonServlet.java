package lv.odylab.evedb.servlet;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.gson.Gson;
import lv.odylab.evedb.client.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.client.rpc.dto.Rowset;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class XmlJsonServlet extends EveDbServlet {
    private Gson gson;
    private Marshaller marshaller;

    @Override
    protected void writeResponse(String pathInfo, String acceptHeader, HttpServletResponse resp) throws IOException, JAXBException {
        MemcacheService memcacheService = getMemcacheService();
        StringBuilder keyBuilder = new StringBuilder(getClass().getSimpleName()).append("|").append(pathInfo).append("|").append(DUMP_VERSION);
        if ("application/json".equals(acceptHeader)) {
            String key = keyBuilder.append("|json").toString();
            String result = (String) memcacheService.get(key);
            if (result == null) {
                logger.info("Key was not found in cache: {}, the result will be cached", key);
                StringWriter stringWriter = new StringWriter();
                getGson().toJson(provideResponse(pathInfo), stringWriter);
                result = stringWriter.toString();
                memcacheService.put(key, result);
            } else {
                logger.info("Key was found in cache: {}", key);
            }
            resp.setContentType("application/json");
            resp.getWriter().write(result);
        } else {
            String key = keyBuilder.append("|xml").toString();
            String result = (String) memcacheService.get(key);
            if (result == null) {
                logger.info("Key was not found in cache: {}, the result will be cached", key);
                StringWriter stringWriter = new StringWriter();
                writeXml(provideResponse(pathInfo), stringWriter);
                result = stringWriter.toString();
                memcacheService.put(key, result);
            } else {
                logger.info("Key was found in cache: {}", key);
            }
            resp.setContentType("application/xml");
            resp.getWriter().write(result);
        }
    }

    protected abstract Object provideResponse(String pathInfo);

    protected void writeXml(Object object, Writer writer) throws JAXBException {
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
            JAXBContext jaxbContext = JAXBContext.newInstance(InvBlueprintTypeDto.class, InvTypeMaterialDto.class, InvTypeBasicInfoDto.class, RamTypeRequirementDto.class, BlueprintDetailsDto.class, Rowset.class);
            marshaller = jaxbContext.createMarshaller();
        }
        return marshaller;
    }
}
