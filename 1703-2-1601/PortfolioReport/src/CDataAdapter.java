import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * escape some special characters(<,>,&,',"),put them in CDATA area
 *
 */
public class CDataAdapter extends XmlAdapter<String, String> {

	/**
	 * convert CDATA to normal string
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@Override
	public String unmarshal(String str) throws Exception {
		if(str.contains("&lt;")){
			str = str.replace("&lt;","<");
		}else if(str.contains("&gt;")){
			str = str.replace("&gt;",">");
		}else if(str.contains("&amp;")){
			str = str.replace("&amp;","&");
		}else if(str.contains("&apos;")){
			str = str.replace("&apos;","\'");
		}else if(str.contains("&quot;")){
			str = str.replace("&quot;","\"");
		}
		return str;
	}

	/**
	 * convert normal string to CDATA
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@Override
	public String marshal(String str) throws Exception {
		if(str.matches(".*[<|>|&|'|\"].*")){
			StringBuilder sb = new StringBuilder();
			char[] chs = str.toCharArray();
			for(char ch:chs){
				switch(ch){
					case '<': sb.append("&lt;");break;
					case '>': sb.append("&gt;");break;
					case '&': sb.append("&amp;");break;
					case '\'': sb.append("&apos;");break;
					case '"': sb.append("&quot;");break;
					default:sb.append(ch);
				}
			}
			return sb.toString();
		}else{
			return str;
		}
	}

}
