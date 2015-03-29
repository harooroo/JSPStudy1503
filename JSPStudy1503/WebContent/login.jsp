<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Global CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Plugins CSS -->    
    <link rel="stylesheet" href="assets/plugins/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="assets/plugins/prism/prism.css">
    <!-- Theme CSS -->  
    <link id="theme-style" rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
	<!--login modal-->
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          
          <h1 class="text-center">Login</h1>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block" action="login.do" method="post">
            <div class="form-group">
              <input type="text" class="form-control input-lg" placeholder="ID" name="memberid">
            </div>
            <div class="form-group">
              <input type="password" class="form-control input-lg" placeholder="Password" name="password">
            </div>
            <div class="form-group">
              ID저장<input type="checkbox" name="saveid">
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-lg btn-block">로그인</button>
              <span class="pull-right"><a href="register.jsp">회원가입</a></span>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
          <!-- <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button> -->
		  </div>	
      </div>
  </div>
  </div>
</div>
</body>
</html>