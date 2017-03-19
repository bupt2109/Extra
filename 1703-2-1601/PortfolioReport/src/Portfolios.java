import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * class for reading content from given file <br>
 * and storing  all Portfolio Objects <br>
 *
 */
@XmlRootElement
public class Portfolios {

    @XmlElementRef
    private List<Portfolio> portfolio=new ArrayList<Portfolio>();

    /**
     * create Portfolios from .dat
     * @param file
     * @return
     */
    public static Portfolios parse(File file) {

        Portfolios portfolios=new Portfolios();
        try(FileInputStream in=new FileInputStream(file);
            Scanner scanner=new Scanner(in);){
            scanner.nextLine();
            while(scanner.hasNext()){

                Portfolio p = Portfolio.newInstance(scanner.nextLine());
                portfolios.portfolio.add(p);
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return portfolios;
    }

    /**
     * create Portfolios from sql
     * @return Portfolios
     */
    public static Portfolios parse() {

        Portfolios portfolios=new Portfolios();
        Persons persons = new Persons();
        //find all Portfolios information
        String sql = "Select * from portfolio";
        ResultSet rs = PortfolioSQL.query(sql);
        StringBuilder sb = new StringBuilder();
        try {
            while (rs.next()) {
                sb.delete( 0, sb.length() );
                String code = rs.getString(1);
                // find Portfolio code
                sb.append(code);
                sb.append(';');
                sb.append(rs.getString(2));
                sb.append(';');
                sb.append(rs.getString(3));
                sb.append(';');
                if(rs.getString(4)!=null) {
                    sb.append(rs.getString(4));
                }
                sb.append(';');
                //find all Portfolio assets
                String subSql = "Select * from portfolioAsset where portfolioCode = " + "'" + code + "'";
                ResultSet subRs = PortfolioSQL.query(subSql);
                while (subRs.next()) {
                    sb.append(subRs.getString(3));
                    sb.append(":");
                    sb.append(subRs.getString(4));
                    sb.append(",");
                }
                if(sb.charAt(sb.length()-1)==','){
                    sb.deleteCharAt(sb.length()-1);
                }
                //System.out.println(sb.toString());
                Portfolio p = Portfolio.newInstance(sb.toString());
                portfolios.portfolio.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return portfolios;
    }

    public List<Portfolio> getPortfolio() {
        return portfolio;
    }


}

