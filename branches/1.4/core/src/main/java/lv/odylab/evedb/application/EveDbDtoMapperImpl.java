package lv.odylab.evedb.application;

import com.google.inject.Singleton;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintType;
import lv.odylab.evedb.domain.inv.type.InvType;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;

@Singleton
public class EveDbDtoMapperImpl implements EveDbDtoMapper {

    @Override
    public InvTypeMaterialDto map(InvTypeMaterial invTypeMaterial) {
        InvTypeMaterialDto invTypeMaterialDto = new InvTypeMaterialDto();
        invTypeMaterialDto.setMaterialTypeID(invTypeMaterial.getMaterialTypeID());
        invTypeMaterialDto.setMaterialTypeName(invTypeMaterial.getMaterialTypeName());
        invTypeMaterialDto.setMaterialTypeCategoryID(invTypeMaterial.getMaterialTypeCategoryID());
        invTypeMaterialDto.setMaterialTypeGraphicIcon(invTypeMaterial.getMaterialTypeIcon());
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
        invBlueprintTypeDto.setProductGraphicIcon(invBlueprintType.getProductTypeIcon());
        invBlueprintTypeDto.setWasteFactor(invBlueprintType.getWasteFactor());
        invBlueprintTypeDto.setMaxProductionLimit(invBlueprintType.getMaxProductionLimit());
        return invBlueprintTypeDto;
    }

    @Override
    public RamTypeRequirementDto map(RamTypeRequirement ramTypeRequirement) {
        RamTypeRequirementDto ramTypeRequirementDto = new RamTypeRequirementDto();
        ramTypeRequirementDto.setRequiredTypeID(ramTypeRequirement.getRequiredTypeID());
        ramTypeRequirementDto.setRequiredTypeName(ramTypeRequirement.getRequiredTypeName());
        ramTypeRequirementDto.setRequiredTypeNameGraphicIcon(ramTypeRequirement.getRequiredTypeIcon());
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
        invTypeBasicInfoDto.setGraphicIcon(invType.getTypeIcon());
        return invTypeBasicInfoDto;
    }
}