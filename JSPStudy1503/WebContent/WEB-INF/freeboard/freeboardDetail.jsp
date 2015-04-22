<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>	
</head>
<body data-spy="scroll">

	<!-- ******FreeBoard****** -->
	<section id="about" class="about section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 mt">					
					<div class="content-panel panel-body">
						<!-- 뷰 -->
						<div class="view_head">
							<div class="subject">${article.title}</div>						
							<div class="readcount_text">조회수</div>
							<div class="readcount">${article.read_count}</div>
							<span class="txt_bar"> | </span>
							<div class="writer">${article.writer_name}</div>
							<span class="txt_bar"> | </span>
							<div class="reg_date">${article.posting_date}</div>
							<%-- <div class="reg_date">
								<fmt:formatDate value="${article.posting_date}" pattern="yy.MM.dd hh:mm:ss" />
							</div> --%>
							
						</div>
						<div class="view_body">
							<div class="content">${article.content}</div>
						</div>
						<div class="btn-group">
							<button id="listBtn" class="btn btn-default" 
								onclick="window.location.href='${pageContext.request.contextPath}/freeboard/list.do'">목록</button>
							 <form method="post" action="${pageContext.request.contextPath}/freeboard/writeView.do">
								<input type="hidden" name="article_id" value="${article.article_id }">
								<input type="hidden" name="group_id" value="${article.group_id }">
								<input type="hidden" name="depth" value="${article.depth }">
								<input type="hidden" name="indent" value="${article.indent }">
								<button id="listBtn" class="btn btn-default" type="submit">답글</button>
							</form>  						
							<c:if test="${UNAME == article.writer_name }">
								<button id="modifyBtn" class="btn btn-default" 
									onclick="window.location.href='${pageContext.request.contextPath}/freeboard/modifyView.do?article_id=${article.article_id }'">수정</button>
								<button id="deleteBtn" class="btn btn-default" 
									onclick="window.location.href='${pageContext.request.contextPath}/freeboard/delete.do?article_id=${article.article_id }'">삭제</button>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>