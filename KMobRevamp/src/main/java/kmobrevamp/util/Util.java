package kmobrevamp.util;

public class Util {

	public static String[] split(String s)
	{
		String[] strings=new String[2];
		char[] chars=s.toCharArray();
		int i=0;
		for(char c:chars)
		{
			if(c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9')
			{
				
				break;
			}
			
			i++;
			
		}
		
		strings[0]=s.substring(0,i);
		strings[1]=s.substring(i);
		return strings;
	}
	
}
