package lv.odylab.evedb.client.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class InvBlueprintTypeDto implements Serializable {
    private static final long serialVersionUID = 4979702377892792598L;

    private Long blueprintTypeID;
    private String blueprintTypeName;
    private Long productTypeID;
    private String productTypeName;
    private Long productCategoryID;
    private String productGraphicIcon;
    private Integer wasteFactor;
    private Integer maxProductionLimit;

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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Long getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Long productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductGraphicIcon() {
        return productGraphicIcon;
    }

    public void setProductGraphicIcon(String productGraphicIcon) {
        this.productGraphicIcon = productGraphicIcon;
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
}
