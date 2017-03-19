import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class for modeling AssetofPortfolio
 */
@XmlRootElement
public class AssetofPortfolio {
    @XmlJavaTypeAdapter(CDataAdapter.class)
    @XmlElement
    private String code;
    @XmlJavaTypeAdapter(CDataAdapter.class)
    @XmlElement
    private String value;

    public AssetofPortfolio() {
    }

    public AssetofPortfolio(String[] string) {
        code = string[0].trim();
        value = string[1].trim();
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
