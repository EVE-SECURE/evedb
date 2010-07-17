package lv.odylab.evedb.domain.inv.blueprinttype;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;

public class InvBlueprintTypeDao {
    private final ObjectifyFactory objectifyFactory;

    @Inject
    public InvBlueprintTypeDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public InvBlueprintType getByTypeID(Long typeID, String dumpVersion) {
        return objectifyFactory.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeID", typeID).get();
    }

    public InvBlueprintType getByTypeName(String typeName, String dumpVersion) {
        return objectifyFactory.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeName", typeName).get();
    }
}
