package lv.odylab.evedb.application;

import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;

import java.util.List;

public interface EveDbClientFacade {

    String getTypeNameByTypeID(Long typeID);

    Long getTypeIdByTypeName(String typeName);

    List<InvTypeBasicInfoDto> findTypeByPartialTypeName(String partialTypeName);

    List<InvTypeBasicInfoDto> findResourceTypeByPartialTypeName(String partialTypeName);

    List<InvTypeBasicInfoDto> findBlueprintTypeByPartialTypeName(String partialTypeName);

    InvBlueprintTypeDto getBlueprintTypeByTypeID(Long typeID);

    InvBlueprintTypeDto getBlueprintTypeByTypeName(String blueprintTypeName);

    InvTypeBasicInfoDto getTypeBasicInfoByTypeID(Long typeID);

    InvTypeBasicInfoDto getTypeBasicInfoByTypeName(String typeName);

    List<InvTypeMaterialDto> getInvTypeMaterialsForTypeID(Long typeID);

    List<InvTypeMaterialDto> getInvTypeMaterialsForTypeName(String typeName);

    List<RamTypeRequirementDto> getRamTypeRequirementsForTypeID(Long typeID);

    List<RamTypeRequirementDto> getRamTypeRequirementsForTypeName(String typeName);

    String getEveDbVersion();

    String clearCache();

}
