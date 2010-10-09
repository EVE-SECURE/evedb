package lv.odylab.evedb.domain.ram.typerequirement;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;

import java.util.List;

@Caching
public class RamTypeRequirementDao {
    private final ObjectifyFactory objectifyFactory;
    private final String dumpVersion;

    @Inject
    public RamTypeRequirementDao(ObjectifyFactory objectifyFactory, @Named("dump.version") String dumpVersion) {
        this.objectifyFactory = objectifyFactory;
        this.dumpVersion = dumpVersion;
    }

    public List<RamTypeRequirement> getForTypeID(Long typeID) {
        List<RamTypeRequirement> ramTypeRequirements = objectifyFactory.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
        if (ramTypeRequirements.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return ramTypeRequirements;
    }

    public List<RamTypeRequirement> getForTypeIdWithoutCheck(Long typeID) {
        return objectifyFactory.begin().query(RamTypeRequirement.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("requiredTypeID").list();
    }

    public List<RamTypeRequirement> getForTypeName(String typeName) {
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
