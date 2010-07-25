package lv.odylab.evedb.domain.inv.typematerial;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;

import java.util.List;

public class InvTypeMaterialDao {
    private final ObjectifyFactory objectifyFactory;

    @Inject
    public InvTypeMaterialDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public List<InvTypeMaterial> getForTypeID(Long typeID, String dumpVersion) {
        List<InvTypeMaterial> invTypeMaterials = objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).list();
        if (invTypeMaterials.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return invTypeMaterials;
    }

    public List<InvTypeMaterial> getForTypeName(String typeName, String dumpVersion) {
        List<InvTypeMaterial> invTypeMaterials = objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName).list();
        if (invTypeMaterials.isEmpty()) {
            throw new NameNotFoundException(typeName);
        }
        return invTypeMaterials;
    }
}
