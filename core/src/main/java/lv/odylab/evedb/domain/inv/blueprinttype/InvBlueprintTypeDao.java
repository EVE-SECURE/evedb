package lv.odylab.evedb.domain.inv.blueprinttype;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;

@Caching
public class InvBlueprintTypeDao {
    private final ObjectifyFactory objectifyFactory;
    private final String dumpVersion;

    @Inject
    public InvBlueprintTypeDao(ObjectifyFactory objectifyFactory, @Named("dump.version") String dumpVersion) {
        this.objectifyFactory = objectifyFactory;
        this.dumpVersion = dumpVersion;
    }

    public InvBlueprintType getByTypeID(Long typeID) {
        InvBlueprintType invBlueprintType = objectifyFactory.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeID", typeID).get();
        if (invBlueprintType == null) {
            throw new IdNotFoundException(typeID);
        }
        return invBlueprintType;
    }

    public InvBlueprintType getByTypeName(String typeName) {
        InvBlueprintType invBlueprintType = objectifyFactory.begin().query(InvBlueprintType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("blueprintTypeName", typeName).get();
        if (invBlueprintType == null) {
            throw new NameNotFoundException(typeName);
        }
        return invBlueprintType;
    }
}
