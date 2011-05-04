package lv.odylab.evedb.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class InvTypeMaterialDto implements Serializable {
    private Long materialTypeID;
    private String materialTypeName;
    private Long materialTypeCategoryID;
    private String materialTypeIcon;
    private String materialVolume;
    private Long quantity;

    public Long getMaterialTypeID() {
        return materialTypeID;
    }

    public void setMaterialTypeID(Long materialTypeID) {
        this.materialTypeID = materialTypeID;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public Long getMaterialTypeCategoryID() {
        return materialTypeCategoryID;
    }

    public void setMaterialTypeCategoryID(Long materialTypeCategoryID) {
        this.materialTypeCategoryID = materialTypeCategoryID;
    }

    public String getMaterialTypeIcon() {
        return materialTypeIcon;
    }

    public void setMaterialTypeIcon(String materialTypeIcon) {
        this.materialTypeIcon = materialTypeIcon;
    }

    public String getMaterialVolume() {
        return materialVolume;
    }

    public void setMaterialVolume(String materialVolume) {
        this.materialVolume = materialVolume;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}