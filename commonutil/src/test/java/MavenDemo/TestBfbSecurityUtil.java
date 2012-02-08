package MavenDemo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import com.bfb.commons.security.BfbSecurityFilter;
import junit.framework.TestCase;

public class TestBfbSecurityUtil extends TestCase {
	public void testDoFilter(){
		//通过第三方的测试替身来模拟请求对象和应答对象。用来模拟浏览器发起的请求  
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        
        request.addParameter("param1", "param1value1");  
        request.addParameter("param2", "param2value1");  
        request.addParameter("param3", "param2value2");  
        FilterChain chain = new MockFilterChain();
        
        try {
			new BfbSecurityFilter().doFilter(request, response, chain);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}  
       
	}
}
