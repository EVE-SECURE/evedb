package lv.odylab.evedb.domain.ram.typerequirement;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

import javax.persistence.Id;
import java.io.Serializable;

@Unindexed
public class RamTypeRequirement implements Serializable {
    private static final long serialVersionUID = 3554002802876121626L;

    @Id
    private Long id;
    @Indexed
    private Long typeID;
    @Indexed
    private String typeName;
    private Long activityID;
    private String activityName;
    private Long typeGraphicID;
    private String typeIcon;
    private Long typeGroupID;
    private String typeGroupName;
    private Long typeCategoryID;
    private String typeCategoryName;
    private Long requiredTypeID;
    private String requiredTypeName;
    private Long requiredTypeGraphicID;
    private String requiredTypeIcon;
    private Long requiredTypeGroupID;
    private String requiredTypeGroupName;
    private Long requiredTypeCategoryID;
    private String requiredTypeCategoryName;
    private Long quantity;
    private Double damagePerJob;
    private Boolean recycle;
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

    public Long getActivityID() {
        return activityID;
    }

    public void setActivityID(Long activityID) {
        this.activityID = activityID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getTypeGraphicID() {
        return typeGraphicID;
    }

    public void setTypeGraphicID(Long typeGraphicID) {
        this.typeGraphicID = typeGraphicID;
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

    public Long getRequiredTypeGraphicID() {
        return requiredTypeGraphicID;
    }

    public void setRequiredTypeGraphicID(Long requiredTypeGraphicID) {
        this.requiredTypeGraphicID = requiredTypeGraphicID;
    }

    public String getRequiredTypeIcon() {
        return requiredTypeIcon;
    }

    public void setRequiredTypeIcon(String requiredTypeIcon) {
        this.requiredTypeIcon = requiredTypeIcon;
    }

    public Long getRequiredTypeGroupID() {
        return requiredTypeGroupID;
    }

    public void setRequiredTypeGroupID(Long requiredTypeGroupID) {
        this.requiredTypeGroupID = requiredTypeGroupID;
    }

    public String getRequiredTypeGroupName() {
        return requiredTypeGroupName;
    }

    public void setRequiredTypeGroupName(String requiredTypeGroupName) {
        this.requiredTypeGroupName = requiredTypeGroupName;
    }

    public Long getRequiredTypeCategoryID() {
        return requiredTypeCategoryID;
    }

    public void setRequiredTypeCategoryID(Long requiredTypeCategoryID) {
        this.requiredTypeCategoryID = requiredTypeCategoryID;
    }

    public String getRequiredTypeCategoryName() {
        return requiredTypeCategoryName;
    }

    public void setRequiredTypeCategoryName(String requiredTypeCategoryName) {
        this.requiredTypeCategoryName = requiredTypeCategoryName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getDamagePerJob() {
        return damagePerJob;
    }

    public void setDamagePerJob(Double damagePerJob) {
        this.damagePerJob = damagePerJob;
    }

    public Boolean getRecycle() {
        return recycle;
    }

    public void setRecycle(Boolean recycle) {
        this.recycle = recycle;
    }

    public String getDumpVersion() {
        return dumpVersion;
    }

    public void setDumpVersion(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }
}
