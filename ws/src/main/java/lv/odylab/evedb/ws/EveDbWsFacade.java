package lv.odylab.evedb.ws;

public interface EveDbWsFacade {

    String clearCache();

    String getInvTypeMaterialsForTypeIdAsJson(Long typeID);

    String getInvTypeMaterialsForTypeIdAsXml(Long typeID);

    String getInvTypeMaterialsForTypeNameAsJson(String typeName);

    String getInvTypeMaterialsForTypeNameAsXml(String typeName);

    String getBlueprintTypeByTypeIdAsJson(Long typeID);

    String getBlueprintTypeByTypeIdAsXml(Long typeID);

    String getBlueprintTypeByTypeNameAsJson(String typeName);

    String getBlueprintTypeByTypeNameAsXml(String typeName);

    String getBlueprintDetailsForTypeIdAsJson(Long typeID);

    String getBlueprintDetailsForTypeIdAsXml(Long typeID);

    String getBlueprintDetailsForTypeNameAsJson(String typeName);

    String getBlueprintDetailsForTypeNameAsXml(String typeName);

    String getRamTypeRequirementsForTypeIdAsJson(Long typeID);

    String getRamTypeRequirementsForTypeIdAsXml(Long typeID);

    String getRamTypeRequirementsForTypeNameAsJson(String typeName);

    String getRamTypeRequirementsForTypeNameAsXml(String typeName);

    String findBlueprintTypeByPartialTypeNameAsJson(String query);

    String findBlueprintTypeByPartialTypeNameAsXml(String query);

    String findResourceTypeByPartialTypeNameAsJson(String query);

    String findResourceTypeByPartialTypeNameAsXml(String query);

    String findTypeByPartialTypeNameAsJson(String query);

    String findTypeByPartialTypeNameAsXml(String query);

    String getTypeBasicInfoByTypeIdAsJson(Long typeID);

    String getTypeBasicInfoByTypeIdAsXml(Long typeID);

    String getTypeBasicInfoByTypeNameAsJson(String typeName);

    String getTypeBasicInfoByTypeNameAsXml(String typeName);

    String getTypeIdByTypeName(String typeName);

    String getTypeNameByTypeID(Long typeID);

    String getVersion();

}