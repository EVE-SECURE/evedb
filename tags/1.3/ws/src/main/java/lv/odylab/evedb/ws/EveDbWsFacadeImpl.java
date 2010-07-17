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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;

@Singleton
public class EveDbWsFacadeImpl implements EveDbWsFacade {
    private final Gson gson;
    private final EveDbClientFacade eveDbClientFacade;
    private final Type invTypeMaterialDtoListType;
    private final Type invTypeBasicInfoDtoListType;
    private final Type ramTypeRequirementDtoListType;

    @Inject
    public EveDbWsFacadeImpl(Gson gson, EveDbClientFacade eveDbClientFacade) {
        this.gson = gson;
        this.eveDbClientFacade = eveDbClientFacade;

        this.invTypeMaterialDtoListType = new TypeToken<List<InvTypeMaterialDto>>() {
        }.getType();
        this.invTypeBasicInfoDtoListType = new TypeToken<List<InvTypeBasicInfoDto>>() {
        }.getType();
        this.ramTypeRequirementDtoListType = new TypeToken<List<RamTypeRequirementDto>>() {
        }.getType();
    }

    @Override
    public String clearCache() {
        return eveDbClientFacade.clearCache();
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getInvTypeMaterialsForTypeID")
    public String getInvTypeMaterialsForTypeID(Long typeID) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = eveDbClientFacade.getInvTypeMaterialsForTypeID(typeID);
        return gson.toJson(invTypeMaterialDtos, invTypeMaterialDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getInvTypeMaterialsForTypeName")
    public String getInvTypeMaterialsForTypeName(String typeName) {
        List<InvTypeMaterialDto> invTypeMaterialDtos = eveDbClientFacade.getInvTypeMaterialsForTypeName(decodeString(typeName));
        return gson.toJson(invTypeMaterialDtos, invTypeMaterialDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getBlueprintTypeByTypeID")
    public String getBlueprintTypeByTypeID(Long typeID) {
        InvBlueprintTypeDto invBlueprintTypeDtos = eveDbClientFacade.getBlueprintTypeByTypeID(typeID);
        return gson.toJson(invBlueprintTypeDtos, InvBlueprintTypeDto.class);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getBlueprintTypeByTypeName")
    public String getBlueprintTypeByTypeName(String typeName) {
        InvBlueprintTypeDto invBlueprintTypeDtos = eveDbClientFacade.getBlueprintTypeByTypeName(decodeString(typeName));
        return gson.toJson(invBlueprintTypeDtos, InvBlueprintTypeDto.class);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getRamTypeRequirementsForTypeID")
    public String getRamTypeRequirementsForTypeID(Long typeID) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = eveDbClientFacade.getRamTypeRequirementsForTypeID(typeID);
        return gson.toJson(ramTypeRequirementDtos, ramTypeRequirementDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getRamTypeRequirementsForTypeName")
    public String getRamTypeRequirementsForTypeName(String typeName) {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = eveDbClientFacade.getRamTypeRequirementsForTypeName(decodeString(typeName));
        return gson.toJson(ramTypeRequirementDtos, ramTypeRequirementDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findBlueprintTypeByPartialTypeName")
    public String findBlueprintTypeByPartialTypeName(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = eveDbClientFacade.findBlueprintTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findResourceTypeByPartialTypeName")
    public String findResourceTypeByPartialTypeName(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = eveDbClientFacade.findResourceTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|findTypeByPartialTypeName")
    public String findTypeByPartialTypeName(String query) {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = eveDbClientFacade.findTypeByPartialTypeName(decodeString(query));
        return gson.toJson(invTypeBasicInfoDtos, invTypeBasicInfoDtoListType);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeBasicInfoByTypeID")
    public String getTypeBasicInfoByTypeID(Long typeID) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = eveDbClientFacade.getTypeBasicInfoByTypeID(typeID);
        return gson.toJson(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeBasicInfoByTypeName")
    public String getTypeBasicInfoByTypeName(String typeName) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = eveDbClientFacade.getTypeBasicInfoByTypeName(decodeString(typeName));
        return gson.toJson(invTypeBasicInfoDto);
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeIdByTypeName")
    public String getTypeIdByTypeName(String typeName) {
        return String.valueOf(eveDbClientFacade.getTypeIdByTypeName(decodeString(typeName)));
    }

    @Override
    @Caching(keyPrefix = "EveDbWsFacade|getTypeNameByTypeID")
    public String getTypeNameByTypeID(Long typeID) {
        return eveDbClientFacade.getTypeNameByTypeID(typeID);
    }

    @Override
    public String getEveDbVersion() {
        return eveDbClientFacade.getEveDbVersion();
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
}