package MavenDemo;

import org.junit.Test;

import com.bfb.commons.security.HTMLFilter;

public class TestHtmlFilter {
	@Test
	public void testFilter(){
		String input="<a href='2323'alert('a')>asdf</a>";
		String res = new HTMLFilter().filter(input);
		System.out.println(res);
	}
}
