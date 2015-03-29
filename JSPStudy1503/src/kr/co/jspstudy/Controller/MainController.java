package kr.co.jspstudy.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.MemberDAO;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.VO.Member;


/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		boolean isRedirect = false;
		String viewPage ="";
		String command = request.getRequestURI();		
		
		if(command.indexOf(request.getContextPath())==0){
			command = command.substring(request.getContextPath().length());
		}
		
	    System.out.println("command:"+command);
	    System.out.println("getContextPath():"+request.getContextPath());
	    int a = command.indexOf(request.getContextPath());
	    System.out.println("a:"+a);	    
	    
	   if(command.equals("/index.do")){ //메인페이지
		   /*Cookie[] cookies = request.getCookies();
		   HashMap<String,Cookie> cookiesMap = new HashMap<String,Cookie>();
		   if(cookies != null && cookies.length>0){
			   for(int i=0; i<cookies.length;i++){
				   cookiesMap.put(cookies[i].getName(),cookies[i]);
			   }
		   }
		   if(cookiesMap.get("LOGIN") != null && cookiesMap.get("LOGIN").getValue()=="success"){
			   cookiesMap.get("name").getValue();
		   }*/
		   isRedirect = true;
		   viewPage ="index.jsp";		   
	   }else if(command.equals("/login.do")){ //로그인
	    	String memberid = request.getParameter("memberid");
    		String password = request.getParameter("password");
    		String saveid = request.getParameter("saveid");
    		
    		MemberDAO memberDao = new MemberDAO();
    		Member member = new Member();
    		member.setMemberid(memberid);
    		member.setPassword(password);
    		
    		Connection conn = DBConnect.getConnection();
    		memberDao.login(conn,member);
    		try {	conn.close();	} catch (SQLException e) {	e.printStackTrace(); }
    		
    		System.out.println("name:"+member.getName());
    		
    		Cookie cookie1 = new Cookie("LOGIN","success");
    		Cookie cookie2 = new Cookie("UNAME",URLEncoder.encode(member.getName(),"utf-8"));
    		response.addCookie(cookie1);
    		response.addCookie(cookie2);
    		viewPage = "index.do";
	    }else if(command.equals("/register.do")){ //회원가입
	    	
	    	System.out.println("name:"+request.getParameter("name"));
	    	String memberid = request.getParameter("memberid");
    		String password = request.getParameter("password");
    		String name = request.getParameter("name");
    		String email = request.getParameter("email");
    		    		
    		Member member = new Member(memberid,password,name,email);
    		MemberDAO memberDao = new MemberDAO();
    		
    		Connection conn = DBConnect.getConnection();
    		boolean result = memberDao.register(conn, member);
    		try {	conn.close();	} catch (SQLException e) {	e.printStackTrace(); }
    		
    		if(result){
    			viewPage = "login.jsp";
    		}else{
    			viewPage ="register.jsp";
    		}
    		
	    }
	    
	    if(isRedirect){
	    	response.sendRedirect(viewPage);
	    }else{
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);	    
		    dispatcher.forward(request, response);
	    }	    
	    
	}	

}
