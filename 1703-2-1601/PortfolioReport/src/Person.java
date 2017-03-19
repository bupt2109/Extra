import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 * class  for modeling person and <br>
 * providing a factory method to parse a string to a person object<br>
 *
 */
@XmlRootElement
public class Person {
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	private String code;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	private String firstName;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	private String lastName;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	private String secIdentifier;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElement
	private String type;
	@XmlJavaTypeAdapter(CDataAdapter.class)
	@XmlElementWrapper(name="emails")
	@XmlElement(name="string")
	private List<String> email=new ArrayList<String>();
	@XmlElement
	private Address address;
	/**
	 * parse given string and return a Person Object according to given format
	 */
	public static Person newInstance(String nextLine) {
		Person result=new Person();
		String[] info=nextLine.split(";");
		result.code=info[0].trim();
		result.firstName=info[2].split(",")[1].trim();
		result.lastName=info[2].split(",")[0].trim();
		result.address=new Address(info[3].split(","));
		if(info.length>4){
			String[] emails=info[4].split(",");
			for(String email:emails){
				result.email.add(email);
			}
		}
		if(!"".equals(info[1])){
			result.type=info[1].split(",")[0];
			result.secIdentifier=info[1].split(",")[1];
		}
		return result;
	}


	public String getCode() {
		return code;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSecIdentifier() {
		return secIdentifier;
	}

	public String getType() {
		return type;
	}

	public List<String> getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}
}
