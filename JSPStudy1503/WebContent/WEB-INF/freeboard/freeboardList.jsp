<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script type="text/javascript">
	function btnClick_write() {
		alert("Button Click");
	}
</script>
</head>
<body >

	<!-- ******FreeBoard****** -->
	<section id="about" class="about section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h3>
						<i class="fa fa-angle-right"></i> 자유게시판
					</h3>
					<table class="table">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>조회수</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="board" items="${list}">
								<tr>
									<td>${board.article_id}</td>
									<td>										
										<c:if test="${board.indent!=0}">			
											<c:forEach begin="0" end="${board.indent }">
												&nbsp;&nbsp;&nbsp;
											</c:forEach>							
											<img src="${pageContext.request.contextPath}/assets/images/ioc-reply.gif">
										</c:if>
										<a href="${pageContext.request.contextPath}/freeboard/viewDetail.do?article_id=${board.article_id}">${board.title}</a>
									</td>
									<td>${board.read_count}</td>
									<td>${board.writer_name}</td>
									<td>${board.posting_date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if test="${empty list || empty list[0]}">
						<h3 style="margin: 80px auto; text-align: center;">등록한 글이 없습니다...</h3>
					</c:if>
				</div>
			</div>

			<!-- 페이징 -->
			<div class="col-xs-12 text-center">
				<ul class="pagination">
					<!-- 이전 링크 -->
					<li <c:if test="${beginpage<10}"> class="disabled"</c:if>><a
						<c:if test="${beginpage>10}"> href="${pageContext.request.contextPath}/freeboard/list.do?pageSize=${pageSize}&pageNum=${beginpage-1}"</c:if>>«</a>
					</li>

					<!-- 페이지 리스트   -->
					<c:if test="${beginpage!=0}">
						<c:forEach var="i" begin="${beginpage}" end="${endpage}" step="1">

							<c:if test="${i==pageNum}">
								<li class="active"><a>${i} <span class="sr-only">(current)</span></a></li>
							</c:if>
							<c:if test="${i!=pageNum}">
								<li><a
									href="${pageContext.request.contextPath}/freeboard/list.do?pageSize=${pageSize}&pageNum=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
					</c:if>
					<!-- 다음링크 -->
					<li <c:if test="${endpage>=pagecount}"> class="disabled"</c:if>>
						<a
						<c:if test="${endpage<pagecount}"> href="${pageContext.request.contextPath}/freeboard/list.do?pageSize=${pageSize}&pageNum=${endpage+1}"</c:if>>»</a>
					</li>
				</ul>
			</div>
			<!-- 글쓰기 버튼 -->
			<div class="col-lg-12 text-center">
				<div id="success">
					<button onclick="window.location.href='${pageContext.request.contextPath}/freeboard/writeView.do'"
						class="btn btn-xl">글쓰기</button>
				</div>
			</div>
			
		</div>
	</section>

</body>
</html>