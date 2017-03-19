import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class  for modeling Portfolio and <br>
 * providing a factory method to parse a string to a portfolio object<br>
 *
 */

@XmlRootElement
public class Portfolio {

    @XmlJavaTypeAdapter(CDataAdapter.class)
    @XmlElement
    private String code;
    @XmlJavaTypeAdapter(CDataAdapter.class)
    @XmlElement
    private String owner;
    @XmlJavaTypeAdapter(CDataAdapter.class)
    @XmlElement
    private String manager;
    @XmlJavaTypeAdapter(CDataAdapter.class)
    @XmlElement
    private String beneficiary;
    @XmlElementWrapper(name="assetList")
    @XmlElement(name="asset")
    private List<AssetofPortfolio> assetList = new ArrayList<AssetofPortfolio>();
    /**
     * parse given string and return a Portfolio Object according to given format
     */
    public static Portfolio newInstance(String nextLine) {
        Portfolio result=new Portfolio();
        String[] info=nextLine.split(";",-1);
        result.code=info[0].trim();
        result.owner=info[1].trim();
        result.manager=info[2].trim();
        if(!"".equals(info[3].trim())) {
            result.beneficiary = info[3].trim();
        }
        if(!info[4].trim().equals("")){
            String[] list=info[4].split(",");
            for(String asset : list){
                String[] kv = asset.split(":");
                AssetofPortfolio a = new AssetofPortfolio(kv);
                result.assetList.add(a);
            }
        }

        return result;
    }

    public String getCode() {
        return code;
    }

    public String getOwner() {
        return owner;
    }

    public String getManager() {
        return manager;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public List<AssetofPortfolio> getAssetList() {
        return assetList;
    }
}
