import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * class for reading content from given file <br>
 * and storing  all Person Objects <br>
 *
 */
@XmlRootElement
public class Persons {

	@XmlElementRef
	private List<Person> person=new ArrayList<Person>();

	/**
	 * create Persons from .dat
	 * @param file
	 * @return
	 */
	public static Persons parse(File file) {
		
		Persons persons=new Persons();
		try(FileInputStream in=new FileInputStream(file);
			Scanner scanner=new Scanner(in);){									
			scanner.nextLine();
			while(scanner.hasNext()){
				
				Person p=Person.newInstance(scanner.nextLine());
				persons.person.add(p);
			}
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return persons;
	}

	/**
	 * create Persons from sql
	 * @return
	 */
	public static Persons parse() {
		CDataAdapter adapter = new CDataAdapter();
		Persons persons = new Persons();
		String sql = "Select * from person";
		// find all person information
		ResultSet rs = PortfolioSQL.query(sql);
		StringBuilder sb = new StringBuilder();
		try {
			while (rs.next()) {
				sb.delete( 0, sb.length() );
				//find person code
				String code = rs.getString(1);
				sb.append(code);
				sb.append(';');
				if(rs.getString(4)!=null){
					sb.append(rs.getString(4));
					sb.append(',');
					sb.append(rs.getString(5));
				}
				sb.append(';');
				sb.append(adapter.unmarshal(rs.getString(2)));
				sb.append(',');
				sb.append(adapter.unmarshal(rs.getString(3)));
				sb.append(';');
				//find all address information
				String addressSql = "Select * from addresses where PersonCode = " + "'" + code + "'";
				ResultSet addressRs = PortfolioSQL.query(addressSql);
				while (addressRs.next()) {
					for (int i = 2; i <= 4; i++) {
						sb.append(addressRs.getString(i));
						sb.append(",");
					}
					if(addressRs.getString(5)!=null){
						sb.append(addressRs.getString(5));
					}
					sb.append(",");
					sb.append(addressRs.getString(6));
				}
				sb.append(';');
				//find all email information
				String mailSql = "Select * from emails where PersonCode = " + "'"+code + "'";
				ResultSet mailRs = PortfolioSQL.query(mailSql);
				while (mailRs.next()) {
					sb.append(mailRs.getString(2));
					sb.append(",");
				}
				if(sb.charAt(sb.length()-1)==','){
					sb.deleteCharAt(sb.length()-1);
				}
				//System.out.println(sb.toString());
				Person p=Person.newInstance(sb.toString());
				persons.person.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persons;
	}


	public Person findPersonbyCode(String code){
		for(Person p:person){
			if(p.getCode().equals(code))
				return p;
		}
		return null;
	}

	public String findPersonNamebyCode(String code){
		Person p = findPersonbyCode(code);
		if(p!=null){
			return p.getLastName()+", "+p.getFirstName();
		}
		else{
			return null;
		}
	}

	public List<Person> getPerson() {
		return person;
	}
}

