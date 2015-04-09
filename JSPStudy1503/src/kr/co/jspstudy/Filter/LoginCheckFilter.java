package kr.co.jspstudy.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/LoginCheckFilter")
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain;
		HttpServletRequest httpRequest  = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		System.out.println("session:"+session);
		
		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println("path:"+path);
		
		if (path.startsWith("/JSPStudy1503/login.do")|| path.startsWith("/JSPStudy1503/register.do")) {
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			chain.doFilter(request,response);
		} else {
			boolean login = false;
			if(session != null){
				if(session.getAttribute("UNAME") != null){
					login = true;
				}
			}
			
			if(login){
				chain.doFilter(request, response);
			}else{
				/*RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);*/
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect("login.jsp");
			}
			//chain.doFilter(request, response); // Just continue chain.
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
