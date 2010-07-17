package lv.odylab.evedb.domain.inv.type;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.evedb.domain.TooShortPartialNameException;

import java.util.List;

public class InvTypeDao {
    private static final Long CATEGORY_MATERIAL = 4L;
    private static final Long CATEGORY_BLUEPRINT = 9L;

    private final ObjectifyFactory objectifyFactory;

    @Inject
    public InvTypeDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public InvType getByTypeID(Long typeID, String dumpVersion) {
        return objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).get();
    }

    public InvType getByTypeName(String typeName, String dumpVersion) {
        return objectifyFactory.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName).get();
    }

    public List<InvType> findByPartialTypeName(String partialTypeName, Integer limit, String dumpVersion) {
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

    public List<InvType> findResourceByPartialTypeName(String partialTypeName, Integer limit, String dumpVersion) {
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

    public List<InvType> findBlueprintByPartialTypeName(String partialTypeName, Integer limit, String dumpVersion) {
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
