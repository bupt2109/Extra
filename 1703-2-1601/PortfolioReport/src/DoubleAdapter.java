import java.text.DecimalFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * avoid from scientific notation when double value is too big<br>
 */
public class DoubleAdapter extends XmlAdapter<String, Double> {

	@Override
	public Double unmarshal(String v) throws Exception {
		
		return Double.parseDouble(v);
	}

	@Override
	public String marshal(Double v) throws Exception {
		if(v != null){
			return v.toString();
        }
        return "";
	}



}
