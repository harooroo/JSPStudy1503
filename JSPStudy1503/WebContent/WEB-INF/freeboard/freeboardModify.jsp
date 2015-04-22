<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
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
						<i class="fa fa-angle-right"></i> 자유게시판
					</h3>
					<form id="freeboardFrm" class="form-horizontal style-form"
						method="post"
						action="${pageContext.request.contextPath}/freeboard/modify.do">
						<input type="hidden" name="article_id" value="${article.article_id }">
						<div class="form-group">
							<label class="col-sm-2 control-label">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title" value="${article.title }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">내용</label>
							<div class="col-sm-10">								
								<textarea rows="10" cols="5" class="form-control" id="content" name="content" >${article.content }</textarea>
							</div>
						</div>
				</div>
			</div>

			<div class="col-lg-12 text-center">
				<div id="success">
					<button type="submit" class="btn btn-xl">수정</button>
				</div>
			</div>
			</form>
		</div>
	</section>

</body>
</html>