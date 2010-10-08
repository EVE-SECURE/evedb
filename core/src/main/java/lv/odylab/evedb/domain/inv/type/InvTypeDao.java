package lv.odylab.evedb.domain.inv.type;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;
import lv.odylab.evedb.domain.TooShortPartialNameException;

import java.util.List;

@Caching
public class InvTypeDao {
    private static final Long CATEGORY_MATERIAL = 4L;
    private static final Long CATEGORY_BLUEPRINT = 9L;

    private final ObjectifyFactory objectifyFactory;
    private final String dumpVersion;

    @Inject
    public InvTypeDao(ObjectifyFactory objectifyFactory, @Named("dump.version") String dumpVersion) {
        this.objectifyFactory = objectifyFactory;
        this.dumpVersion = dumpVersion;
    }

    public InvType getByTypeID(Long typeID) {
        InvType invType = objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).get();
        if (invType == null) {
            throw new IdNotFoundException(typeID);
        }
        return invType;
    }

    public InvType getByTypeName(String typeName) {
        InvType invType = objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName).get();
        if (invType == null) {
            throw new NameNotFoundException(typeName);
        }
        return invType;
    }

    public List<InvType> findByPartialTypeName(String partialTypeName, Integer limit) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("typeName >=", partialTypeName)
                .filter("typeName <", partialTypeName + "\uFFFD")
                .order("typeName")
                .limit(limit).list();
    }

    public List<InvType> findResourceByPartialTypeName(String partialTypeName, Integer limit) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", CATEGORY_MATERIAL)
                .filter("typeName >=", partialTypeName)
                .filter("typeName <", partialTypeName + "\uFFFD")
                .order("typeName")
                .limit(limit).list();
    }

    public List<InvType> findBlueprintByPartialTypeName(String partialTypeName, Integer limit) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", CATEGORY_BLUEPRINT)
                .filter("typeName >=", partialTypeName)
                .filter("typeName <", partialTypeName + "\uFFFD")
                .order("typeName")
                .limit(limit).list();
    }
}
