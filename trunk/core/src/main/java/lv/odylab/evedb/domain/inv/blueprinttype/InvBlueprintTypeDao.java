package lv.odylab.evedb.domain.inv.blueprinttype;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;

public class InvBlueprintTypeDao {
    private final ObjectifyFactory objectifyFactory;

    @Inject
    public InvBlueprintTypeDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public InvBlueprintType getByTypeID(Long typeID, String dumpVersion) {
        InvBlueprintType invBlueprintType = objectifyFactory.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeID", typeID).get();
        if (invBlueprintType == null) {
            throw new IdNotFoundException(typeID);
        }
        return invBlueprintType;
    }

    public InvBlueprintType getByTypeName(String typeName, String dumpVersion) {
        InvBlueprintType invBlueprintType = objectifyFactory.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeName", typeName).get();
        if (invBlueprintType == null) {
            throw new NameNotFoundException(typeName);
        }
        return invBlueprintType;
    }
}
