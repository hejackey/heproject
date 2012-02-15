package MavenDemo;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.bfb.commons.security.HTMLFilter;

public class TestHtmlFilter {
	@Test
	public void testFilter() throws IOException{
		String input="<a href='2323'alert('a')>asdf</a>";
		String res = new HTMLFilter().filter(input);
		System.out.println(res);
		
		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
		
		//user.name User's account name 
		//user.home User's home directory 
		//user.dir User's current working directory 
		
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(new File(System.getProperty("user.dir")).getCanonicalPath());
	}
}
