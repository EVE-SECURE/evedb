package lv.odylab.evedb.domain;

import com.googlecode.objectify.annotation.Unindexed;

import javax.persistence.Embedded;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Unindexed
public class BlueprintDetails implements Serializable {
    @Id
    private Long id;
    @Embedded
    private InvBlueprintType blueprintType;
    @Embedded
    private List<InvTypeMaterial> materials = new ArrayList<InvTypeMaterial>();
    @Embedded
    private List<RamTypeRequirement> manufacturingRequirements = new ArrayList<RamTypeRequirement>();
    @Embedded
    private List<RamTypeRequirement> timeProductivityRequirements = new ArrayList<RamTypeRequirement>();
    @Embedded
    private List<RamTypeRequirement> materialProductivityRequirements = new ArrayList<RamTypeRequirement>();
    @Embedded
    private List<RamTypeRequirement> copyingRequirements = new ArrayList<RamTypeRequirement>();
    @Embedded
    private List<RamTypeRequirement> reverseEngineeringRequirements = new ArrayList<RamTypeRequirement>();
    @Embedded
    private List<RamTypeRequirement> inventionRequirements = new ArrayList<RamTypeRequirement>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
