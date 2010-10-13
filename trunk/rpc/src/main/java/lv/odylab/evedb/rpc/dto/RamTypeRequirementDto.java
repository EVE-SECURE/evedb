package lv.odylab.evedb.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class RamTypeRequirementDto implements Serializable {
    private static final long serialVersionUID = 1999371741294265927L;

    private Long activityID;
    private String activityName;
    private Long requiredTypeID;
    private String requiredTypeName;
    private Long requiredTypeCategoryID;
    private Long requiredTypeGroupID;
    private String requiredTypeGroupName;
    private String requiredTypeIcon;
    private Long quantity;
    private String damagePerJob;

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

    public Long getRequiredTypeCategoryID() {
        return requiredTypeCategoryID;
    }

    public void setRequiredTypeCategoryID(Long requiredTypeCategoryID) {
        this.requiredTypeCategoryID = requiredTypeCategoryID;
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

    public String getRequiredTypeIcon() {
        return requiredTypeIcon;
    }

    public void setRequiredTypeIcon(String requiredTypeIcon) {
        this.requiredTypeIcon = requiredTypeIcon;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDamagePerJob() {
        return damagePerJob;
    }

    public void setDamagePerJob(String damagePerJob) {
        this.damagePerJob = damagePerJob;
    }
}