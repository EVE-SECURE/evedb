package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class InvTypeMaterialDao {

    static {
        ObjectifyService.register(InvTypeMaterial.class);
    }

    public List<InvTypeMaterial> getForTypeID(Long typeID, String dumpVersion) {
        List<InvTypeMaterial> invTypeMaterials = ObjectifyService.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("materialTypeID").list();
        if (invTypeMaterials.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return invTypeMaterials;
    }

    public List<InvTypeMaterial> getForTypeIdWithoutCheck(Long typeID, String dumpVersion) {
        return ObjectifyService.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeID", typeID)
                .order("materialTypeID").list();
    }

    public List<InvTypeMaterial> getForTypeName(String typeName, String dumpVersion) {
        List<InvTypeMaterial> invTypeMaterials = ObjectifyService.begin().query(InvTypeMaterial.class)
                .filter("dumpVersion", dumpVersion)
                .filter("typeName", typeName)
                .order("materialTypeID").list();
        if (invTypeMaterials.isEmpty()) {
            throw new NameNotFoundException(typeName);
        }
        return invTypeMaterials;
    }
}
