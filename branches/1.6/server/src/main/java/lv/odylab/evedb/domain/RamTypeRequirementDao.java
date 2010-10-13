package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class RamTypeRequirementDao {

    static {
        ObjectifyService.register(RamTypeRequirement.class);
    }

    public List<RamTypeRequirement> getForTypeID(Long typeID, String dumpVersion) {
        List<RamTypeRequirement> ramTypeRequirements = ObjectifyService.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
        if (ramTypeRequirements.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return ramTypeRequirements;
    }

    public List<RamTypeRequirement> getForTypeIdWithoutCheck(Long typeID, String dumpVersion) {
        return ObjectifyService.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
    }

    public List<RamTypeRequirement> getForTypeName(String typeName, String dumpVersion) {
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
