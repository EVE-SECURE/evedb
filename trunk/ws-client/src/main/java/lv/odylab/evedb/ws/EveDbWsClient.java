package lv.odylab.evedb.ws;

import lv.odylab.evedb.client.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;

import java.util.List;

public interface EveDbWsClient {

    public interface HttpRequestSender {

        String doGet(String urlString, String acceptHeader);

    }

    List<InvTypeMaterialDto> getBaseMaterialsForTypeID(Long typeID);

    List<InvTypeMaterialDto> getBaseMaterialsForTypeName(String typeName);

    InvBlueprintTypeDto getBlueprintTypeByTypeID(Long typeID);

    InvBlueprintTypeDto getBlueprintTypeByTypeName(String typeName);

    BlueprintDetailsDto getBlueprintDetailsForTypeID(Long typeID);

    BlueprintDetailsDto getBlueprintDetailsForTypeName(String typeName);

    List<RamTypeRequirementDto> getExtraMaterialsForTypeID(Long typeID);

    List<RamTypeRequirementDto> getExtraMaterialsForTypeName(String typeName);

    List<InvTypeBasicInfoDto> lookupBlueprintType(String query);

    List<InvTypeBasicInfoDto> lookupResourceType(String query);

    List<InvTypeBasicInfoDto> lookupType(String query);

    InvTypeBasicInfoDto getTypeBasicInfoByTypeID(Long typeID);

    InvTypeBasicInfoDto getTypeBasicInfoByTypeName(String typeName);

    String getTypeIdToTypeName(Long typeID);

    Long getTypeNameToTypeID(String typeName);

    String getVersion();

}
