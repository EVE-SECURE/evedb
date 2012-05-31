package lv.odylab.evedb.client.rpc.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class InvTypeBasicInfoDto implements Serializable {
    private static final long serialVersionUID = -2743012358611532079L;

    private Long itemTypeID;
    private Long itemCategoryID;
    private String name;
    private String graphicIcon;

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

    public String getGraphicIcon() {
        return graphicIcon;
    }

    public void setGraphicIcon(String graphicIcon) {
        this.graphicIcon = graphicIcon;
    }
}