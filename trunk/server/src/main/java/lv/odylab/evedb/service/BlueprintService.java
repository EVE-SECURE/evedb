package lv.odylab.evedb.service;

public interface BlueprintService {

    BlueprintDetails getBlueprintDetailsForTypeID(Long typeID);

    BlueprintDetails getBlueprintDetailsForTypeName(String typeName);

}
