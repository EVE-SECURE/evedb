package lv.odylab.evedb.domain;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Unindexed
public class InvType implements Serializable {
    @Id
    private Long id;
    @Indexed
    private Long typeID;
    @Indexed
    private String typeName;
    @Indexed
    private List<String> typeNameTokens;
    private Long typeIconID;
    private String typeIcon;
    private Long groupID;
    private String groupName;
    @Indexed
    private Long categoryID;
    private String categoryName;
    @Indexed
    private Integer metaLevel;
    @Indexed
    private Long parentTypeID;
    @Indexed
    private String parentTypeName;
    private Double mass;
    private Double volume;
    private Double capacity;
    private Long portionSize;
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

    public List<String> getTypeNameTokens() {
        return typeNameTokens;
    }

    public void setTypeNameTokens(List<String> typeNameTokens) {
        this.typeNameTokens = typeNameTokens;
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

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getMetaLevel() {
        return metaLevel;
    }

    public void setMetaLevel(Integer metaLevel) {
        this.metaLevel = metaLevel;
    }

    public Long getParentTypeID() {
        return parentTypeID;
    }

    public void setParentTypeID(Long parentTypeID) {
        this.parentTypeID = parentTypeID;
    }

    public String getParentTypeName() {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Long getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(Long portionSize) {
        this.portionSize = portionSize;
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
