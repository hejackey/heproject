package MavenDemo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Assert;

public class MockFilterChain implements FilterChain {
	private boolean shouldBeInvoked;
    private boolean wasInvoked;

    public void doFilter(ServletRequest req, ServletResponse res)
            throws IOException, ServletException {
        this.wasInvoked = true;
    }

    public void setExpectedInvocation(boolean shouldBeInvoked) {
        this.shouldBeInvoked = shouldBeInvoked;
    }

    public void verify(  ) {

        if (this.shouldBeInvoked) {
            Assert.assertTrue("Expected MockFilterChain to be invoked.",
                              this.wasInvoked);
        } else {
            Assert.assertTrue("Expected MockFilterChain filter not to be invoked.",
                              !this.wasInvoked);
        }
    }

}
