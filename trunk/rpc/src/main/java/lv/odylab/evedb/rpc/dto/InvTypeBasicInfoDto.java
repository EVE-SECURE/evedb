package lv.odylab.evedb.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class InvTypeBasicInfoDto implements Serializable {
    private Long itemTypeID;
    private Long itemCategoryID;
    private String name;
    private String icon;
    private Integer metaLevel;

    public Long getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(Long itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public Long getItemCategoryID() {
        return itemCategoryID;
    }

    public void setItemCategoryID(Long itemCategoryID) {
        this.itemCategoryID = itemCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMetaLevel() {
        return metaLevel;
    }

    public void setMetaLevel(Integer metaLevel) {
        this.metaLevel = metaLevel;
    }
}
