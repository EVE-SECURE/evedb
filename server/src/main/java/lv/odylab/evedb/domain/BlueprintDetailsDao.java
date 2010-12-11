package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

public class BlueprintDetailsDao {

    static {
        ObjectifyService.register(BlueprintDetails.class);
    }

    public BlueprintDetails getBlueprintDetailsForTypeID(Long typeID, String dumpVersion) {
        BlueprintDetails blueprintDetails = ObjectifyService.begin().query(BlueprintDetails.class)
                .filter("blueprintType.dumpVersion", dumpVersion)
                .filter("blueprintType.blueprintTypeID", typeID).get();
        if (blueprintDetails == null) {
            throw new IdNotFoundException(typeID);
        }
        return blueprintDetails;
    }

    public BlueprintDetails getBlueprintDetailsForTypeName(String typeName, String dumpVersion) {
        BlueprintDetails blueprintDetails = ObjectifyService.begin().query(BlueprintDetails.class)
                .filter("blueprintType.dumpVersion", dumpVersion)
                .filter("blueprintType.blueprintTypeName", typeName).get();
        if (blueprintDetails == null) {
            throw new NameNotFoundException(typeName);
        }
        return blueprintDetails;
    }
}
