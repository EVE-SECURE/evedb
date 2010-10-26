package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class InvTypeDao {

    static {
        ObjectifyService.register(InvType.class);
    }

    public InvType getByTypeID(Long typeID, String dumpVersion) {
        InvType invType = ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).get();
        if (invType == null) {
            throw new IdNotFoundException(typeID);
        }
        return invType;
    }

    public InvType getByTypeName(String typeName, String dumpVersion) {
        InvType invType = ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName).get();
        if (invType == null) {
            throw new NameNotFoundException(typeName);
        }
        return invType;
    }

    public List<InvType> findByPartialTypeName(String partialTypeName, Integer limit, String dumpVersion) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("typeNameTokens >=", partialTypeName.toUpperCase())
                .filter("typeNameTokens <", partialTypeName.toUpperCase() + "\uFFFD")
                .order("typeNameTokens")
                .limit(limit).list();
    }

    public List<InvType> findResourceByPartialTypeName(String partialTypeName, Integer limit, String dumpVersion) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", 4L)
                .filter("typeNameTokens >=", partialTypeName.toUpperCase())
                .filter("typeNameTokens <", partialTypeName.toUpperCase() + "\uFFFD")
                .order("typeNameTokens")
                .limit(limit).list();
    }

    public List<InvType> findBlueprintByPartialTypeName(String partialTypeName, Integer limit, String dumpVersion) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", 9L)
                .filter("typeNameTokens >=", partialTypeName.toUpperCase())
                .filter("typeNameTokens <", partialTypeName.toUpperCase() + "\uFFFD")
                .order("typeNameTokens")
                .limit(limit).list();
    }
}
