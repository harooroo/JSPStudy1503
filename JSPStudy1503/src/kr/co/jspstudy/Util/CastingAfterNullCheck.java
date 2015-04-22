package kr.co.jspstudy.Util;

public class CastingAfterNullCheck {
	public static boolean isNull(String param){
		boolean result = true;	

		if(param != null && !param.equals("")){
			result = false;
		}	
		
		return result;
	}	
	
	public static int toInteger(String param){
		int result = 0;		

		if(param != null && !param.equals("")){
			result = Integer.parseInt(param);			
		}	
		return result;
	}
}
