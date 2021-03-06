package kr.co.jspstudy.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import sun.util.logging.resources.logging;

//@WebFilter("/*")
public class SetCharacterEncodingFilter implements Filter {
    
    protected String encoding = null;
    protected FilterConfig filterConfig = null;
    protected boolean ignore = true;

    public SetCharacterEncodingFilter() {
    }

	public void destroy() {
	    this.encoding = null;
	    this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Conditionally select and set the character encoding to be used
        if (ignore || (request.getCharacterEncoding() == null)) {
            //System.out.println("### filter test 1111");
            String encoding = selectEncoding(request);
            if (encoding != null) {
               // System.out.println("### filter test 2222");
                request.setCharacterEncoding(encoding);
            }
        }
        //System.out.println("### filter test 3333");
        //System.out.println("### filter test currentEncoding:" + request.getCharacterEncoding());

        // Pass control on to the next filter
        chain.doFilter(request, response);
	}

	private String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

    public void init(FilterConfig fConfig) throws ServletException {
	    
        this.filterConfig = fConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        //System.out.println("### encoding:" + filterConfig.getInitParameter("encoding"));
        //System.out.println("### path:" + fConfig.getServletContext().getContextPath());
        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;

	}

}