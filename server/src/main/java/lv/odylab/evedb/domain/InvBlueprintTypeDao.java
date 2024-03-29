package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

public class InvBlueprintTypeDao {

    static {
        ObjectifyService.register(InvBlueprintType.class);
    }

    private final String dumpVersion;

    public InvBlueprintTypeDao(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }

    public InvBlueprintType getByTypeID(Long typeID) {
        InvBlueprintType invBlueprintType = ObjectifyService.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeID", typeID).get();
        if (invBlueprintType == null) {
            throw new IdNotFoundException(typeID);
        }
        return invBlueprintType;
    }

    public InvBlueprintType getByTypeName(String typeName) {
        InvBlueprintType invBlueprintType = ObjectifyService.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeName", typeName).get();
        if (invBlueprintType == null) {
            throw new NameNotFoundException(typeName);
        }
        return invBlueprintType;
    }
}
