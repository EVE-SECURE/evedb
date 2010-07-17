package lv.odylab.evedb.ws;

public interface EveDbWsFacade {

    String clearCache();

    String getInvTypeMaterialsForTypeID(Long typeID);

    String getInvTypeMaterialsForTypeName(String typeName);

    String getBlueprintTypeByTypeID(Long typeID);

    String getBlueprintTypeByTypeName(String typeName);

    String getRamTypeRequirementsForTypeID(Long typeID);

    String getRamTypeRequirementsForTypeName(String typeName);

    String findBlueprintTypeByPartialTypeName(String query);

    String findResourceTypeByPartialTypeName(String query);

    String findTypeByPartialTypeName(String query);

    String getTypeBasicInfoByTypeID(Long typeID);

    String getTypeBasicInfoByTypeName(String typeName);

    String getTypeIdByTypeName(String typeName);

    String getTypeNameByTypeID(Long typeID);

    String getEveDbVersion();

}