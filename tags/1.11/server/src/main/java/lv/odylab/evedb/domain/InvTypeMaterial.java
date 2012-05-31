package lv.odylab.evedb.domain;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

import javax.persistence.Id;
import java.io.Serializable;

@Unindexed
public class InvTypeMaterial implements Serializable {
    @Id
    private Long id;
    @Indexed
    private Long typeID;
    @Indexed
    private String typeName;
    private Long typeIconID;
    private String typeIcon;
    private Long typeGroupID;
    private String typeGroupName;
    private Long typeCategoryID;
    private String typeCategoryName;
    @Indexed
    private Long materialTypeID;
    private String materialTypeName;
    private Long materialTypeIconID;
    private String materialTypeIcon;
    private Long materialTypeGroupID;
    private String materialTypeGroupName;
    private Long materialTypeCategoryID;
    private String materialTypeCategoryName;
    private Double materialVolume;
    private Long quantity;
    @Indexed
    private String dumpVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeIconID() {
        return typeIconID;
    }

    public void setTypeIconID(Long typeIconID) {
        this.typeIconID = typeIconID;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public Long getTypeGroupID() {
        return typeGroupID;
    }

    public void setTypeGroupID(Long typeGroupID) {
        this.typeGroupID = typeGroupID;
    }

    public String getTypeGroupName() {
        return typeGroupName;
    }

    public void setTypeGroupName(String typeGroupName) {
        this.typeGroupName = typeGroupName;
    }

    public Long getTypeCategoryID() {
        return typeCategoryID;
    }

    public void setTypeCategoryID(Long typeCategoryID) {
        this.typeCategoryID = typeCategoryID;
    }

    public String getTypeCategoryName() {
        return typeCategoryName;
    }

    public void setTypeCategoryName(String typeCategoryName) {
        this.typeCategoryName = typeCategoryName;
    }

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

    public Long getMaterialTypeIconID() {
        return materialTypeIconID;
    }

    public void setMaterialTypeIconID(Long materialTypeIconID) {
        this.materialTypeIconID = materialTypeIconID;
    }

    public String getMaterialTypeIcon() {
        return materialTypeIcon;
    }

    public void setMaterialTypeIcon(String materialTypeIcon) {
        this.materialTypeIcon = materialTypeIcon;
    }

    public Long getMaterialTypeGroupID() {
        return materialTypeGroupID;
    }

    public void setMaterialTypeGroupID(Long materialTypeGroupID) {
        this.materialTypeGroupID = materialTypeGroupID;
    }

    public String getMaterialTypeGroupName() {
        return materialTypeGroupName;
    }

    public void setMaterialTypeGroupName(String materialTypeGroupName) {
        this.materialTypeGroupName = materialTypeGroupName;
    }

    public Long getMaterialTypeCategoryID() {
        return materialTypeCategoryID;
    }

    public void setMaterialTypeCategoryID(Long materialTypeCategoryID) {
        this.materialTypeCategoryID = materialTypeCategoryID;
    }

    public String getMaterialTypeCategoryName() {
        return materialTypeCategoryName;
    }

    public void setMaterialTypeCategoryName(String materialTypeCategoryName) {
        this.materialTypeCategoryName = materialTypeCategoryName;
    }

    public Double getMaterialVolume() {
        return materialVolume;
    }

    public void setMaterialVolume(Double materialVolume) {
        this.materialVolume = materialVolume;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDumpVersion() {
        return dumpVersion;
    }

    public void setDumpVersion(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }
}
