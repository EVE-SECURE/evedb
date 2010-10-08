package lv.odylab.evedb.domain.inv.blueprinttype;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

import javax.persistence.Id;
import java.io.Serializable;

@Unindexed
public class InvBlueprintType implements Serializable {
    private static final long serialVersionUID = -1531364479615244907L;

    @Id
    private Long id;
    @Indexed
    private Long blueprintTypeID;
    @Indexed
    private String blueprintTypeName;
    private Long productTypeID;
    private Long productCategoryID;
    private String productTypeName;
    private Long productTypeIconID;
    private String productTypeIcon;
    private Integer productionTime;
    private Integer techLevel;
    private Integer researchProductivityTime;
    private Integer researchMaterialTime;
    private Integer researchCopyTime;
    private Integer researchTechTime;
    private Integer productivityModifier;
    private Integer materialModifier;
    private Integer wasteFactor;
    private Integer maxProductionLimit;
    @Indexed
    private Boolean published;
    @Indexed
    private String dumpVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlueprintTypeID() {
        return blueprintTypeID;
    }

    public void setBlueprintTypeID(Long blueprintTypeID) {
        this.blueprintTypeID = blueprintTypeID;
    }

    public String getBlueprintTypeName() {
        return blueprintTypeName;
    }

    public void setBlueprintTypeName(String blueprintTypeName) {
        this.blueprintTypeName = blueprintTypeName;
    }

    public Long getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(Long productTypeID) {
        this.productTypeID = productTypeID;
    }

    public Long getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Long productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Long getProductTypeIconID() {
        return productTypeIconID;
    }

    public void setProductTypeIconID(Long productTypeIconID) {
        this.productTypeIconID = productTypeIconID;
    }

    public String getProductTypeIcon() {
        return productTypeIcon;
    }

    public void setProductTypeIcon(String productTypeIcon) {
        this.productTypeIcon = productTypeIcon;
    }

    public Integer getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Integer productionTime) {
        this.productionTime = productionTime;
    }

    public Integer getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(Integer techLevel) {
        this.techLevel = techLevel;
    }

    public Integer getResearchProductivityTime() {
        return researchProductivityTime;
    }

    public void setResearchProductivityTime(Integer researchProductivityTime) {
        this.researchProductivityTime = researchProductivityTime;
    }

    public Integer getResearchMaterialTime() {
        return researchMaterialTime;
    }

    public void setResearchMaterialTime(Integer researchMaterialTime) {
        this.researchMaterialTime = researchMaterialTime;
    }

    public Integer getResearchCopyTime() {
        return researchCopyTime;
    }

    public void setResearchCopyTime(Integer researchCopyTime) {
        this.researchCopyTime = researchCopyTime;
    }

    public Integer getResearchTechTime() {
        return researchTechTime;
    }

    public void setResearchTechTime(Integer researchTechTime) {
        this.researchTechTime = researchTechTime;
    }

    public Integer getProductivityModifier() {
        return productivityModifier;
    }

    public void setProductivityModifier(Integer productivityModifier) {
        this.productivityModifier = productivityModifier;
    }

    public Integer getMaterialModifier() {
        return materialModifier;
    }

    public void setMaterialModifier(Integer materialModifier) {
        this.materialModifier = materialModifier;
    }

    public Integer getWasteFactor() {
        return wasteFactor;
    }

    public void setWasteFactor(Integer wasteFactor) {
        this.wasteFactor = wasteFactor;
    }

    public Integer getMaxProductionLimit() {
        return maxProductionLimit;
    }

    public void setMaxProductionLimit(Integer maxProductionLimit) {
        this.maxProductionLimit = maxProductionLimit;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getDumpVersion() {
        return dumpVersion;
    }

    public void setDumpVersion(String dumpVersion) {
        this.dumpVersion = dumpVersion;
    }
}
