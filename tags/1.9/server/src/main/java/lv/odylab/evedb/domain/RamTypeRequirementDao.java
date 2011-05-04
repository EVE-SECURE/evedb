package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class RamTypeRequirementDao {

    static {
        ObjectifyService.register(RamTypeRequirement.class);
    }

    private final String dumpVersion;

    public RamTypeRequirementDao(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }

    public List<RamTypeRequirement> getForTypeID(Long typeID) {
        List<RamTypeRequirement> ramTypeRequirements = ObjectifyService.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
        if (ramTypeRequirements.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return ramTypeRequirements;
    }

    public List<RamTypeRequirement> getForTypeIdWithoutCheck(Long typeID) {
        return ObjectifyService.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
    }

    public List<RamTypeRequirement> getForTypeName(String typeName) {
        List<RamTypeRequirement> ramTypeRequirements = ObjectifyService.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName)
                .order("requiredTypeID").list();
        if (ramTypeRequirements.isEmpty()) {
            throw new NameNotFoundException(typeName);
        }
        return ramTypeRequirements;
    }
}
