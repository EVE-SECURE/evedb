package lv.odylab.evedb.client.rpc.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "rowset")
public class XmlResultContainer<T> implements Serializable {
    private static final long serialVersionUID = -4377907135327072738L;

    private List<T> result;

    public XmlResultContainer() {
    }

    public XmlResultContainer(T resultObject) {
        result = new ArrayList<T>();
        result.add(resultObject);
    }

    public XmlResultContainer(List<T> resultObject) {
        result = resultObject;
    }

    @XmlElement(name = "row")
    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
