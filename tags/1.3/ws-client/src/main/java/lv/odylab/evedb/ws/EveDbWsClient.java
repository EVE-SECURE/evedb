package lv.odylab.evedb.ws;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class EveDbWsClient {
    private final String eveDbUrl;
    private final Gson gson;
    private final Type invTypeMaterialDtoListType;
    private final Type invTypeBasicInfoDtoListType;
    private final Type ramTypeRequirementDtoListType;

    public EveDbWsClient(String eveDbUrl) {
        this.eveDbUrl = eveDbUrl;
        this.gson = new Gson();
        this.invTypeMaterialDtoListType = new TypeToken<List<InvTypeMaterialDto>>() {
        }.getType();
        this.invTypeBasicInfoDtoListType = new TypeToken<List<InvTypeBasicInfoDto>>() {
        }.getType();
        this.ramTypeRequirementDtoListType = new TypeToken<List<RamTypeRequirementDto>>() {
        }.getType();
    }

    public List<InvTypeMaterialDto> getBaseMaterialsForTypeID(Long typeID) {
        String jsonString = doGet(eveDbUrl + "/baseMaterialsForTypeID/" + typeID);
        return gson.fromJson(jsonString, invTypeMaterialDtoListType);
    }

    public List<InvTypeMaterialDto> getBaseMaterialsForTypeName(String typeName) {
        String jsonString = doGet(eveDbUrl + "/baseMaterialsForTypeName/" + encodeString(typeName));
        return gson.fromJson(jsonString, invTypeMaterialDtoListType);
    }

    public InvBlueprintTypeDto getBlueprintTypeByTypeID(Long typeID) {
        String jsonString = doGet(eveDbUrl + "/blueprintTypeByTypeID/" + typeID);
        return gson.fromJson(jsonString, InvBlueprintTypeDto.class);
    }

    public InvBlueprintTypeDto getBlueprintTypeByTypeName(String typeName) {
        String jsonString = doGet(eveDbUrl + "/blueprintTypeByTypeName/" + encodeString(typeName));
        return gson.fromJson(jsonString, InvBlueprintTypeDto.class);
    }

    public List<RamTypeRequirementDto> getExtraMaterialsForTypeID(Long typeID) {
        String jsonString = doGet(eveDbUrl + "/extraMaterialsForTypeID/" + typeID);
        return gson.fromJson(jsonString, ramTypeRequirementDtoListType);
    }

    public List<RamTypeRequirementDto> getExtraMaterialsForTypeName(String typeName) {
        String jsonString = doGet(eveDbUrl + "/extraMaterialsForTypeName/" + encodeString(typeName));
        return gson.fromJson(jsonString, ramTypeRequirementDtoListType);
    }

    public List<InvTypeBasicInfoDto> lookupBlueprintType(String query) {
        String jsonString = doGet(eveDbUrl + "/lookupBlueprintType/" + encodeString(query));
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    public List<InvTypeBasicInfoDto> lookupResourceType(String query) {
        String jsonString = doGet(eveDbUrl + "/lookupResourceType/" + encodeString(query));
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    public List<InvTypeBasicInfoDto> lookupType(String query) {
        String jsonString = doGet(eveDbUrl + "/lookupType/" + encodeString(query));
        return gson.fromJson(jsonString, invTypeBasicInfoDtoListType);
    }

    public InvTypeBasicInfoDto getTypeBasicInfoByTypeID(Long typeID) {
        String jsonString = doGet(eveDbUrl + "/typeBasicInfoByTypeID/" + typeID);
        return gson.fromJson(jsonString, InvTypeBasicInfoDto.class);
    }

    public InvTypeBasicInfoDto getTypeBasicInfoByTypeName(String typeName) {
        String jsonString = doGet(eveDbUrl + "/typeBasicInfoByTypeName/" + encodeString(typeName));
        return gson.fromJson(jsonString, InvTypeBasicInfoDto.class);
    }

    public String getTypeIdToTypeName(Long typeID) {
        return doGet(eveDbUrl + "/typeIdToTypeName/" + typeID);
    }

    public Long getTypeNameToTypeID(String typeName) {
        return Long.valueOf(doGet(eveDbUrl + "/typeNameToTypeID/" + encodeString(typeName)));
    }

    public String getEveDbVersion() {
        return doGet(eveDbUrl + "/version");
    }

    private String encodeString(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String doGet(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
