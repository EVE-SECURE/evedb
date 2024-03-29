package lv.odylab.evedb.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lv.odylab.evedb.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.rpc.dto.PlanetSchematicDto;
import lv.odylab.evedb.rpc.dto.RamTypeRequirementDto;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

public class EveDbWsClientImpl implements EveDbWsClient {
    private final String eveDbUrl;
    private final Gson gson;
    private final HttpRequestSender requestSender;
    private final Type invTypeMaterialDtoListType;
    private final Type invTypeBasicInfoDtoListType;
    private final Type ramTypeRequirementDtoListType;
    private final Type planetarySchematicDtoListType;

    public EveDbWsClientImpl(String eveDbUrl) {
        this(eveDbUrl, new HttpRequestSenderImpl(), new Gson());
    }

    public EveDbWsClientImpl(String eveDbUrl, HttpRequestSender requestSender) {
        this(eveDbUrl, requestSender, new Gson());
    }

    public EveDbWsClientImpl(String eveDbUrl, HttpRequestSender requestSender, Gson gson) {
        this.eveDbUrl = eveDbUrl;
        this.requestSender = requestSender;
        this.gson = gson;

        this.invTypeMaterialDtoListType = new TypeToken<List<InvTypeMaterialDto>>() {
        }.getType();
        this.invTypeBasicInfoDtoListType = new TypeToken<List<InvTypeBasicInfoDto>>() {
        }.getType();
        this.ramTypeRequirementDtoListType = new TypeToken<List<RamTypeRequirementDto>>() {
        }.getType();
        this.planetarySchematicDtoListType = new TypeToken<List<PlanetSchematicDto>>() {
        }.getType();
    }

    @Override
    public List<InvTypeBasicInfoDto> getBaseItemsForTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/baseItemsForTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    @Override
    public List<InvTypeBasicInfoDto> getBaseItemsForTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/baseItemsForTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    @Override
    public List<InvTypeMaterialDto> getBaseMaterialsForTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/baseMaterialsForTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, invTypeMaterialDtoListType);
    }

    @Override
    public List<InvTypeMaterialDto> getBaseMaterialsForTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/baseMaterialsForTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, invTypeMaterialDtoListType);
    }

    @Override
    public InvBlueprintTypeDto getBlueprintTypeByTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/blueprintTypeByTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, InvBlueprintTypeDto.class);
    }

    @Override
    public InvBlueprintTypeDto getBlueprintTypeByTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/blueprintTypeByTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, InvBlueprintTypeDto.class);
    }

    @Override
    public BlueprintDetailsDto getBlueprintDetailsForTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/blueprintDetailsForTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, BlueprintDetailsDto.class);
    }

    @Override
    public BlueprintDetailsDto getBlueprintDetailsForTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/blueprintDetailsForTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, BlueprintDetailsDto.class);
    }

    @Override
    public List<RamTypeRequirementDto> getExtraMaterialsForTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/extraMaterialsForTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, ramTypeRequirementDtoListType);
    }

    @Override
    public List<RamTypeRequirementDto> getExtraMaterialsForTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/extraMaterialsForTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, ramTypeRequirementDtoListType);
    }

    @Override
    public List<InvTypeBasicInfoDto> lookupBlueprintType(String query) {
        String jsonString = requestSender.doGet(eveDbUrl + "/lookupBlueprintType/" + encodeString(query), "application/json");
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    @Override
    public List<InvTypeBasicInfoDto> lookupResourceType(String query) {
        String jsonString = requestSender.doGet(eveDbUrl + "/lookupResourceType/" + encodeString(query), "application/json");
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    @Override
    public List<InvTypeBasicInfoDto> lookupType(String query) {
        String jsonString = requestSender.doGet(eveDbUrl + "/lookupType/" + encodeString(query), "application/json");
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    @Override
    public List<PlanetSchematicDto> getPlanetSchematicForTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/planetSchematicForTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, planetarySchematicDtoListType);
    }

    @Override
    public List<PlanetSchematicDto> getPlanetSchematicForTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/planetSchematicForTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, planetarySchematicDtoListType);
    }

    @Override
    public InvTypeBasicInfoDto getTypeBasicInfoByTypeID(Long typeID) {
        String jsonString = requestSender.doGet(eveDbUrl + "/typeBasicInfoByTypeID/" + typeID, "application/json");
        return gson.fromJson(jsonString, InvTypeBasicInfoDto.class);
    }

    @Override
    public InvTypeBasicInfoDto getTypeBasicInfoByTypeName(String typeName) {
        String jsonString = requestSender.doGet(eveDbUrl + "/typeBasicInfoByTypeName/" + encodeString(typeName), "application/json");
        return gson.fromJson(jsonString, InvTypeBasicInfoDto.class);
    }

    @Override
    public String getTypeIdToTypeName(Long typeID) {
        return requestSender.doGet(eveDbUrl + "/typeIdToTypeName/" + typeID, "text/plain");
    }

    @Override
    public Long getTypeNameToTypeID(String typeName) {
        return Long.valueOf(requestSender.doGet(eveDbUrl + "/typeNameToTypeID/" + encodeString(typeName), "text/plain"));
    }

    @Override
    public String getVersion() {
        return requestSender.doGet(eveDbUrl + "/version", "text/plain");
    }

    private String encodeString(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}