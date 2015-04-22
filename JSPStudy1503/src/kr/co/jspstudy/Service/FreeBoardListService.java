package kr.co.jspstudy.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jspstudy.DAO.ArticleDao;
import kr.co.jspstudy.DBLoader.DBConnect;
import kr.co.jspstudy.Util.CastingAfterNullCheck;
import kr.co.jspstudy.VO.Article;

public class FreeBoardListService implements JSPService {

	@Override
	public String doService(HttpServletRequest request,HttpServletResponse response) {
		ArticleDao articleDao = ArticleDao.getInstance();
		Connection conn = DBConnect.getConnection();
		
		//페이징 변수     
		int totalCount = 0;
		int pagecount=0;
		int beginPage = 0;
		int endPage = 0;
		int pageSize = 10;  //1페이지당10개 	      
		int pageNum=1;
		
		String pageSizeString = request.getParameter("pageSize");
		String pageNumString = request.getParameter("pageNum");	
	
	    if(!CastingAfterNullCheck.isNull(pageSizeString)){
	    	pageSize = Integer.parseInt(pageSizeString);
	    }
	    if(!CastingAfterNullCheck.isNull(pageNumString)){
	    	pageNum = Integer.parseInt(pageNumString);
	    }		
		
	    System.out.println("*****");
	    System.out.println("pageSize:"+pageSize);
	    System.out.println("pageNum:"+pageNum);
	    System.out.println("*****");
	    
		int firstRow=0;
		int endRow=pageSize;
				
		List<Article> list = new ArrayList<>();
		try {
			totalCount = articleDao.selectCount(conn);			
			//int pages = pageSize/totalCount + pageSize%totalCount; //페이지 수
			firstRow  = (pageNum*5)-5;
			Connection conn2 = DBConnect.getConnection();
			list = articleDao.select(conn2, firstRow, endRow);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		request.setAttribute("list", list);
		
		//paging
		 int startNum = pageNum * pageSize - (pageSize -1)-1;      
	      System.out.println("startNum:"+startNum);
	           
	     
		if (list != null) {
	         pagecount = totalCount / pageSize;// 115건 = 11page
	         if (totalCount % pageSize > 0) {// 115건 = 나머지 5 true
	            pagecount++;// 12page
	         }
	         beginPage = (pageNum - 1) / pageSize * pageSize + 1;
	         endPage = beginPage + (pageSize - 1);
	         if (endPage > pagecount) {
	            endPage = pagecount;
	         }
	      }
	      System.out.println("pagecount:"+pagecount);
	      // view에 보낼 데이터
	      request.setAttribute("pagecount", pagecount);
	      request.setAttribute("beginpage", beginPage);
	      request.setAttribute("endpage", endPage);
	
	      request.setAttribute("pageNum",pageNum);
	      request.setAttribute("pageSize",pageSize);
	      
		return  "/WEB-INF/freeboard/freeboardList.jsp";
	}

}
