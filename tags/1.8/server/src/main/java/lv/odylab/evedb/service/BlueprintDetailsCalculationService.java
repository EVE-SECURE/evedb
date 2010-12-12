package lv.odylab.evedb.service;

import lv.odylab.evedb.domain.BlueprintDetails;
import lv.odylab.evedb.domain.InvBlueprintType;
import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.InvTypeMaterial;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.domain.RamTypeRequirement;
import lv.odylab.evedb.domain.RamTypeRequirementDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlueprintDetailsCalculationService {
    private final InvBlueprintTypeDao invBlueprintTypeDao;
    private final InvTypeMaterialDao invTypeMaterialDao;
    private final RamTypeRequirementDao ramTypeRequirementDao;

    public BlueprintDetailsCalculationService(InvBlueprintTypeDao invBlueprintTypeDao, InvTypeMaterialDao invTypeMaterialDao, RamTypeRequirementDao ramTypeRequirementDao) {
        this.invBlueprintTypeDao = invBlueprintTypeDao;
        this.invTypeMaterialDao = invTypeMaterialDao;
        this.ramTypeRequirementDao = ramTypeRequirementDao;
    }

    public BlueprintDetails getBlueprintDetailsForTypeID(Long typeID) {
        InvBlueprintType invBlueprintType = invBlueprintTypeDao.getByTypeID(typeID);
        List<InvTypeMaterial> invTypeMaterials = invTypeMaterialDao.getForTypeIdWithoutCheck(invBlueprintType.getProductTypeID());
        List<RamTypeRequirement> ramTypeRequirements = ramTypeRequirementDao.getForTypeIdWithoutCheck(typeID);
        return createBlueprintDetails(invBlueprintType, invTypeMaterials, ramTypeRequirements);
    }

    public BlueprintDetails getBlueprintDetailsForTypeName(String typeName) {
        InvBlueprintType invBlueprintType = invBlueprintTypeDao.getByTypeName(typeName);
        List<InvTypeMaterial> invTypeMaterials = invTypeMaterialDao.getForTypeIdWithoutCheck(invBlueprintType.getProductTypeID());
        List<RamTypeRequirement> ramTypeRequirements = ramTypeRequirementDao.getForTypeIdWithoutCheck(invBlueprintType.getBlueprintTypeID());
        return createBlueprintDetails(invBlueprintType, invTypeMaterials, ramTypeRequirements);
    }

    private BlueprintDetails createBlueprintDetails(InvBlueprintType invBlueprintType, List<InvTypeMaterial> invTypeMaterials, List<RamTypeRequirement> ramTypeRequirements) {
        List<RamTypeRequirement> manufacturingRequirements = new ArrayList<RamTypeRequirement>();
        List<RamTypeRequirement> timeProductivityRequirements = new ArrayList<RamTypeRequirement>();
        List<RamTypeRequirement> materialProductivityRequirements = new ArrayList<RamTypeRequirement>();
        List<RamTypeRequirement> copyingRequirements = new ArrayList<RamTypeRequirement>();
        List<RamTypeRequirement> reverseEngineeringRequirements = new ArrayList<RamTypeRequirement>();
        List<RamTypeRequirement> inventionRequirements = new ArrayList<RamTypeRequirement>();
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            if ("Manufacturing".equals(ramTypeRequirement.getActivityName())) {
                manufacturingRequirements.add(ramTypeRequirement);
            } else if ("Researching Time Productivity".equals(ramTypeRequirement.getActivityName())) {
                timeProductivityRequirements.add(ramTypeRequirement);
            } else if ("Researching Material Productivity".equals(ramTypeRequirement.getActivityName())) {
                materialProductivityRequirements.add(ramTypeRequirement);
            } else if ("Copying".equals(ramTypeRequirement.getActivityName())) {
                copyingRequirements.add(ramTypeRequirement);
            } else if ("Reverse Engineering".equals(ramTypeRequirement.getActivityName())) {
                reverseEngineeringRequirements.add(ramTypeRequirement);
            } else if ("Invention".equals(ramTypeRequirement.getActivityName())) {
                inventionRequirements.add(ramTypeRequirement);
            }
        }

        List<RamTypeRequirement> recyclableRequirements = getRecyclableRequirements(manufacturingRequirements);
        subtractRecycledMaterials(invTypeMaterials, recyclableRequirements);

        BlueprintDetails blueprintDetails = new BlueprintDetails();
        blueprintDetails.setBlueprintType(invBlueprintType);
        blueprintDetails.setMaterials(invTypeMaterials);
        blueprintDetails.setManufacturingRequirements(manufacturingRequirements);
        blueprintDetails.setTimeProductivityRequirements(timeProductivityRequirements);
        blueprintDetails.setMaterialProductivityRequirements(materialProductivityRequirements);
        blueprintDetails.setCopyingRequirements(copyingRequirements);
        blueprintDetails.setReverseEngineeringRequirements(reverseEngineeringRequirements);
        blueprintDetails.setInventionRequirements(inventionRequirements);
        return blueprintDetails;
    }

    private List<RamTypeRequirement> getRecyclableRequirements(List<RamTypeRequirement> ramTypeRequirements) {
        List<RamTypeRequirement> recycledMaterials = new ArrayList<RamTypeRequirement>();
        for (RamTypeRequirement ramTypeRequirement : ramTypeRequirements) {
            if (ramTypeRequirement.isRecycle()) {
                recycledMaterials.add(ramTypeRequirement);
            }
        }
        return recycledMaterials;
    }

    private void subtractRecycledMaterials(List<InvTypeMaterial> materials, List<RamTypeRequirement> recyclableRequirements) {
        Map<Long, InvTypeMaterial> typeIdToInvTypeMaterialMap = new HashMap<Long, InvTypeMaterial>();
        for (InvTypeMaterial invTypeMaterial : materials) {
            typeIdToInvTypeMaterialMap.put(invTypeMaterial.getMaterialTypeID(), invTypeMaterial);
        }

        for (RamTypeRequirement ramTypeRequirement : recyclableRequirements) {
            List<InvTypeMaterial> invTypeMaterials = invTypeMaterialDao.getForTypeIdWithoutCheck(ramTypeRequirement.getRequiredTypeID());
            for (InvTypeMaterial invTypeMaterial : invTypeMaterials) {
                InvTypeMaterial existingInvTypeMaterial = typeIdToInvTypeMaterialMap.get(invTypeMaterial.getMaterialTypeID());
                if (existingInvTypeMaterial != null) {
                    Long existingQuantity = existingInvTypeMaterial.getQuantity();
                    Long quantity = invTypeMaterial.getQuantity() * ramTypeRequirement.getQuantity();
                    if (existingQuantity - quantity > 0) {
                        existingInvTypeMaterial.setQuantity(existingQuantity - quantity);
                    } else {
                        typeIdToInvTypeMaterialMap.remove(invTypeMaterial.getMaterialTypeID());
                        materials.remove(existingInvTypeMaterial);
                    }
                }
            }
        }
    }
}
