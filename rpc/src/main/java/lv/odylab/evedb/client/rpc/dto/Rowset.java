package lv.odylab.evedb.client.rpc.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "rowset")
public class Rowset implements Serializable {
    private static final long serialVersionUID = -4433970127027146955L;

    private List result;

    public Rowset() {
    }

    public Rowset(List resultObject) {
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
