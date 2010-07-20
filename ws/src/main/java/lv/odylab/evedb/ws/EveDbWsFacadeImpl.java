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
    @Caching(keyPrefix = "EveDbWsFacade|getInvTypeMaterialsForTypeIdAsJson")
    public String getInvTypeMaterialsForTypeIdAsJson(Long typeID) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeID(typeID);
        return gson.toJson(invTypeMaterialDtos, invTypeMaterialDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getInvTypeMaterialsForTypeIdAsXml")
    public String getInvTypeMaterialsForTypeIdAsXml(Long typeID) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeID(typeID);
        XmlResultContainer<InvTypeMaterialDto> xmlResultContainer = new XmlResultContainer<InvTypeMaterialDto>(invTypeMaterialDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getInvTypeMaterialsForTypeNameAsJson")
    public String getInvTypeMaterialsForTypeNameAsJson(String typeName) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeName(decodeString(typeName));
        return gson.toJson(invTypeMaterialDtos, invTypeMaterialDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getInvTypeMaterialsForTypeNameAsXml")
    public String getInvTypeMaterialsForTypeNameAsXml(String typeName) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = clientFacade.getInvTypeMaterialsForTypeName(decodeString(typeName));
        XmlResultContainer<InvTypeMaterialDto> xmlResultContainer = new XmlResultContainer<InvTypeMaterialDto>(invTypeMaterialDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getBlueprintTypeByTypeIdAsJson")
    public String getBlueprintTypeByTypeIdAsJson(Long typeID) {
        InvBlueprintTypeDto invBlueprintTypeDtos = clientFacade.getBlueprintTypeByTypeID(typeID);
        return gson.toJson(invBlueprintTypeDtos, InvBlueprintTypeDto.class);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getBlueprintTypeByTypeIdAsXml")
    public String getBlueprintTypeByTypeIdAsXml(Long typeID) {
        InvBlueprintTypeDto invBlueprintTypeDtos = clientFacade.getBlueprintTypeByTypeID(typeID);
        return marshall(invBlueprintTypeDtos);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getBlueprintTypeByTypeNameAsJson")
    public String getBlueprintTypeByTypeNameAsJson(String typeName) {
        InvBlueprintTypeDto invBlueprintTypeDtos = clientFacade.getBlueprintTypeByTypeName(decodeString(typeName));
        return gson.toJson(invBlueprintTypeDtos, InvBlueprintTypeDto.class);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getBlueprintTypeByTypeNameAsXml")
    public String getBlueprintTypeByTypeNameAsXml(String typeName) {
        InvBlueprintTypeDto invBlueprintTypeDtos = clientFacade.getBlueprintTypeByTypeName(decodeString(typeName));
        return marshall(invBlueprintTypeDtos);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getRamTypeRequirementsForTypeIdAsJson")
    public String getRamTypeRequirementsForTypeIdAsJson(Long typeID) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeID(typeID);
        return gson.toJson(ramTypeRequirementDtos, ramTypeRequirementDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getRamTypeRequirementsForTypeIdAsXml")
    public String getRamTypeRequirementsForTypeIdAsXml(Long typeID) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeID(typeID);
        XmlResultContainer<RamTypeRequirementDto> xmlResultContainer = new XmlResultContainer<RamTypeRequirementDto>(ramTypeRequirementDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getRamTypeRequirementsForTypeNameAsJson")
    public String getRamTypeRequirementsForTypeNameAsJson(String typeName) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeName(decodeString(typeName));
        return gson.toJson(ramTypeRequirementDtos, ramTypeRequirementDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getRamTypeRequirementsForTypeNameAsXml")
    public String getRamTypeRequirementsForTypeNameAsXml(String typeName) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = clientFacade.getRamTypeRequirementsForTypeName(decodeString(typeName));
        XmlResultContainer<RamTypeRequirementDto> xmlResultContainer = new XmlResultContainer<RamTypeRequirementDto>(ramTypeRequirementDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findBlueprintTypeByPartialTypeNameAsJson")
    public String findBlueprintTypeByPartialTypeNameAsJson(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findBlueprintTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findBlueprintTypeByPartialTypeNameAsXml")
    public String findBlueprintTypeByPartialTypeNameAsXml(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findBlueprintTypeByPartialTypeName(decodeString(query));
        XmlResultContainer<InvTypeBasicInfoDto> xmlResultContainer = new XmlResultContainer<InvTypeBasicInfoDto>(invTypeBasicInfoDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findResourceTypeByPartialTypeNameAsJson")
    public String findResourceTypeByPartialTypeNameAsJson(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findResourceTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findResourceTypeByPartialTypeNameAsXml")
    public String findResourceTypeByPartialTypeNameAsXml(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findResourceTypeByPartialTypeName(decodeString(query));
        XmlResultContainer<InvTypeBasicInfoDto> xmlResultContainer = new XmlResultContainer<InvTypeBasicInfoDto>(invTypeBasicInfoDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findTypeByPartialTypeNameAsJson")
    public String findTypeByPartialTypeNameAsJson(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findTypeByPartialTypeNameAsXml")
    public String findTypeByPartialTypeNameAsXml(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = clientFacade.findTypeByPartialTypeName(decodeString(query));
        XmlResultContainer<InvTypeBasicInfoDto> xmlResultContainer = new XmlResultContainer<InvTypeBasicInfoDto>(invTypeBasicInfoDtos);
        return marshall(xmlResultContainer);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeBasicInfoByTypeIdAsJson")
    public String getTypeBasicInfoByTypeIdAsJson(Long typeID) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeID(typeID);
        return gson.toJson(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeBasicInfoByTypeIdAsXml")
    public String getTypeBasicInfoByTypeIdAsXml(Long typeID) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeID(typeID);
        return marshall(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeBasicInfoByTypeNameAsJson")
    public String getTypeBasicInfoByTypeNameAsJson(String typeName) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeName(decodeString(typeName));
        return gson.toJson(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeBasicInfoByTypeNameAsXml")
    public String getTypeBasicInfoByTypeNameAsXml(String typeName) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = clientFacade.getTypeBasicInfoByTypeName(decodeString(typeName));
        return marshall(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeIdByTypeName")
    public String getTypeIdByTypeName(String typeName) {
        return String.valueOf(clientFacade.getTypeIdByTypeName(decodeString(typeName)));
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeNameByTypeID")
    public String getTypeNameByTypeID(Long typeID) {
        return clientFacade.getTypeNameByTypeID(typeID);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getVersion")
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