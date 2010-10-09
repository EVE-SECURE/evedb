package lv.odylab.evedb.application;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lv.odylab.evedb.client.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintType;
import lv.odylab.evedb.domain.inv.type.InvType;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;
import lv.odylab.evedb.service.BlueprintDetails;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class EveDbClientFacadeImpl implements EveDbClientFacade {
    private final EveDbApplicationFacade applicationFacade;
    private final EveDbDtoMapper mapper;

    @Inject
    public EveDbClientFacadeImpl(EveDbApplicationFacade applicationFacade, EveDbDtoMapper mapper) {
        this.applicationFacade = applicationFacade;
        this.mapper = mapper;
    }

    @Override
    public String clearCache() {
        return applicationFacade.clearCache();
    }

    @Override
    public List<InvTypeMaterialDto> getInvTypeMaterialsForTypeID(Long typeID) {
        List<InvTypeMaterial> invTypeMaterials = applicationFacade.getInvTypeMaterialsForTypeID(typeID);
        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>(invTypeMaterials.size());
        for (InvTypeMaterial invTypeMaterial : invTypeMaterials) {
            invTypeMaterialDtos.add(mapper.map(invTypeMaterial));
        }
        return invTypeMaterialDtos;
    }

    @Override
    public List<InvTypeMaterialDto> getInvTypeMaterialsForTypeName(String typeName) {
        List<InvTypeMaterial> invTypeMaterials = applicationFacade.getInvTypeMaterialsForTypeName(typeName);
        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>(invTypeMaterials.size());
        for (InvTypeMaterial invTypeMaterial : invTypeMaterials) {
            invTypeMaterialDtos.add(mapper.map(invTypeMaterial));
        }
        return invTypeMaterialDtos;
    }

    @Override
    public InvBlueprintTypeDto getBlueprintTypeByTypeID(Long typeID) {
        InvBlueprintType invBlueprintType = applicationFacade.getBlueprintTypeByTypeID(typeID);
        return invBlueprintType == null ? null : mapper.map(invBlueprintType);
    }

    @Override
    public InvBlueprintTypeDto getBlueprintTypeByTypeName(String typeName) {
        InvBlueprintType invBlueprintType = applicationFacade.getBlueprintTypeByTypeName(typeName);
        return invBlueprintType == null ? null : mapper.map(invBlueprintType);
    }

    @Override
    public BlueprintDetailsDto getBlueprintDetailsForTypeID(Long typeID) {
        BlueprintDetails blueprintDetails = applicationFacade.getBlueprintDetailsForTypeID(typeID);
        return blueprintDetails == null ? null : mapper.map(blueprintDetails);
    }

    @Override
    public BlueprintDetailsDto getBlueprintDetailsForTypeName(String typeName) {
        BlueprintDetails blueprintDetails = applicationFacade.getBlueprintDetailsForTypeName(typeName);
        return blueprintDetails == null ? null : mapper.map(blueprintDetails);
    }

    @Override
    public List<RamTypeRequirementDto> getRamTypeRequirementsForTypeID(Long typeID) {
        List<RamTypeRequirement> ramTypeRequirements = applicationFacade.getRamTypeRequirementsForTypeID(typeID);
        List<RamTypeRequirementDto> ramTypeRequirementDtos = new ArrayList<RamTypeRequirementDto>(ramTypeRequirements.size());
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            ramTypeRequirementDtos.add(mapper.map(ramTypeRequirement));
        }
        return ramTypeRequirementDtos;
    }

    @Override
    public List<RamTypeRequirementDto> getRamTypeRequirementsForTypeName(String typeName) {
        List<RamTypeRequirement> ramTypeRequirements = applicationFacade.getRamTypeRequirementsForTypeName(typeName);
        List<RamTypeRequirementDto> ramTypeRequirementDtos = new ArrayList<RamTypeRequirementDto>(ramTypeRequirements.size());
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            ramTypeRequirementDtos.add(mapper.map(ramTypeRequirement));
        }
        return ramTypeRequirementDtos;
    }

    @Override
    public List<InvTypeBasicInfoDto> findTypeByPartialTypeName(String partialTypeName) {
        List<InvType> invTypes = applicationFacade.findTypeByPartialTypeName(partialTypeName);
        List<InvTypeBasicInfoDto> invTypeLookupResultItemDtos = new ArrayList<InvTypeBasicInfoDto>(invTypes.size());
        for (InvType invType : invTypes) {
            invTypeLookupResultItemDtos.add(mapper.map(invType));
        }
        return invTypeLookupResultItemDtos;
    }

    @Override
    public List<InvTypeBasicInfoDto> findResourceTypeByPartialTypeName(String partialTypeName) {
        List<InvType> invTypes = applicationFacade.findResourceTypeByPartialTypeName(partialTypeName);
        List<InvTypeBasicInfoDto> invTypeLookupResultItemDtos = new ArrayList<InvTypeBasicInfoDto>(invTypes.size());
        for (InvType invType : invTypes) {
            invTypeLookupResultItemDtos.add(mapper.map(invType));
        }
        return invTypeLookupResultItemDtos;
    }

    @Override
    public List<InvTypeBasicInfoDto> findBlueprintTypeByPartialTypeName(String partialTypeName) {
        List<InvType> invTypes = applicationFacade.findBlueprintTypeByPartialTypeName(partialTypeName);
        List<InvTypeBasicInfoDto> invTypeLookupResultItemDtos = new ArrayList<InvTypeBasicInfoDto>(invTypes.size());
        for (InvType invType : invTypes) {
            invTypeLookupResultItemDtos.add(mapper.map(invType));
        }
        return invTypeLookupResultItemDtos;
    }

    @Override
    public InvTypeBasicInfoDto getTypeBasicInfoByTypeID(Long typeID) {
        InvType invType = applicationFacade.getTypeBasicInfoByTypeID(typeID);
        return invType != null ? mapper.map(invType) : null;
    }

    @Override
    public InvTypeBasicInfoDto getTypeBasicInfoByTypeName(String typeName) {
        InvType invType = applicationFacade.getTypeBasicInfoByTypeName(typeName);
        return invType != null ? mapper.map(invType) : null;
    }

    @Override
    public String getTypeNameByTypeID(Long typeID) {
        return applicationFacade.getTypeNameByTypeID(typeID);
    }

    @Override
    public Long getTypeIdByTypeName(String typeName) {
        return applicationFacade.getTypeIdByTypeName(typeName);
    }

    @Override
    public String getVersion() {
        return applicationFacade.getVersion();
    }
}