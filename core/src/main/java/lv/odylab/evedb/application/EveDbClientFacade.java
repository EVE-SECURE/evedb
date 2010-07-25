package lv.odylab.evedb.application;

import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;

import java.util.List;

public interface EveDbClientFacade {

    String clearCache();

    List<InvTypeMaterialDto> getInvTypeMaterialsForTypeID(Long typeID);

    List<InvTypeMaterialDto> getInvTypeMaterialsForTypeName(String typeName);

    InvBlueprintTypeDto getBlueprintTypeByTypeID(Long typeID);

    InvBlueprintTypeDto getBlueprintTypeByTypeName(String blueprintTypeName);

    List<RamTypeRequirementDto> getRamTypeRequirementsForTypeID(Long typeID);

    List<RamTypeRequirementDto> getRamTypeRequirementsForTypeName(String typeName);

    List<InvTypeBasicInfoDto> findBlueprintTypeByPartialTypeName(String partialTypeName);

    List<InvTypeBasicInfoDto> findResourceTypeByPartialTypeName(String partialTypeName);

    List<InvTypeBasicInfoDto> findTypeByPartialTypeName(String partialTypeName);

    InvTypeBasicInfoDto getTypeBasicInfoByTypeID(Long typeID);

    InvTypeBasicInfoDto getTypeBasicInfoByTypeName(String typeName);

    Long getTypeIdByTypeName(String typeName);

    String getTypeNameByTypeID(Long typeID);

    String getVersion();

}
