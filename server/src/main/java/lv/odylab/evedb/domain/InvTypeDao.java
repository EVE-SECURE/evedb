package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvTypeDao {

    static {
        ObjectifyService.register(InvType.class);
    }

    private final String dumpVersion;
    private final Integer lookupResultLimit;

    public InvTypeDao(String dumpVersion, Integer lookupResultLimit) {
        this.dumpVersion = dumpVersion;
        this.lookupResultLimit = lookupResultLimit;
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

    public List<InvType> findByPartialTypeName(String partialTypeName) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        Query<InvType> query = createQuery(partialTypeName);
        List<InvType> result = query.limit(lookupResultLimit).list();
        Collections.sort(result, new InvTypeNameComparator());
        return result;
    }

    public List<InvType> findResourceByPartialTypeName(String partialTypeName) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        Query<InvType> query = createQuery(partialTypeName);
        query.filter("categoryID", 4L);
        List<InvType> result = query.limit(lookupResultLimit).list();
        Collections.sort(result, new InvTypeNameComparator());
        return result;
    }

    public List<InvType> findBlueprintByPartialTypeName(String partialTypeName) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        Query<InvType> query = createQuery(partialTypeName);
        query.filter("categoryID", 9L);
        List<InvType> result = query.limit(lookupResultLimit).list();
        Collections.sort(result, new InvTypeNameComparator());
        return result;
    }

    private Query<InvType> createQuery(String partialTypeName) {
        List<String> words = getWords(partialTypeName);
        if (words.size() > 1) {
            Query<InvType> query = ObjectifyService.begin().query(InvType.class)
                    .filter("dumpVersion", dumpVersion)
                    .filter("published", Boolean.TRUE);
            for (String word : words) {
                query.filter("typeNameTokens", word.trim().toUpperCase());
            }
            return query;
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("typeNameTokens >=", partialTypeName.trim().toUpperCase())
                .filter("typeNameTokens <", partialTypeName.trim().toUpperCase() + "\uFFFD");
    }

    private List<String> getWords(String inputString) {
        String[] words = inputString.split(" ");
        List<String> result = new ArrayList<String>();
        for (String word : words) {
            if (word != null && word.trim().length() > 0) {
                result.add(word);
            }
        }
        return result;
    }

    public List<InvType> getBaseItemsForTypeID(Long typeID) {
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("parentTypeID", typeID).list();
    }

    public List<InvType> getBaseItemsForTypeName(String typeName) {
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("parentTypeName", typeName).list();
    }
}
