package lv.odylab.evedb.service;

import lv.odylab.evedb.domain.inv.blueprinttype.InvBlueprintType;
import lv.odylab.evedb.domain.inv.typematerial.InvTypeMaterial;
import lv.odylab.evedb.domain.ram.typerequirement.RamTypeRequirement;

import java.util.List;

public class BlueprintDetails {
    private InvBlueprintType blueprintType;
    private List<InvTypeMaterial> materials;
    private List<RamTypeRequirement> manufacturingRequirements;
    private List<RamTypeRequirement> timeProductivityRequirements;
    private List<RamTypeRequirement> materialProductivityRequirements;
    private List<RamTypeRequirement> copyingRequirements;
    private List<RamTypeRequirement> reverseEngineeringRequirements;
    private List<RamTypeRequirement> inventionRequirements;

    public InvBlueprintType getBlueprintType() {
        return blueprintType;
    }

    public void setBlueprintType(InvBlueprintType blueprintType) {
        this.blueprintType = blueprintType;
    }

    public List<InvTypeMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<InvTypeMaterial> materials) {
        this.materials = materials;
    }

    public List<RamTypeRequirement> getManufacturingRequirements() {
        return manufacturingRequirements;
    }

    public void setManufacturingRequirements(List<RamTypeRequirement> manufacturingRequirements) {
        this.manufacturingRequirements = manufacturingRequirements;
    }

    public List<RamTypeRequirement> getTimeProductivityRequirements() {
        return timeProductivityRequirements;
    }

    public void setTimeProductivityRequirements(List<RamTypeRequirement> timeProductivityRequirements) {
        this.timeProductivityRequirements = timeProductivityRequirements;
    }

    public List<RamTypeRequirement> getMaterialProductivityRequirements() {
        return materialProductivityRequirements;
    }

    public void setMaterialProductivityRequirements(List<RamTypeRequirement> materialProductivityRequirements) {
        this.materialProductivityRequirements = materialProductivityRequirements;
    }

    public List<RamTypeRequirement> getCopyingRequirements() {
        return copyingRequirements;
    }

    public void setCopyingRequirements(List<RamTypeRequirement> copyingRequirements) {
        this.copyingRequirements = copyingRequirements;
    }

    public List<RamTypeRequirement> getReverseEngineeringRequirements() {
        return reverseEngineeringRequirements;
    }

    public void setReverseEngineeringRequirements(List<RamTypeRequirement> reverseEngineeringRequirements) {
        this.reverseEngineeringRequirements = reverseEngineeringRequirements;
    }

    public List<RamTypeRequirement> getInventionRequirements() {
        return inventionRequirements;
    }

    public void setInventionRequirements(List<RamTypeRequirement> inventionRequirements) {
        this.inventionRequirements = inventionRequirements;
    }
}
