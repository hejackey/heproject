package MavenDemo;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(fn(6));
		long time=System.currentTimeMillis();
		String str = "1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12312" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf134dasf1231234dasf1234dasf1231234dasf11234dasf1" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf11234dasf1231234dasf1231234dasf13"+
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12312" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf134dasf1231234dasf1234dasf1231234dasf11234dasf1" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf11234dasf1231234dasf1231234dasf13"+
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12312" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf134dasf1231234dasf1234dasf1231234dasf11234dasf1" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf11234dasf1231234dasf1231234dasf13"+
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf12" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf123" +
				"1234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231" +
				"231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1231234dasf1234dasf1231234dasf1231234dasf123" ;
		System.out.println(fnStr(str));
		long time1= System.currentTimeMillis();
		System.out.println((time1-time));
		printStr(str);
		long time2= System.currentTimeMillis();
		System.out.println((time2-time1));
	}
	
	/**
	 * n阶乘，递归调用
	 * @param i
	 * @return
	 */
	public static int fn(int i){
		if(i == 0 )
			return 1;
		else
			return i*fn(i-1);
	}
	
	/**
	 * 字符串递归调用
	 * @param str
	 * @return
	 */
	public static String fnStr(String str){
		if(str.length() == 1)
			return str;
		else
			return str.substring(0, 1)+fnStr(str.substring(1));
	}

	public static String printStr(String str){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			sb.append(str.charAt(i));
		}
		
		return sb.toString();
	}
}