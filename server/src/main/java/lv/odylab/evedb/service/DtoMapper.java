package lv.odylab.evedb.service;

import lv.odylab.evedb.domain.BlueprintDetails;
import lv.odylab.evedb.domain.InvBlueprintType;
import lv.odylab.evedb.domain.InvType;
import lv.odylab.evedb.domain.InvTypeMaterial;
import lv.odylab.evedb.domain.PlanetSchematic;
import lv.odylab.evedb.domain.RamTypeRequirement;
import lv.odylab.evedb.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.rpc.dto.PlanetSchematicDto;
import lv.odylab.evedb.rpc.dto.RamTypeRequirementDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DtoMapper {

    public InvTypeMaterialDto map(InvTypeMaterial invTypeMaterial) {
        InvTypeMaterialDto invTypeMaterialDto = new InvTypeMaterialDto();
        invTypeMaterialDto.setMaterialTypeID(invTypeMaterial.getMaterialTypeID());
        invTypeMaterialDto.setMaterialTypeName(invTypeMaterial.getMaterialTypeName());
        invTypeMaterialDto.setMaterialTypeCategoryID(invTypeMaterial.getMaterialTypeCategoryID());
        invTypeMaterialDto.setMaterialTypeIcon(invTypeMaterial.getMaterialTypeIcon());
        invTypeMaterialDto.setQuantity(invTypeMaterial.getQuantity());
        invTypeMaterialDto.setMaterialVolume(BigDecimal.valueOf(invTypeMaterial.getMaterialVolume()).toPlainString());
        return invTypeMaterialDto;
    }

    public InvBlueprintTypeDto map(InvBlueprintType invBlueprintType) {
        InvBlueprintTypeDto invBlueprintTypeDto = new InvBlueprintTypeDto();
        invBlueprintTypeDto.setBlueprintTypeID(invBlueprintType.getBlueprintTypeID());
        invBlueprintTypeDto.setBlueprintTypeName(invBlueprintType.getBlueprintTypeName());
        invBlueprintTypeDto.setProductTypeID(invBlueprintType.getProductTypeID());
        invBlueprintTypeDto.setProductTypeName(invBlueprintType.getProductTypeName());
        invBlueprintTypeDto.setProductCategoryID(invBlueprintType.getProductCategoryID());
        invBlueprintTypeDto.setProductIcon(invBlueprintType.getProductTypeIcon());
        invBlueprintTypeDto.setParentBlueprintTypeID(invBlueprintType.getParentBlueprintTypeID());
        invBlueprintTypeDto.setParentBlueprintTypeName(invBlueprintType.getParentBlueprintTypeName());
        invBlueprintTypeDto.setParentProductTypeID(invBlueprintType.getParentProductTypeID());
        invBlueprintTypeDto.setParentProductTypeName(invBlueprintType.getParentProductTypeName());
        invBlueprintTypeDto.setTechLevel(invBlueprintType.getTechLevel());
        invBlueprintTypeDto.setProductionTime(invBlueprintType.getProductionTime());
        invBlueprintTypeDto.setResearchProductivityTime(invBlueprintType.getResearchProductivityTime());
        invBlueprintTypeDto.setResearchMaterialTime(invBlueprintType.getResearchMaterialTime());
        invBlueprintTypeDto.setResearchCopyTime(invBlueprintType.getResearchCopyTime());
        invBlueprintTypeDto.setResearchTechTime(invBlueprintType.getResearchTechTime());
        invBlueprintTypeDto.setProductivityModifier(invBlueprintType.getProductivityModifier());
        invBlueprintTypeDto.setWasteFactor(invBlueprintType.getWasteFactor());
        invBlueprintTypeDto.setMaxProductionLimit(invBlueprintType.getMaxProductionLimit());
        invBlueprintTypeDto.setProductVolume(BigDecimal.valueOf(invBlueprintType.getProductVolume()).toPlainString());
        invBlueprintTypeDto.setProductPortionSize(invBlueprintType.getProductPortionSize());
        invBlueprintTypeDto.setDumpVersion(invBlueprintType.getDumpVersion());
        return invBlueprintTypeDto;
    }

    public BlueprintDetailsDto map(BlueprintDetails blueprintDetails) {
        BlueprintDetailsDto blueprintDetailsDto = new BlueprintDetailsDto();
        blueprintDetailsDto.setInvBlueprintTypeDto(map(blueprintDetails.getBlueprintType()));

        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>();
        for (InvTypeMaterial invTypeMaterial : blueprintDetails.getMaterials()) {
            invTypeMaterialDtos.add(map(invTypeMaterial));
        }
        blueprintDetailsDto.setMaterialDtos(invTypeMaterialDtos);

        List<RamTypeRequirementDto> manufacturingRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : blueprintDetails.getManufacturingRequirements()) {
            manufacturingRequirementDtos.add(map(ramTypeRequirement));
        }
        blueprintDetailsDto.setManufacturingRequirementDtos(manufacturingRequirementDtos);

        List<RamTypeRequirementDto> timeProductivityRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : blueprintDetails.getTimeProductivityRequirements()) {
            timeProductivityRequirementDtos.add(map(ramTypeRequirement));
        }
        blueprintDetailsDto.setTimeProductivityRequirementDtos(timeProductivityRequirementDtos);

        List<RamTypeRequirementDto> materialProductivityRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : blueprintDetails.getMaterialProductivityRequirements()) {
            materialProductivityRequirementDtos.add(map(ramTypeRequirement));
        }
        blueprintDetailsDto.setMaterialProductivityRequirementDtos(materialProductivityRequirementDtos);

        List<RamTypeRequirementDto> copyingRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : blueprintDetails.getCopyingRequirements()) {
            copyingRequirementDtos.add(map(ramTypeRequirement));
        }
        blueprintDetailsDto.setCopyingRequirementDtos(copyingRequirementDtos);

        List<RamTypeRequirementDto> reverseEngineeringRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : blueprintDetails.getReverseEngineeringRequirements()) {
            reverseEngineeringRequirementDtos.add(map(ramTypeRequirement));
        }
        blueprintDetailsDto.setReverseEngineeringRequirementDtos(reverseEngineeringRequirementDtos);

        List<RamTypeRequirementDto> inventionRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        for (RamTypeRequirement ramTypeRequirement : blueprintDetails.getInventionRequirements()) {
            inventionRequirementDtos.add(map(ramTypeRequirement));
        }
        blueprintDetailsDto.setInventionRequirementDtos(inventionRequirementDtos);
        return blueprintDetailsDto;
    }

    public RamTypeRequirementDto map(RamTypeRequirement ramTypeRequirement) {
        RamTypeRequirementDto ramTypeRequirementDto = new RamTypeRequirementDto();
        ramTypeRequirementDto.setRequiredTypeID(ramTypeRequirement.getRequiredTypeID());
        ramTypeRequirementDto.setRequiredTypeName(ramTypeRequirement.getRequiredTypeName());
        ramTypeRequirementDto.setRequiredTypeIcon(ramTypeRequirement.getRequiredTypeIcon());
        ramTypeRequirementDto.setRequiredTypeCategoryID(ramTypeRequirement.getRequiredTypeCategoryID());
        ramTypeRequirementDto.setRequiredTypeGroupID(ramTypeRequirement.getRequiredTypeGroupID());
        ramTypeRequirementDto.setRequiredTypeGroupName(ramTypeRequirement.getRequiredTypeGroupName());
        ramTypeRequirementDto.setActivityID(ramTypeRequirement.getActivityID());
        ramTypeRequirementDto.setActivityName(ramTypeRequirement.getActivityName());
        ramTypeRequirementDto.setDamagePerJob(BigDecimal.valueOf(ramTypeRequirement.getDamagePerJob()).toPlainString());
        ramTypeRequirementDto.setQuantity(ramTypeRequirement.getQuantity());
        ramTypeRequirementDto.setRequiredTypeVolume(BigDecimal.valueOf(ramTypeRequirement.getRequiredTypeVolume()).toPlainString());
        return ramTypeRequirementDto;
    }

    public InvTypeBasicInfoDto map(InvType invType) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setItemTypeID(invType.getTypeID());
        invTypeBasicInfoDto.setItemCategoryID(invType.getCategoryID());
        invTypeBasicInfoDto.setName(invType.getTypeName());
        invTypeBasicInfoDto.setIcon(invType.getTypeIcon());
        return invTypeBasicInfoDto;
    }

    public PlanetSchematicDto map(PlanetSchematic planetSchematic) {
        PlanetSchematicDto planetSchematicDto = new PlanetSchematicDto();
        planetSchematicDto.setSchematicTypeID(planetSchematic.getSchematicTypeID());
        planetSchematicDto.setSchematicTypeName(planetSchematic.getSchematicTypeName());
        planetSchematicDto.setSchematicGroupID(planetSchematic.getSchematicGroupID());
        planetSchematicDto.setSchematicGroupName(planetSchematic.getSchematicGroupName());
        planetSchematicDto.setSchematicIcon(planetSchematic.getSchematicIcon());
        planetSchematicDto.setSchematicCycleTime(planetSchematic.getSchematicCycleTime());
        planetSchematicDto.setSchematicQuantity(planetSchematic.getSchematicQuantity());
        planetSchematicDto.setSchematicVolume(BigDecimal.valueOf(planetSchematic.getSchematicVolume()).toPlainString());
        planetSchematicDto.setRequiredTypeID(planetSchematic.getRequiredTypeID());
        planetSchematicDto.setRequiredTypeName(planetSchematic.getRequiredTypeName());
        planetSchematicDto.setRequiredGroupID(planetSchematic.getRequiredGroupID());
        planetSchematicDto.setRequiredGroupName(planetSchematic.getRequiredGroupName());
        planetSchematicDto.setRequiredIcon(planetSchematic.getRequiredIcon());
        planetSchematicDto.setRequiredQuantity(planetSchematic.getRequiredQuantity());
        planetSchematicDto.setRequiredVolume(BigDecimal.valueOf(planetSchematic.getRequiredVolume()).toPlainString());
        return planetSchematicDto;
    }
}