package kr.co.jspstudy.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jspstudy.Service.FreeBoardDeleteService;
import kr.co.jspstudy.Service.FreeBoardDetailService;
import kr.co.jspstudy.Service.FreeBoardModifyService;
import kr.co.jspstudy.Service.FreeBoardModifyViewService;
import kr.co.jspstudy.Service.FreeBoardWriteService;
import kr.co.jspstudy.Service.GuestBookDeleteService;
import kr.co.jspstudy.Service.GuestBookViewService;
import kr.co.jspstudy.Service.GuestBookWriteService;
import kr.co.jspstudy.Service.JSPService;
import kr.co.jspstudy.Service.MemberLoginService;
import kr.co.jspstudy.Service.MemberLogoutService;
import kr.co.jspstudy.Service.MemberRegisterService;
import kr.co.jspstudy.Service.FreeBoardListService;


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
		//request.setCharacterEncoding("utf-8");
		
		JSPService service = null;
		boolean isRedirect = false;
		String viewPage ="";
		
		String command = "";
		command = request.getRequestURI();		
		if(command.indexOf(request.getContextPath())==0){
			command = command.substring(request.getContextPath().length());
		}
		
		System.out.println("command:" + command);
		//System.out.println("getContextPath():" + request.getContextPath());
		//int a = command.indexOf(request.getContextPath());
		//System.out.println("a:" + a);

		//URI ó��
		//db�� DMLó���� �ϴ� ���� redirect�� �����༭ url�� �����Ǵ��� DML���� ����� ������ ���� �ʵ��� �ߴ�.
		if (command.equals("/index.do")) { // ����������
			isRedirect = true;
			viewPage = "index.jsp";
		} else if (command.equals("/login.do")) { // �α���
			service = new MemberLoginService();
		} else if (command.equals("/logout.do")) { // �α׾ƿ�
			isRedirect = true;
			service = new MemberLogoutService();
		} else if (command.equals("/register.do")) { // ȸ������
			service = new MemberRegisterService();
		} else if (command.equals("/guestbook.do")) { // ���� ���
			service = new GuestBookViewService();			
		} else if (command.equals("/writeGuestbook.do")) { // ���� �ۼ�
			service = new GuestBookWriteService();
		} else if (command.equals("/deleteGuestBook.do")) { // ���� ����
			service = new GuestBookDeleteService();
		} else if (command.equals("/freeboard/list.do")) { // �����Խ��� ���
			service = new FreeBoardListService();
		} else if (command.equals("/freeboard/writeView.do")) { // �����Խ��� ���� ȭ��
			System.out.println(request.getParameter("article_id"));
			viewPage ="/WEB-INF/freeboard/freeboardWrite.jsp";
		} else if (command.equals("/freeboard/write.do")) { // �����Խ��� ���� ó��
			isRedirect = true;
			service = new FreeBoardWriteService();
		} else if (command.equals("/freeboard/viewDetail.do")) { // �����Խ��� �󼼱� ����
			service = new FreeBoardDetailService();
		} else if (command.equals("/freeboard/modifyView.do")) { // �����Խ��� ���� ȭ��
			service = new FreeBoardModifyViewService();			
		} else if (command.equals("/freeboard/modify.do")) { // �����Խ��� ���� ó��
			isRedirect = true;
			service = new FreeBoardModifyService();
		} else if (command.equals("/freeboard/delete.do")) { // �����Խ��� ����
			isRedirect = true;
			service = new FreeBoardDeleteService();
		} else {
			System.out.println("No matching Command!!");
		}
		
		//Service ����
		if(service != null){
			viewPage = service.doService(request, response);
		}		

		//View ����
		if (isRedirect) {
			response.sendRedirect(viewPage);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	    
	}	

}
