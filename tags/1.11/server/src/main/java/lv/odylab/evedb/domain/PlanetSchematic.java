package lv.odylab.evedb.domain;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

import javax.persistence.Id;
import java.io.Serializable;

@Unindexed
public class PlanetSchematic implements Serializable {
    @Id
    private Long id;
    @Indexed
    private Long schematicTypeID;
    @Indexed
    private String schematicTypeName;
    private Long schematicGroupID;
    private String schematicGroupName;
    private Long schematicCategoryID;
    private String schematicCategoryName;
    private Long schematicIconID;
    private String schematicIcon;
    private String schematicName;
    private Long schematicCycleTime;
    private Long schematicQuantity;
    private Double schematicVolume;
    @Indexed
    private Long requiredTypeID;
    private String requiredTypeName;
    private Long requiredGroupID;
    private String requiredGroupName;
    private Long requiredCategoryID;
    private String requiredCategoryName;
    private Long requiredIconID;
    private String requiredIcon;
    private Long requiredQuantity;
    private Double requiredVolume;
    @Indexed
    private String dumpVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getSchematicCategoryID() {
        return schematicCategoryID;
    }

    public void setSchematicCategoryID(Long schematicCategoryID) {
        this.schematicCategoryID = schematicCategoryID;
    }

    public String getSchematicCategoryName() {
        return schematicCategoryName;
    }

    public void setSchematicCategoryName(String schematicCategoryName) {
        this.schematicCategoryName = schematicCategoryName;
    }

    public Long getSchematicIconID() {
        return schematicIconID;
    }

    public void setSchematicIconID(Long schematicIconID) {
        this.schematicIconID = schematicIconID;
    }

    public String getSchematicIcon() {
        return schematicIcon;
    }

    public void setSchematicIcon(String schematicIcon) {
        this.schematicIcon = schematicIcon;
    }

    public String getSchematicName() {
        return schematicName;
    }

    public void setSchematicName(String schematicName) {
        this.schematicName = schematicName;
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

    public Double getSchematicVolume() {
        return schematicVolume;
    }

    public void setSchematicVolume(Double schematicVolume) {
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

    public Long getRequiredCategoryID() {
        return requiredCategoryID;
    }

    public void setRequiredCategoryID(Long requiredCategoryID) {
        this.requiredCategoryID = requiredCategoryID;
    }

    public String getRequiredCategoryName() {
        return requiredCategoryName;
    }

    public void setRequiredCategoryName(String requiredCategoryName) {
        this.requiredCategoryName = requiredCategoryName;
    }

    public Long getRequiredIconID() {
        return requiredIconID;
    }

    public void setRequiredIconID(Long requiredIconID) {
        this.requiredIconID = requiredIconID;
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

    public Double getRequiredVolume() {
        return requiredVolume;
    }

    public void setRequiredVolume(Double requiredVolume) {
        this.requiredVolume = requiredVolume;
    }

    public String getDumpVersion() {
        return dumpVersion;
    }

    public void setDumpVersion(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }
}
