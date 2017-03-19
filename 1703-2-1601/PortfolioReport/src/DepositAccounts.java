import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class for modeling DepositAccounts
 */
@XmlRootElement(name="asset")
public class DepositAccounts extends Asset {

	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double apr;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	protected String type="D";
	
	public DepositAccounts(){}
	
	public DepositAccounts(String[] info) {
		if(info.length!=4){
			throw new RuntimeException("infomation does not match with type!!!");
		}
		code=info[0].trim();
		label=info[2].trim();
		apr=Double.parseDouble(info[3].trim())/100;
		
	}

	public Double getApr() {
		return apr;
	}

	public String getType() {
		return type;
	}
}
