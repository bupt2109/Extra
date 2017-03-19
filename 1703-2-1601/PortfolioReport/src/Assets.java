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
 * and storing  all Asset Objects <br>
 */
@XmlRootElement
public class Assets {

	@XmlElementRef
	private List<Asset> asset=new ArrayList<Asset>();

	/**
	 * create Assets from .dat
	 * @param file
	 * @return
	 */
	public static Assets parse(File file) {
		Assets assets=new Assets();
		try(FileInputStream in=new FileInputStream(file);
			Scanner scanner=new Scanner(in);){									
			scanner.nextLine();
			while(scanner.hasNext()){
				
				Asset a=Asset.newInstance(scanner.nextLine());
				assets.asset.add(a);
			}
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return assets;
	}

	/**
	 * create Assets from sql
	 * @return
	 */
	public static Assets parse() {
		CDataAdapter adapter = new CDataAdapter();
		Assets assets = new Assets();
		// find all person information
		String sql = "Select * from asset";
		ResultSet rs = PortfolioSQL.query(sql);
		StringBuilder sb = new StringBuilder();
		try {
			while (rs.next()) {
				sb.delete( 0, sb.length() );
				String code = rs.getString(1);
				// find asset code
				sb.append(code);
				sb.append(';');
				char ch = rs.getString(2).charAt(0);
				sb.append(ch);
				sb.append(';');
				sb.append(adapter.unmarshal(rs.getString(3)));
				sb.append(';');
				String subSql = null;
				int num = 0;
				//according to different kind of asset, find different asset information from different tables
				switch(ch){
					case 'D':
						subSql = "Select * from assetDeposit where AssetCode = " + "'" + code + "'";
						num = 1;
						break;
					case 'P':
						subSql = "Select * from assetPrivate where AssetCode = " + "'" + code + "'";
						num = 4;
						break;
					case 'S':
						subSql = "Select * from assetStock where AssetCode = " + "'" + code + "'";
						num = 5;
						break;
				}
				ResultSet subRs = PortfolioSQL.query(subSql);
				while (subRs.next()) {
					for (int i = 2; i < num+2; i++) {
						sb.append(subRs.getString(i));
						sb.append(";");
					}
					sb.deleteCharAt(sb.length()-1);
				}
				//System.out.println(sb.toString());
				Asset a=Asset.newInstance(sb.toString());
				assets.asset.add(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assets;
	}

	public Asset findAssetbyCode(String code){
		for(Asset a : asset){
			if(a.getCode().equals(code))
				return a;
		}
		return null;
	}

	public List<Asset> getAsset() {
		return asset;
	}

}
