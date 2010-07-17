package lv.odylab.evedb.domain.ram.typerequirement;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;

import java.util.List;

public class RamTypeRequirementDao {
    private final ObjectifyFactory objectifyFactory;

    @Inject
    public RamTypeRequirementDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public List<RamTypeRequirement> getForTypeID(Long typeID, String dumpVersion) {
        return objectifyFactory.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).list();
    }

    public List<RamTypeRequirement> getForTypeName(String typeName, String dumpVersion) {
        return objectifyFactory.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName).list();
    }
}
