package lv.odylab.evedb.application;

import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintType;
import lv.odylab.evedb.domain.inv.type.InvType;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;

import java.util.List;

public interface EveDbApplicationFacade {

    String clearCache();

    List<InvTypeMaterial> getInvTypeMaterialsForTypeID(Long typeID);

    List<InvTypeMaterial> getInvTypeMaterialsForTypeName(String typeName);

    InvBlueprintType getBlueprintTypeByTypeID(Long typeID);

    InvBlueprintType getBlueprintTypeByTypeName(String typeName);

    List<RamTypeRequirement> getRamTypeRequirementsForTypeID(Long typeID);

    List<RamTypeRequirement> getRamTypeRequirementsForTypeName(String typeName);

    List<InvType> findTypeByPartialTypeName(String partialTypeName);

    List<InvType> findResourceTypeByPartialTypeName(String partialTypeName);

    List<InvType> findBlueprintTypeByPartialTypeName(String partialTypeName);

    InvType getTypeBasicInfoByTypeID(Long typeID);

    InvType getTypeBasicInfoByTypeName(String typeName);

    String getTypeNameByTypeID(Long typeID);

    Long getTypeIdByTypeName(String typeName);

    String getVersion();

}
