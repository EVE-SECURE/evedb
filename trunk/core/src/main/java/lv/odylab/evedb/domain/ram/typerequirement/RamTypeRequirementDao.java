package lv.odylab.evedb.domain.ram.typerequirement;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;

import java.util.List;

public class RamTypeRequirementDao {
    private final ObjectifyFactory objectifyFactory;

    @Inject
    public RamTypeRequirementDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public List<RamTypeRequirement> getForTypeID(Long typeID, String dumpVersion) {
        List<RamTypeRequirement> ramTypeRequirements = objectifyFactory.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
        if (ramTypeRequirements.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return ramTypeRequirements;
    }

    public List<RamTypeRequirement> getForTypeName(String typeName, String dumpVersion) {
        List<RamTypeRequirement> ramTypeRequirements = objectifyFactory.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName)
                .order("requiredTypeID").list();
        if (ramTypeRequirements.isEmpty()) {
            throw new NameNotFoundException(typeName);
        }
        return ramTypeRequirements;
    }
}
