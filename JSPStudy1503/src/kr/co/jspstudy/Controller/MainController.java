package kr.co.jspstudy.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.GuestBookMessageDao;
import kr.co.jspstudy.DAO.MemberDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Service.GuestBookDelete;
import kr.co.jspstudy.Service.GuestBookWrite;
import kr.co.jspstudy.Service.JSPService;
import kr.co.jspstudy.Service.MemberLogin;
import kr.co.jspstudy.Service.MemberRegister;
import kr.co.jspstudy.Service.GuestBookView;
import kr.co.jspstudy.VO.GuestBookMessage;
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
		
		JSPService service = null;
		boolean isRedirect = false;
		String viewPage ="";
		
		String command = "";
		command = request.getRequestURI();		
		if(command.indexOf(request.getContextPath())==0){
			command = command.substring(request.getContextPath().length());
		}
		
		System.out.println("command:" + command);
		System.out.println("getContextPath():" + request.getContextPath());
		int a = command.indexOf(request.getContextPath());
		System.out.println("a:" + a);

		//URI 처리
		if (command.equals("/index.do")) { // 메인페이지
			isRedirect = true;
			viewPage = "index.jsp";
		} else if (command.equals("/login.do")) { // 로그인
			service = new MemberLogin();
		} else if (command.equals("/logout.do")) { // 로그아웃
			Cookie cookie1 = new Cookie("LOGIN","");
			Cookie cookie2 = new Cookie("UNAME","");			
			cookie1.setMaxAge(0); // 쿠키삭제			
			cookie2.setMaxAge(0); // 쿠키삭제
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			
			isRedirect = true;
			viewPage = "index.jsp";
		} else if (command.equals("/register.do")) { // 회원가입
			service = new MemberRegister();
		} else if (command.equals("/guestbook.do")) { // 방명록 리스트
			service = new GuestBookView();
		} else if (command.equals("/writeGuestbook.do")) { // 방명록 작성
			service = new GuestBookWrite();
		} else if (command.equals("/deleteGuestBook.do")) { // 방명록 삭제
			service = new GuestBookDelete();
		} else {
			System.out.println("No matching Command!!");
		}

		//view
		if (isRedirect) {
			response.sendRedirect(viewPage);
		} else {
			viewPage = service.doService(request, response);

			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	    
	}	

}
