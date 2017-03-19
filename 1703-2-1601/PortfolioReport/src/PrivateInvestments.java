import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class  for modeling PrivateInvestments
 *
 */
@XmlRootElement(name="asset")
public class PrivateInvestments extends Asset {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	protected String type="P";
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double quarterlyDividend;
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double baseRateOfReturn;
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double omega;
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double value;
	
	
	public PrivateInvestments(){}
	
	public PrivateInvestments(String[] info) {
		if(info.length!=7){
			throw new RuntimeException("infomation does not match with type!!!");
		}
		code=info[0].trim();
		label=info[2].trim();
		quarterlyDividend=Double.parseDouble(info[3].trim());
		baseRateOfReturn=Double.parseDouble(info[4].trim())/100;
		omega=Double.parseDouble(info[5].trim());
		value=Double.parseDouble(info[6].trim());
	}

	public String getType() {
		return type;
	}

	public Double getQuarterlyDividend() {
		return quarterlyDividend;
	}

	public Double getBaseRateOfReturn() {
		return baseRateOfReturn;
	}

	public Double getOmega() {
		return omega;
	}

	public Double getValue() {
		return value;
	}
}
