package lv.odylab.evedb.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement
public class PlanetSchematicDto implements Serializable {
    private Long schematicTypeID;
    private String schematicTypeName;
    private Long schematicGroupID;
    private String schematicGroupName;
    private String schematicIcon;
    private Long schematicCycleTime;
    private Long schematicQuantity;
    private BigDecimal schematicVolume;
    private Long requiredTypeID;
    private String requiredTypeName;
    private Long requiredGroupID;
    private String requiredGroupName;
    private String requiredIcon;
    private Long requiredQuantity;
    private BigDecimal requiredVolume;

    public Long getSchematicTypeID() {
        return schematicTypeID;
    }

    public void setSchematicTypeID(Long schematicTypeID) {
        this.schematicTypeID = schematicTypeID;
    }

    public String getSchematicTypeName() {
        return schematicTypeName;
    }

    public void setSchematicTypeName(String schematicTypeName) {
        this.schematicTypeName = schematicTypeName;
    }

    public Long getSchematicGroupID() {
        return schematicGroupID;
    }

    public void setSchematicGroupID(Long schematicGroupID) {
        this.schematicGroupID = schematicGroupID;
    }

    public String getSchematicGroupName() {
        return schematicGroupName;
    }

    public void setSchematicGroupName(String schematicGroupName) {
        this.schematicGroupName = schematicGroupName;
    }

    public String getSchematicIcon() {
        return schematicIcon;
    }

    public void setSchematicIcon(String schematicIcon) {
        this.schematicIcon = schematicIcon;
    }

    public Long getSchematicCycleTime() {
        return schematicCycleTime;
    }

    public void setSchematicCycleTime(Long schematicCycleTime) {
        this.schematicCycleTime = schematicCycleTime;
    }

    public Long getSchematicQuantity() {
        return schematicQuantity;
    }

    public void setSchematicQuantity(Long schematicQuantity) {
        this.schematicQuantity = schematicQuantity;
    }

    public BigDecimal getSchematicVolume() {
        return schematicVolume;
    }

    public void setSchematicVolume(BigDecimal schematicVolume) {
        this.schematicVolume = schematicVolume;
    }

    public Long getRequiredTypeID() {
        return requiredTypeID;
    }

    public void setRequiredTypeID(Long requiredTypeID) {
        this.requiredTypeID = requiredTypeID;
    }

    public String getRequiredTypeName() {
        return requiredTypeName;
    }

    public void setRequiredTypeName(String requiredTypeName) {
        this.requiredTypeName = requiredTypeName;
    }

    public Long getRequiredGroupID() {
        return requiredGroupID;
    }

    public void setRequiredGroupID(Long requiredGroupID) {
        this.requiredGroupID = requiredGroupID;
    }

    public String getRequiredGroupName() {
        return requiredGroupName;
    }

    public void setRequiredGroupName(String requiredGroupName) {
        this.requiredGroupName = requiredGroupName;
    }

    public String getRequiredIcon() {
        return requiredIcon;
    }

    public void setRequiredIcon(String requiredIcon) {
        this.requiredIcon = requiredIcon;
    }

    public Long getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Long requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public BigDecimal getRequiredVolume() {
        return requiredVolume;
    }

    public void setRequiredVolume(BigDecimal requiredVolume) {
        this.requiredVolume = requiredVolume;
    }
}