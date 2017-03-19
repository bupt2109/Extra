import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

/**
 * flow control class including input analysis and output
 *
 */
public class Main {

	private Persons persons;
	private Assets assets;
	private Portfolios portfolios;

	public static void main(String[] args) {

		Main test = new Main();
		test.printXml();
		test.printInfo();
	}


	public void printXml(){
		System.out.println("convert start....");
		//File src=new File("./data/Persons.dat");
		persons=Persons.parse();
		toXml("./data/Persons.xml",persons);
		//src=new File("./data/Assets.dat");
		assets=Assets.parse();
		toXml("./data/Assets.xml",assets);
		//src=new File("./data/Portfolios.dat");
		portfolios=Portfolios.parse();
		toXml("./data/Portfolios.xml",portfolios);
		System.out.println("convert finish....");
	}

	/**
	 * marshal the given object to the given destination with XML format
	 */
	private <T> void toXml(String dest,T target){

		if(!target.getClass().isAnnotationPresent(XmlRootElement.class)) return;

		try(FileOutputStream out=new FileOutputStream(dest);){
			JAXBContext jaxbContext = JAXBContext.newInstance(target.getClass());  
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
		    jaxbMarshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler",
		    		new CharacterEscapeHandler() {//escape some special characters(<,>,&,',"),put them in CDATA area
		            	@Override
		                public void escape(char[] ch, int start,int length, boolean isAttVal,
		                        Writer writer) throws IOException {
		                    writer.write(ch, start, length);
		                }
		            });
		    jaxbMarshaller.marshal(target, out);	         			
		} catch ( IOException |  JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * print "Portfolio Summary Report" and "Portfolio Details" to output.txt
	 */
	public void printInfo() {

		int size = portfolios.getPortfolio().size();
		PortfolioInfo[] pis = new PortfolioInfo[size];
		for (int i = 0; i < size; i++) {
			pis[i] = new PortfolioInfo(portfolios.getPortfolio().get(i));
		}
		String format;
		double totalFee = 0;
		double totalCommision = 0;
		double totalReturn = 0;
		double totalTotal = 0;
		System.out.print("Portfolio Summary Report\n");
		for (int i = 0; i < 127; i++) {
			System.out.print("=");
		}
		System.out.print("\n");
		format = String.format("%-10s %-20s %-20s   %10s   %10s %10s   %10s   %10s\n",
				"Portfolio", "Owner", "Manager", "Fees", "Commisions", "Weighted Risk", "Return", "Total");
		System.out.print(format);
		for (int i = 0; i < size; i++) {
			PortfolioInfo pi = pis[i];
			double fee;
			double commision;
			if (pi.type == 1) {//manager level E
				fee = 10 * pi.list.size();
				commision = 0.05 * pi.totalReturn;
			} else {//manager level J
				fee = 50 * pi.list.size();
				commision = 0.02 * pi.totalReturn;
			}
			totalFee += fee;
			totalCommision += commision;
			totalReturn += pi.totalReturn;
			totalTotal += pi.totalValue;
			format = String.format("%-10s %-20s %-20s $ %10.2f $ %10.2f %10.4f $ %10.2f $ %10.2f\n",
					pi.code, pi.owner, pi.manager, fee, commision, pi.totalRisk, pi.totalReturn, pi.totalValue);
			System.out.print(format);
		}
		for (int k = 0; k < 53; k++) {
			System.out.print(" ");
		}
		for (int k = 0; k < 72; k++) {
			System.out.print("-");
		}
		System.out.print("\n");
		format = String.format("%52s $ %10.2f $ %10.2f %10s $ %10.2f $ %10.2f\n",
				"Totals", totalFee, totalCommision, " ", totalReturn, totalTotal);
		System.out.print(format);


		System.out.print("\n\n\nPortfolio Details\n");
		for (int i = 0; i < 112; i++) {
			System.out.print("=");
		}
		System.out.print("\n");
		for (int i = 0; i < size; i++) {
			PortfolioInfo pi = pis[i];
			System.out.print("Portfolio " + pi.code + '\n');
			for (int k = 0; k < 42; k++) {
				System.out.print("-");
			}
			System.out.print("\n");
			format = String.format("%-15s %s\n", "Owner:", pi.owner);
			System.out.print(format);
			format = String.format("%-15s %s\n", "Manager:", pi.manager);
			System.out.print(format);
			format = String.format("%-15s %s\n", "Beneficiary:", pi.beneficiary);
			System.out.print(format);
			System.out.print("Assets \n");
			format = String.format("%-10s %-28s %14s %13s  %-18s %9s\n",
					"Code", "Asset", "Return Rate", "Risk", "Annual Return", "Value");
			System.out.print(format);
			for (AssetInfo ai : pi.list) {
				format = String.format("%-10s %-28s %13.2f%% %13.2f  $%12.2f  $%12.2f\n",
						ai.assetCode, ai.assetName, ai.assetRR, ai.assetRisk, ai.assetARR, ai.assetValue);
				System.out.print(format);
			}
			for (int k = 0; k < 56; k++) {
				System.out.print(" ");
			}
			for (int k = 0; k < 44; k++) {
				System.out.print("-");
			}
			System.out.print("\n");
			format = String.format("%48sTotals %13.4f  $%12.2f  $%12.2f\n\n",
					" ", pi.totalRisk, pi.totalReturn, pi.totalValue);
			System.out.print(format);
		}

	}

	/**
	 * inner class of Portfolio Info, for print Portfolio Details
	 */
	public class PortfolioInfo {
		String code;
		String owner;
		String manager;
		String beneficiary;
		List<AssetInfo> list;
		double totalRisk;
		double totalReturn;
		double totalValue;
		int type;

		public PortfolioInfo(Portfolio p) {
			code = p.getCode();
			owner = persons.findPersonNamebyCode(p.getOwner());
			manager = persons.findPersonNamebyCode(p.getManager());
			beneficiary = p.getBeneficiary() == null ? "none" : persons.findPersonNamebyCode(p.getBeneficiary());
			type = persons.findPersonbyCode(p.getManager()).getType().charAt(0) == 'E' ? 1 : 0;
			list = new ArrayList<AssetInfo>();
			totalRisk = 0;
			totalReturn = 0;
			totalValue = 0;
			int length = p.getAssetList().size();
			for (int i = 0; i < length; i++) {
				AssetofPortfolio aop = p.getAssetList().get(i);
				AssetInfo ai = new AssetInfo(aop.getCode(), Double.parseDouble(aop.getValue()));
				totalReturn += ai.assetARR;
				totalValue += ai.assetValue;
				totalRisk += ai.assetRisk * ai.assetValue;
				list.add(ai);
			}
			if (totalValue != 0)
				totalRisk /= totalValue;
		}
	}

	/**
	 * inner class of Asset Info, for calculate and print asset info in Portfolio Details.
	 */
	public class AssetInfo {
		String assetCode;
		String assetName;
		double assetRR;//return rate
		double assetRisk;
		double assetARR;//annual return rate
		double assetValue;

		public AssetInfo(String k, Double v) {
			assetCode = k;
			Asset asset = assets.findAssetbyCode(k);
			assetName = asset.getLabel();
			if (asset != null) {
				char type = asset.getType().charAt(0);
				switch (type) {
					case 'D':
						DepositAccounts d = (DepositAccounts) asset;
						assetRR = (Math.pow(Math.E, d.getApr()) - 1) * 100;
						assetRisk = 0;
						assetValue = v;
						assetARR = assetValue * assetRR / 100;
						break;
					case 'S':
						Stocks s = (Stocks) asset;
						assetRisk = s.getBeta();
						assetValue = v * s.getSharePrice();
						assetRR = (s.getQuarterlyDividend() * 4 + s.getBaseRateOfReturn() * s.getSharePrice()) /
								s.getSharePrice() * 100;
						assetARR = assetValue * assetRR / 100;
						break;
					case 'P':
						PrivateInvestments p = (PrivateInvestments) asset;
						assetValue = v / 100 * p.getValue();
						assetRisk = (Math.pow(Math.E, -100000 / p.getValue()) + p.getOmega());
						assetRR = (p.getQuarterlyDividend() * 4 + p.getBaseRateOfReturn() * p.getValue()) /
								p.getValue() * 100;
						assetARR = assetValue * assetRR / 100;
						break;
				}
			}
		}
	}

}
