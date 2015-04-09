<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
				<div class="col-md-12">
					<h3>
						<i class="fa-angle-right"></i>자유게시판
					</h3>
					<table class="table">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<%-- <c:forEach var="emp" items="${emplist}" varStatus="seq"> --%>
							<tr>
								<td>1</td>
								<td>2</td>
								<td>3</td>
								<td>4</td>
							</tr>
							<tr>
								<td>1</td>
								<td>2</td>
								<td>3</td>
								<td>4</td>
							</tr>
							<!-- </c:forEach> -->
						</tbody>
					</table>
					<%--  <c:if test="${empty emplist || empty emplist[0]}">
					<h3 style="margin: 80px auto; text-align: center;">등록 직원이 없습니다...</h3>
				  </c:if> --%>
				</div>
			</div>
		</div>
		<div class="col-lg-12 text-center">
			<div id="success">
				<button type="submit" class="btn btn-xl">글쓰기</button>
			</div>
		</div>
	</section>

</body>
</html>