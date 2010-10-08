package lv.odylab.evedb.ws;

import com.google.inject.servlet.ServletModule;
import lv.odylab.evedb.ws.resource.BaseMaterialsForTypeIdResource;
import lv.odylab.evedb.ws.resource.BaseMaterialsForTypeNameResource;
import lv.odylab.evedb.ws.resource.BlueprintDetailsForTypeIdResource;
import lv.odylab.evedb.ws.resource.BlueprintDetailsForTypeNameResource;
import lv.odylab.evedb.ws.resource.BlueprintTypeByTypeIdResource;
import lv.odylab.evedb.ws.resource.BlueprintTypeByTypeNameResource;
import lv.odylab.evedb.ws.resource.ExtraMaterialsForTypeIdResource;
import lv.odylab.evedb.ws.resource.ExtraMaterialsForTypeNameResource;
import lv.odylab.evedb.ws.resource.LookupBlueprintTypeResource;
import lv.odylab.evedb.ws.resource.LookupResourceTypeResource;
import lv.odylab.evedb.ws.resource.LookupTypeResource;
import lv.odylab.evedb.ws.resource.TypeBasicInfoByTypeIdResource;
import lv.odylab.evedb.ws.resource.TypeBasicInfoByTypeNameResource;
import lv.odylab.evedb.ws.resource.TypeIdToTypeNameResource;
import lv.odylab.evedb.ws.resource.TypeNameToTypeIdResource;
import lv.odylab.evedb.ws.resource.VersionResource;
import lv.odylab.evedb.ws.resource.admin.ClearCacheResource;

public class EveDbServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(EveDbWsFacade.class).to(EveDbWsFacadeImpl.class);

        serve("/admin/clearCache").with(ClearCacheResource.class);
        serve("/baseMaterialsForTypeID/*").with(BaseMaterialsForTypeIdResource.class);
        serve("/baseMaterialsForTypeName/*").with(BaseMaterialsForTypeNameResource.class);
        serve("/blueprintTypeByTypeID/*").with(BlueprintTypeByTypeIdResource.class);
        serve("/blueprintTypeByTypeName/*").with(BlueprintTypeByTypeNameResource.class);
        serve("/blueprintDetailsForTypeID/*").with(BlueprintDetailsForTypeIdResource.class);
        serve("/blueprintDetailsForTypeName/*").with(BlueprintDetailsForTypeNameResource.class);
        serve("/extraMaterialsForTypeID/*").with(ExtraMaterialsForTypeIdResource.class);
        serve("/extraMaterialsForTypeName/*").with(ExtraMaterialsForTypeNameResource.class);
        serve("/lookupBlueprintType/*").with(LookupBlueprintTypeResource.class);
        serve("/lookupResourceType/*").with(LookupResourceTypeResource.class);
        serve("/lookupType/*").with(LookupTypeResource.class);
        serve("/typeBasicInfoByTypeID/*").with(TypeBasicInfoByTypeIdResource.class);
        serve("/typeBasicInfoByTypeName/*").with(TypeBasicInfoByTypeNameResource.class);
        serve("/typeIdToTypeName/*").with(TypeIdToTypeNameResource.class);
        serve("/typeNameToTypeID/*").with(TypeNameToTypeIdResource.class);
        serve("/version").with(VersionResource.class);
        serve("/").with(VersionResource.class);
    }
}
