import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * base class  for modeling asset and <br>
 * providing a factory method to parse a string to a Asset Object<br>
 * there are still three subclass,corresponding to three types of Asset(stocks,depositAccounts,privateInvestments)<br>
 */

@XmlRootElement
@XmlSeeAlso(value = { Stocks.class,DepositAccounts.class,PrivateInvestments.class})
public abstract class Asset {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	protected String code;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	protected String type;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	protected String label;
	


	public static Asset newInstance(String nextLine) {
		String[] info=nextLine.split(";");
		if("D".equals(info[1])){
			return new DepositAccounts(info);
		}else if("S".equals(info[1])){
			return new Stocks(info);
		}else if("P".equals(info[1])){
			return new PrivateInvestments(info);
		}else{
			throw new RuntimeException("Asset type does not exist!!!");
		}
		
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public String getType() {
		return type;
	}
}
