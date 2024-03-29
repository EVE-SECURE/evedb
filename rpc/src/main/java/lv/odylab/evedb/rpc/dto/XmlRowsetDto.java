package lv.odylab.evedb.rpc.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "rowset")
public class XmlRowsetDto implements Serializable {
    private List result;

    public XmlRowsetDto() {
    }

    public XmlRowsetDto(List resultObject) {
        result = resultObject;
    }

    @XmlElement(name = "row")
    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
