package lv.odylab.evedb.application;

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
public class EveDbDtoMapperImpl implements EveDbDtoMapper {

    @Override
    public InvTypeMaterialDto map(InvTypeMaterial invTypeMaterial) {
        InvTypeMaterialDto invTypeMaterialDto = new InvTypeMaterialDto();
        invTypeMaterialDto.setMaterialTypeID(invTypeMaterial.getMaterialTypeID());
        invTypeMaterialDto.setMaterialTypeName(invTypeMaterial.getMaterialTypeName());
        invTypeMaterialDto.setMaterialTypeCategoryID(invTypeMaterial.getMaterialTypeCategoryID());
        invTypeMaterialDto.setMaterialTypeIcon(invTypeMaterial.getMaterialTypeIcon());
        invTypeMaterialDto.setQuantity(invTypeMaterial.getQuantity());
        return invTypeMaterialDto;
    }

    @Override
    public InvBlueprintTypeDto map(InvBlueprintType invBlueprintType) {
        InvBlueprintTypeDto invBlueprintTypeDto = new InvBlueprintTypeDto();
        invBlueprintTypeDto.setBlueprintTypeID(invBlueprintType.getBlueprintTypeID());
        invBlueprintTypeDto.setBlueprintTypeName(invBlueprintType.getBlueprintTypeName());
        invBlueprintTypeDto.setProductTypeID(invBlueprintType.getProductTypeID());
        invBlueprintTypeDto.setProductTypeName(invBlueprintType.getProductTypeName());
        invBlueprintTypeDto.setProductCategoryID(invBlueprintType.getProductCategoryID());
        invBlueprintTypeDto.setProductIcon(invBlueprintType.getProductTypeIcon());
        invBlueprintTypeDto.setTechLevel(invBlueprintType.getTechLevel());
        invBlueprintTypeDto.setProductionTime(invBlueprintType.getProductionTime());
        invBlueprintTypeDto.setResearchProductivityTime(invBlueprintType.getResearchProductivityTime());
        invBlueprintTypeDto.setResearchMaterialTime(invBlueprintType.getResearchMaterialTime());
        invBlueprintTypeDto.setResearchCopyTime(invBlueprintType.getResearchCopyTime());
        invBlueprintTypeDto.setResearchTechTime(invBlueprintType.getResearchTechTime());
        invBlueprintTypeDto.setProductivityModifier(invBlueprintType.getProductivityModifier());
        invBlueprintTypeDto.setWasteFactor(invBlueprintType.getWasteFactor());
        invBlueprintTypeDto.setMaxProductionLimit(invBlueprintType.getMaxProductionLimit());
        return invBlueprintTypeDto;
    }

    @Override
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

    @Override
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
        ramTypeRequirementDto.setDamagePerJob(String.valueOf(ramTypeRequirement.getDamagePerJob()));
        ramTypeRequirementDto.setQuantity(ramTypeRequirement.getQuantity());
        return ramTypeRequirementDto;
    }

    @Override
    public InvTypeBasicInfoDto map(InvType invType) {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setItemTypeID(invType.getTypeID());
        invTypeBasicInfoDto.setItemCategoryID(invType.getCategoryID());
        invTypeBasicInfoDto.setName(invType.getTypeName());
        invTypeBasicInfoDto.setIcon(invType.getTypeIcon());
        return invTypeBasicInfoDto;
    }
}