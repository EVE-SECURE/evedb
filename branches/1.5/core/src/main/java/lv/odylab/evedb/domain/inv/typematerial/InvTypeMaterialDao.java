package lv.odylab.evedb.domain.inv.typematerial;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;

import java.util.List;

@Caching
public class InvTypeMaterialDao {
    private final ObjectifyFactory objectifyFactory;
    private final String dumpVersion;

    @Inject
    public InvTypeMaterialDao(ObjectifyFactory objectifyFactory, @Named("dump.version") String dumpVersion) {
        this.objectifyFactory = objectifyFactory;
        this.dumpVersion = dumpVersion;
    }

    public List<InvTypeMaterial> getForTypeID(Long typeID) {
        List<InvTypeMaterial> invTypeMaterials = objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("materialTypeID").list();
        if (invTypeMaterials.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return invTypeMaterials;
    }

    public List<InvTypeMaterial> getForTypeIdWithoutCheck(Long typeID) {
        return objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("materialTypeID").list();
    }

    public List<InvTypeMaterial> getForTypeName(String typeName) {
        List<InvTypeMaterial> invTypeMaterials = objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName)
                .order("materialTypeID").list();
        if (invTypeMaterials.isEmpty()) {
            throw new NameNotFoundException(typeName);
        }
        return invTypeMaterials;
    }
}
