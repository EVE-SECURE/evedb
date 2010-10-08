package lv.odylab.evedb.application;

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

public interface EveDbDtoMapper {

    InvTypeMaterialDto map(InvTypeMaterial invTypeMaterial);

    InvBlueprintTypeDto map(InvBlueprintType invBlueprintType);

    BlueprintDetailsDto map(BlueprintDetails blueprintDetails);

    RamTypeRequirementDto map(RamTypeRequirement ramTypeRequirement);

    InvTypeBasicInfoDto map(InvType invType);

}
