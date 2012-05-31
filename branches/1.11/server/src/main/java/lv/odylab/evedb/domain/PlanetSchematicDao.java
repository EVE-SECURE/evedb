package lv.odylab.evedb.domain;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

public class PlanetSchematicDao {

    static {
        ObjectifyService.register(PlanetSchematic.class);
    }

    private final String dumpVersion;

    public PlanetSchematicDao(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }

    public List<PlanetSchematic> getForTypeID(Long typeID) {
        List<PlanetSchematic> planetSchematics = ObjectifyService.begin().query(PlanetSchematic.class)
                .filter("dumpVersion", dumpVersion)
                .filter("schematicTypeID", typeID)
                .order("requiredTypeID").list();
        if (planetSchematics.isEmpty()) {
            throw new IdNotFoundException(typeID);
        }
        return planetSchematics;
    }

    public List<PlanetSchematic> getForTypeName(String typeName) {
        List<PlanetSchematic> planetSchematics = ObjectifyService.begin().query(PlanetSchematic.class)
                .filter("dumpVersion", dumpVersion)
                .filter("schematicTypeName", typeName)
                .order("requiredTypeID").list();
        if (planetSchematics.isEmpty()) {
            throw new NameNotFoundException(typeName);
        }
        return planetSchematics;
    }
}
