package lv.odylab.evedb.domain.inv.type;

import com.googlecode.objectify.ObjectifyService;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;
import lv.odylab.evedb.domain.TooShortPartialNameException;

import java.util.List;

public class InvTypeDao {

    static {
        ObjectifyService.register(InvType.class);
    }

    private final String dumpVersion;

    public InvTypeDao(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }

    public InvType getByTypeID(Long typeID) {
        InvType invType = ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).get();
        if (invType == null) {
            throw new IdNotFoundException(typeID);
        }
        return invType;
    }

    public InvType getByTypeName(String typeName) {
        InvType invType = ObjectifyService.begin().query(InvType.class)
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
        return ObjectifyService.begin().query(InvType.class)
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
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", 4L)
                .filter("typeName >=", partialTypeName)
                .filter("typeName <", partialTypeName + "\uFFFD")
                .order("typeName")
                .limit(limit).list();
    }

    public List<InvType> findBlueprintByPartialTypeName(String partialTypeName, Integer limit) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", 9L)
                .filter("typeName >=", partialTypeName)
                .filter("typeName <", partialTypeName + "\uFFFD")
                .order("typeName")
                .limit(limit).list();
    }
}
