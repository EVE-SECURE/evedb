package lv.odylab.evedb.ws;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.evedb.application.EveDbClientFacade;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.client.rpc.dto.XmlResultContainer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;

@Singleton
public class EveDbWsFacadeImpl implements EveDbWsFacade {
    private final Gson gson;
    private final EveDbClientFacade clientFacade;
    private final Type invTypeMaterialDtoListType;
    private final Type invTypeBasicInfoDtoListType;
    private final Type ramTypeRequirementDtoListType;
    private final Marshaller marshaller;

    @Inject
    public EveDbWsFacadeImpl(Gson gson, EveDbClientFacade clientFacade) {
        this.gson = gson;
        this.clientFacade = clientFacade;

        invTypeMaterialDtoListType = new TypeToken<List<InvTypeMaterialDto>>() {
        }.getType();
        invTypeBasicInfoDtoListType = new TypeToken<List<InvTypeBasicInfoDto>>() {
        }.getType();
        ramTypeRequirementDtoListType = new TypeToken<List<RamTypeRequirementDto>>() {
        }.getType();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(InvBlueprintTypeDto.class, InvTypeMaterialDto.class, InvTypeBasicInfoDto.class, RamTypeRequirementDto.class, XmlResultContainer.class);
            marshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to create jaxbContext or marshaller in eveDbWsFacade", e);
        }
    }

    @Override
    public String clearCache() {
        return clientFacade.clearCache();
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getInvTypeMaterialsForTypeIdAsJson")
    public String getInvTypeMaterialsForTypeIdAsJson(Long typeID) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeID(typeID);
        return gson.toJson(invTypeMaterialDtos, invTypeMaterialDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getInvTypeMaterialsForTypeIdAsXml")
    public String getInvTypeMaterialsForTypeIdAsXml(Long typeID) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeID(typeID);
        XmlResultContainer<InvTypeMaterialDto> xmlResultContainer = new XmlResultContainer<InvTypeMaterialDto>(invTypeMaterialDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getInvTypeMaterialsForTypeNameAsJson")
    public String getInvTypeMaterialsForTypeNameAsJson(String typeName) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeName(decodeString(typeName));
        return gson.toJson(invTypeMaterialDtos, invTypeMaterialDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getInvTypeMaterialsForTypeNameAsXml")
    public String getInvTypeMaterialsForTypeNameAsXml(String typeName) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeName(decodeString(typeName));
        XmlResultContainer<InvTypeMaterialDto> xmlResultContainer = new XmlResultContainer<InvTypeMaterialDto>(invTypeMaterialDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getBlueprintTypeByTypeIdAsJson")
    public String getBlueprintTypeByTypeIdAsJson(Long typeID) {
        InvBlueprintTypeDto invBlueprintTypeDto = clientFacade.getBlueprintTypeByTypeID(typeID);
        return gson.toJson(invBlueprintTypeDto, InvBlueprintTypeDto.class);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getBlueprintTypeByTypeIdAsXml")
    public String getBlueprintTypeByTypeIdAsXml(Long typeID) {
        InvBlueprintTypeDto invBlueprintTypeDto = clientFacade.getBlueprintTypeByTypeID(typeID);
        return marshall(invBlueprintTypeDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getBlueprintTypeByTypeNameAsJson")
    public String getBlueprintTypeByTypeNameAsJson(String typeName) {
        InvBlueprintTypeDto invBlueprintTypeDto = clientFacade.getBlueprintTypeByTypeName(decodeString(typeName));
        return gson.toJson(invBlueprintTypeDto, InvBlueprintTypeDto.class);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getBlueprintTypeByTypeNameAsXml")
    public String getBlueprintTypeByTypeNameAsXml(String typeName) {
        InvBlueprintTypeDto invBlueprintTypeDto = clientFacade.getBlueprintTypeByTypeName(decodeString(typeName));
        return marshall(invBlueprintTypeDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getRamTypeRequirementsForTypeIdAsJson")
    public String getRamTypeRequirementsForTypeIdAsJson(Long typeID) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeID(typeID);
        return gson.toJson(ramTypeRequirementDtos, ramTypeRequirementDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getRamTypeRequirementsForTypeIdAsXml")
    public String getRamTypeRequirementsForTypeIdAsXml(Long typeID) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeID(typeID);
        XmlResultContainer<RamTypeRequirementDto> xmlResultContainer = new XmlResultContainer<RamTypeRequirementDto>(ramTypeRequirementDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getRamTypeRequirementsForTypeNameAsJson")
    public String getRamTypeRequirementsForTypeNameAsJson(String typeName) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeName(decodeString(typeName));
        return gson.toJson(ramTypeRequirementDtos, ramTypeRequirementDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getRamTypeRequirementsForTypeNameAsXml")
    public String getRamTypeRequirementsForTypeNameAsXml(String typeName) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeName(decodeString(typeName));
        XmlResultContainer<RamTypeRequirementDto> xmlResultContainer = new XmlResultContainer<RamTypeRequirementDto>(ramTypeRequirementDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|findBlueprintTypeByPartialTypeNameAsJson")
    public String findBlueprintTypeByPartialTypeNameAsJson(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findBlueprintTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|findBlueprintTypeByPartialTypeNameAsXml")
    public String findBlueprintTypeByPartialTypeNameAsXml(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findBlueprintTypeByPartialTypeName(decodeString(query));
        XmlResultContainer<InvTypeBasicInfoDto> xmlResultContainer = new XmlResultContainer<InvTypeBasicInfoDto>(invTypeBasicInfoDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|findResourceTypeByPartialTypeNameAsJson")
    public String findResourceTypeByPartialTypeNameAsJson(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findResourceTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|findResourceTypeByPartialTypeNameAsXml")
    public String findResourceTypeByPartialTypeNameAsXml(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findResourceTypeByPartialTypeName(decodeString(query));
        XmlResultContainer<InvTypeBasicInfoDto> xmlResultContainer = new XmlResultContainer<InvTypeBasicInfoDto>(invTypeBasicInfoDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|findTypeByPartialTypeNameAsJson")
    public String findTypeByPartialTypeNameAsJson(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|findTypeByPartialTypeNameAsXml")
    public String findTypeByPartialTypeNameAsXml(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findTypeByPartialTypeName(decodeString(query));
        XmlResultContainer<InvTypeBasicInfoDto> xmlResultContainer = new XmlResultContainer<InvTypeBasicInfoDto>(invTypeBasicInfoDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getTypeBasicInfoByTypeIdAsJson")
    public String getTypeBasicInfoByTypeIdAsJson(Long typeID) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeID(typeID);
        return gson.toJson(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getTypeBasicInfoByTypeIdAsXml")
    public String getTypeBasicInfoByTypeIdAsXml(Long typeID) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeID(typeID);
        return marshall(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getTypeBasicInfoByTypeNameAsJson")
    public String getTypeBasicInfoByTypeNameAsJson(String typeName) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeName(decodeString(typeName));
        return gson.toJson(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getTypeBasicInfoByTypeNameAsXml")
    public String getTypeBasicInfoByTypeNameAsXml(String typeName) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeName(decodeString(typeName));
        return marshall(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getTypeIdByTypeName")
    public String getTypeIdByTypeName(String typeName) {
        Long typeID = clientFacade.getTypeIdByTypeName(decodeString(typeName));
        return String.valueOf(typeID);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getTypeNameByTypeID")
    public String getTypeNameByTypeID(Long typeID) {
        return clientFacade.getTypeNameByTypeID(typeID);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacadeImpl|getVersion")
    public String getVersion() {
        return clientFacade.getVersion();
    }

    private String decodeString(String string) {
        if (string == null) {
            return null;
        }
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String marshall(Object object) {
        StringWriter stringWriter = new StringWriter();
        try {
            marshaller.marshal(object, stringWriter);
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to marshal object to XML", e);
        }
        return stringWriter.toString();
    }
}