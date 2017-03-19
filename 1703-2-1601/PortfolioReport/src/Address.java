import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 * class for modeling address
 */
@XmlRootElement
public class Address {
	@XmlElement
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String street;
	@XmlElement
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String city;
	@XmlElement
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String state;
	@XmlElement
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String zipcode;
	@XmlElement
	@XmlJavaTypeAdapter(CDataAdapter.class)
	private String country;
	
	public Address(){
		
	}
	public Address(String[] string) {
		street=string[0].trim();
		city=string[1].trim();
		state=string[2].trim();
		zipcode=string[3].trim();
		country=string[4].trim();
	}

}
