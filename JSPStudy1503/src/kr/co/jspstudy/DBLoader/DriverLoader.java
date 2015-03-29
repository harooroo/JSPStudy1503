package kr.co.jspstudy.DBLoader;

import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class DriverLoader
 */
@WebServlet("/DriverLoader")
public class DriverLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverLoader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String driver = config.getInitParameter("jdbcdriver");
		StringTokenizer st = new StringTokenizer(driver, ",");
		while(st.hasMoreElements()){
			String jdbcDriver = st.nextToken();
			try {
				Class.forName(jdbcDriver);
				System.out.println("jdbcDriver Loading Success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
