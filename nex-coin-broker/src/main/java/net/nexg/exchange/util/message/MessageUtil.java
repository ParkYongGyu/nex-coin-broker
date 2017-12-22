package net.nexg.exchange.util.message;

public class MessageUtil {


    public static String getNumericData(String orgStr, int size) throws Exception{
		StringBuffer result = new StringBuffer();
		if(orgStr.startsWith("-")) 	result.append(orgStr.substring(1)); 
		else 						result.append(orgStr.substring(0)); 
		
		if(orgStr.getBytes().length <= size) {
			for(int i = orgStr.getBytes().length; i < size; i++){
				result.insert(0, "0");
			}
			if(orgStr.startsWith("-")) 	result.insert(0, "-");
		}else{
			throw new Exception("getNumericData : 지정한 사이즈["+ size +"]보다 문자열["+ orgStr +"]이 큽니다.");
		}
		//System.out.println( result.toString());
		return result.toString();
	}
	
    public static String getNonNumericData(String orgStr, int size) throws Exception{
    	
    	StringBuffer result = new StringBuffer();
    	result.append(orgStr); 
		
		if(orgStr.getBytes().length <= size) {
			for(int i = orgStr.getBytes().length; i < size; i++){
				result.append(" ");
			}
		}else{
			throw new Exception("getNonNumericData : 지정한 사이즈["+ size +"]보다 문자열["+ orgStr +"]이 큽니다.");
		}
		//System.out.println( "|| " + result.toString());
		return result.toString();
	}
    
    public static String cutByte(byte[] bArr, int offset, int range) throws Exception{
		String result = new String(bArr, offset, range);
		
		return result;
	}	
    
}
