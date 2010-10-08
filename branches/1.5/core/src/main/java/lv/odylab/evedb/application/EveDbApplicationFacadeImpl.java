package lv.odylab.evedb.application;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import lv.odylab.appengine.GoogleAppEngineServices;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintType;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.inv.type.InvType;
import lv.odylab.evedb.domain.inv.type.InvTypeDao;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterialDao;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirementDao;
import lv.odylab.evedb.service.BlueprintDetails;
import lv.odylab.evedb.service.BlueprintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class EveDbApplicationFacadeImpl implements EveDbApplicationFacade {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final GoogleAppEngineServices googleAppEngineServices;
    private final BlueprintService blueprintService;
    private final InvBlueprintTypeDao invBlueprintTypeDao;
    private final InvTypeDao invTypeDao;
    private final InvTypeMaterialDao invTypeMaterialDao;
    private final RamTypeRequirementDao ramTypeRequirementDao;
    private final Integer lookupLimit;
    private final String eveDbVersion;

    @Inject
    public EveDbApplicationFacadeImpl(GoogleAppEngineServices googleAppEngineServices, BlueprintService blueprintService, InvBlueprintTypeDao invBlueprintTypeDao, InvTypeDao invTypeDao, InvTypeMaterialDao invTypeMaterialDao, RamTypeRequirementDao ramTypeRequirementDao,
                                      @Named("lookupLimit") Integer lookupLimit,
                                      @Named("evedb.version") String eveDbVersion) {
        this.googleAppEngineServices = googleAppEngineServices;
        this.blueprintService = blueprintService;
        this.invBlueprintTypeDao = invBlueprintTypeDao;
        this.invTypeDao = invTypeDao;
        this.invTypeMaterialDao = invTypeMaterialDao;
        this.ramTypeRequirementDao = ramTypeRequirementDao;
        this.lookupLimit = lookupLimit;
        this.eveDbVersion = eveDbVersion;
    }

    @Override
    public String clearCache() {
        MemcacheService memcacheService = googleAppEngineServices.getMemcacheService();
        long itemCount = memcacheService.getStatistics().getItemCount();
        memcacheService.clearAll();
        logger.debug("Clearing Memcache. {} items cleared", itemCount);
        return "OK, " + itemCount;
    }

    @Override
    public List<InvTypeMaterial> getInvTypeMaterialsForTypeID(Long typeID) {
        return invTypeMaterialDao.getForTypeID(typeID);
    }

    @Override
    public List<InvTypeMaterial> getInvTypeMaterialsForTypeName(String typeName) {
        return invTypeMaterialDao.getForTypeName(typeName);
    }

    @Override
    public InvBlueprintType getBlueprintTypeByTypeID(Long typeID) {
        return invBlueprintTypeDao.getByTypeID(typeID);
    }

    @Override
    public InvBlueprintType getBlueprintTypeByTypeName(String typeName) {
        return invBlueprintTypeDao.getByTypeName(typeName);
    }

    @Override
    public BlueprintDetails getBlueprintDetailsForTypeID(Long typeID) {
        return blueprintService.getBlueprintDetailsForTypeID(typeID);
    }

    @Override
    public BlueprintDetails getBlueprintDetailsForTypeName(String typeName) {
        return blueprintService.getBlueprintDetailsForTypeName(typeName);
    }

    @Override
    public List<RamTypeRequirement> getRamTypeRequirementsForTypeID(Long typeID) {
        return ramTypeRequirementDao.getForTypeID(typeID);
    }

    @Override
    public List<RamTypeRequirement> getRamTypeRequirementsForTypeName(String typeName) {
        return ramTypeRequirementDao.getForTypeName(typeName);
    }

    @Override
    public List<InvType> findTypeByPartialTypeName(String partialTypeName) {
        return invTypeDao.findByPartialTypeName(partialTypeName, lookupLimit);
    }

    @Override
    public List<InvType> findResourceTypeByPartialTypeName(String partialTypeName) {
        return invTypeDao.findResourceByPartialTypeName(partialTypeName, lookupLimit);
    }

    @Override
    public List<InvType> findBlueprintTypeByPartialTypeName(String partialTypeName) {
        return invTypeDao.findBlueprintByPartialTypeName(partialTypeName, lookupLimit);
    }

    @Override
    public InvType getTypeBasicInfoByTypeID(Long typeID) {
        return invTypeDao.getByTypeID(typeID);
    }

    @Override
    public InvType getTypeBasicInfoByTypeName(String typeName) {
        return invTypeDao.getByTypeName(typeName);
    }

    @Override
    public String getTypeNameByTypeID(Long typeID) {
        InvType invType = invTypeDao.getByTypeID(typeID);
        return invType == null ? null : invType.getTypeName();
    }

    @Override
    public Long getTypeIdByTypeName(String typeName) {
        InvType invType = invTypeDao.getByTypeName(typeName);
        return invType == null ? null : invType.getTypeID();
    }

    @Override
    public String getVersion() {
        return eveDbVersion;
    }
}