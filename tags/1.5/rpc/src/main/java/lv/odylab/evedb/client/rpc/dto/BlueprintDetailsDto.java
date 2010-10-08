package lv.odylab.evedb.client.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class BlueprintDetailsDto implements Serializable {
    private static final long serialVersionUID = 3874089120723899281L;

    private InvBlueprintTypeDto invBlueprintTypeDto;
    private List<InvTypeMaterialDto> materialDtos;
    private List<RamTypeRequirementDto> manufacturingRequirementDtos;
    private List<RamTypeRequirementDto> timeProductivityRequirementDtos;
    private List<RamTypeRequirementDto> materialProductivityRequirementDtos;
    private List<RamTypeRequirementDto> copyingRequirementDtos;
    private List<RamTypeRequirementDto> reverseEngineeringRequirementDtos;
    private List<RamTypeRequirementDto> inventionRequirementDtos;

    public InvBlueprintTypeDto getInvBlueprintTypeDto() {
        return invBlueprintTypeDto;
    }

    public void setInvBlueprintTypeDto(InvBlueprintTypeDto invBlueprintTypeDto) {
        this.invBlueprintTypeDto = invBlueprintTypeDto;
    }

    public List<InvTypeMaterialDto> getMaterialDtos() {
        return materialDtos;
    }

    public void setMaterialDtos(List<InvTypeMaterialDto> materialDtos) {
        this.materialDtos = materialDtos;
    }

    public List<RamTypeRequirementDto> getManufacturingRequirementDtos() {
        return manufacturingRequirementDtos;
    }

    public void setManufacturingRequirementDtos(List<RamTypeRequirementDto> manufacturingRequirementDtos) {
        this.manufacturingRequirementDtos = manufacturingRequirementDtos;
    }

    public List<RamTypeRequirementDto> getTimeProductivityRequirementDtos() {
        return timeProductivityRequirementDtos;
    }

    public void setTimeProductivityRequirementDtos(List<RamTypeRequirementDto> timeProductivityRequirementDtos) {
        this.timeProductivityRequirementDtos = timeProductivityRequirementDtos;
    }

    public List<RamTypeRequirementDto> getMaterialProductivityRequirementDtos() {
        return materialProductivityRequirementDtos;
    }

    public void setMaterialProductivityRequirementDtos(List<RamTypeRequirementDto> materialProductivityRequirementDtos) {
        this.materialProductivityRequirementDtos = materialProductivityRequirementDtos;
    }

    public List<RamTypeRequirementDto> getCopyingRequirementDtos() {
        return copyingRequirementDtos;
    }

    public void setCopyingRequirementDtos(List<RamTypeRequirementDto> copyingRequirementDtos) {
        this.copyingRequirementDtos = copyingRequirementDtos;
    }

    public List<RamTypeRequirementDto> getReverseEngineeringRequirementDtos() {
        return reverseEngineeringRequirementDtos;
    }

    public void setReverseEngineeringRequirementDtos(List<RamTypeRequirementDto> reverseEngineeringRequirementDtos) {
        this.reverseEngineeringRequirementDtos = reverseEngineeringRequirementDtos;
    }

    public List<RamTypeRequirementDto> getInventionRequirementDtos() {
        return inventionRequirementDtos;
    }

    public void setInventionRequirementDtos(List<RamTypeRequirementDto> inventionRequirementDtos) {
        this.inventionRequirementDtos = inventionRequirementDtos;
    }
}
