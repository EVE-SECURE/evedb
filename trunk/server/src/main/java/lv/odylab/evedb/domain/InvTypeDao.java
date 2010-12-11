package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import java.util.ArrayList;
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
        List<String> words = getWords(partialTypeName);
        if (words.size() > 1) {
            Query<InvType> query = ObjectifyService.begin().query(InvType.class)
                    .filter("dumpVersion", dumpVersion)
                    .filter("published", Boolean.TRUE);
            for (String word : words) {
                query.filter("typeNameTokens", word.trim().toUpperCase());
            }
            return query.order("typeNameTokens")
                    .order("typeName")
                    .limit(lookupResultLimit).list();
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("typeNameTokens >=", partialTypeName.trim().toUpperCase())
                .filter("typeNameTokens <", partialTypeName.trim().toUpperCase() + "\uFFFD")
                .order("typeNameTokens")
                .order("typeName")
                .limit(lookupResultLimit).list();
    }

    public List<InvType> findResourceByPartialTypeName(String partialTypeName) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        List<String> words = getWords(partialTypeName);
        if (words.size() > 1) {
            Query<InvType> query = ObjectifyService.begin().query(InvType.class)
                    .filter("dumpVersion", dumpVersion)
                    .filter("published", Boolean.TRUE)
                    .filter("categoryID", 4L);
            for (String word : words) {
                query.filter("typeNameTokens", word.trim().toUpperCase());
            }
            return query.order("typeNameTokens")
                    .order("typeName")
                    .limit(lookupResultLimit).list();
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", 4L)
                .filter("typeNameTokens >=", partialTypeName.trim().toUpperCase())
                .filter("typeNameTokens <", partialTypeName.trim().toUpperCase() + "\uFFFD")
                .order("typeNameTokens")
                .order("typeName")
                .limit(lookupResultLimit).list();
    }

    public List<InvType> findBlueprintByPartialTypeName(String partialTypeName) {
        if (partialTypeName.length() < 3) {
            throw new TooShortPartialNameException(partialTypeName);
        }
        List<String> words = getWords(partialTypeName);
        if (words.size() > 1) {

            Query<InvType> query = ObjectifyService.begin().query(InvType.class)
                    .filter("dumpVersion", dumpVersion)
                    .filter("published", Boolean.TRUE)
                    .filter("categoryID", 9L);
            for (String word : words) {
                query.filter("typeNameTokens", word.trim().toUpperCase());
            }
            return query.order("typeNameTokens")
                    .order("typeName")
                    .limit(lookupResultLimit).list();
        }
        return ObjectifyService.begin().query(InvType.class)
                .filter("dumpVersion", dumpVersion)
                .filter("published", Boolean.TRUE)
                .filter("categoryID", 9L)
                .filter("typeNameTokens >=", partialTypeName.trim().toUpperCase())
                .filter("typeNameTokens <", partialTypeName.trim().toUpperCase() + "\uFFFD")
                .order("typeNameTokens")
                .order("typeName")
                .limit(lookupResultLimit).list();
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
}
