package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

public class BlueprintDetailsDao {

    static {
        ObjectifyService.register(BlueprintDetails.class);
    }

    private final String dumpVersion;

    public BlueprintDetailsDao(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }

    public BlueprintDetails getBlueprintDetailsForTypeID(Long typeID) {
        BlueprintDetails blueprintDetails = ObjectifyService.begin().query(BlueprintDetails.class)
                .filter("blueprintType.dumpVersion", dumpVersion)
                .filter("blueprintType.blueprintTypeID", typeID).get();
        if (blueprintDetails == null) {
            throw new IdNotFoundException(typeID);
        }
        return blueprintDetails;
    }

    public BlueprintDetails getBlueprintDetailsForTypeName(String typeName) {
        BlueprintDetails blueprintDetails = ObjectifyService.begin().query(BlueprintDetails.class)
                .filter("blueprintType.dumpVersion", dumpVersion)
                .filter("blueprintType.blueprintTypeName", typeName).get();
        if (blueprintDetails == null) {
            throw new NameNotFoundException(typeName);
        }
        return blueprintDetails;
    }
}
