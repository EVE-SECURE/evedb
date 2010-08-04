package lv.odylab.evedb.domain;

import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintType;
import lv.odylab.evedb.domain.inv.type.InvType;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;

@Singleton
public class EveDbObjectifyFactory extends ObjectifyFactory {
    public EveDbObjectifyFactory() {
        register(InvBlueprintType.class);
        register(InvType.class);
        register(InvTypeMaterial.class);
        register(RamTypeRequirement.class);
    }
}
