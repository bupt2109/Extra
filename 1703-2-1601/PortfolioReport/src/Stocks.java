import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class  for modeling Stocks
 *
 */
@XmlRootElement(name="asset")
public class Stocks extends Asset {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	protected String type="S";
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double quarterlyDividend;
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double baseRateOfReturn;
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double beta;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	private String symbol;
	@XmlJavaTypeAdapter(DoubleAdapter.class)
	@XmlElement
	private Double sharePrice;
	
	public Stocks(){}
	
	public Stocks(String[] info) {
		if(info.length!=8){
			throw new RuntimeException("infomation does not match with type!!!");
		}
		code=info[0].trim();
		label=info[2].trim();
		symbol=info[6].trim();
		quarterlyDividend=Double.parseDouble(info[3].trim());
		baseRateOfReturn=Double.parseDouble(info[4].trim())/100;
		beta=Double.parseDouble(info[5].trim());
		sharePrice=Double.parseDouble(info[7].trim());
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

	public Double getBeta() {
		return beta;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getSharePrice() {
		return sharePrice;
	}
}
