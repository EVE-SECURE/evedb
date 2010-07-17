package lv.odylab.evedb.domain.inv.typematerial;

import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyFactory;

import java.util.List;

public class InvTypeMaterialDao {
    private final ObjectifyFactory objectifyFactory;

    @Inject
    public InvTypeMaterialDao(ObjectifyFactory objectifyFactory) {
        this.objectifyFactory = objectifyFactory;
    }

    public List<InvTypeMaterial> getForTypeID(Long typeID, String dumpVersion) {
        return objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID).list();
    }

    public List<InvTypeMaterial> getForTypeName(String typeName, String dumpVersion) {
        return objectifyFactory.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName).list();
    }
}
