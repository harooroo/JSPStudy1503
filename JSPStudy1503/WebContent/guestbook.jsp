<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>JSPStudy</title>
    <!-- Meta -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="haroo">    
    <link rel="shortcut icon" href="favicon.ico">  
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> 
    <!-- Global CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Plugins CSS -->    
    <link rel="stylesheet" href="assets/plugins/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="assets/plugins/prism/prism.css">
    <!-- Theme CSS -->  
    <link id="theme-style" rel="stylesheet" href="assets/css/styles.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head> 

<body data-spy="scroll">
    
   
    
    <!-- ******HEADER****** --> 
    <header id="header" class="header">  
        <div class="container">            
            <h1 class="logo pull-left">
                <a class="scrollto" href="#promo">
                    <span class="logo-title"><a href="index.do">Haroo</a></span>
                </a>
            </h1><!--//logo-->              
            <nav id="main-nav" class="main-nav navbar-right" role="navigation">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button><!--//nav-toggle-->
                </div><!--//navbar-header-->            
                <div class="navbar-collapse collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active nav-item sr-only"><a class="scrollto" href="#promo">Home</a></li>
                        <li class="nav-item"><a class="scrollto" href="#about">About</a></li>
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/guestbook.do">Guest Book</a></li>
                        <li class="nav-item"><a class="scrollto" href="#docs">Docs</a></li>
                        <li class="nav-item"><a class="scrollto" href="#license">License</a></li>    
                        <c:set var="username" value="${cookie.UNAME.value }"></c:set>                   
                        <c:choose>
	                        <c:when test="${empty username}">
	                        	<li class="nav-item last"><a href="login.jsp">Login</a></li>                        
	                        </c:when>
	                        <c:otherwise>
	                        	<li class="nav-item"><a class="scrollto">${username }</a></li>
	                        </c:otherwise>
                        </c:choose>
                    </ul><!--//nav-->
                </div><!--//navabr-collapse-->
            </nav><!--//main-nav-->
        </div>
    </header><!--//header-->
    
   
    
    <!-- ******GuestBook****** --> 
    <section id="about" class="about section">
        <div class="container">            
			<div class="row">
                <div class="col-lg-12">
                    <form name="sentMessage" id="contactForm" method="post" action="${pageContext.request.contextPath}/writeGuestbook.do">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="이름 *" name="guest_name" required data-validation-required-message="Please enter your name.">
                                    <p class="help-block text-danger"></p>
                                </div>                                
                                <div class="form-group">
                                    <input type="tel" class="form-control" placeholder="비밀번호 *" name="password" required data-validation-required-message="Please enter your password.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <textarea class="form-control" placeholder="메세지 *" name="message" required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success">                               
                                	<button type="submit" class="btn btn-xl">작성</button>
                                </div> 
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <hr>
            <!-- Guestbook list -->
            <c:forEach var="message" items="${messages}">
	            <div class="row">
	            	<div class="col-md-12">
	            		<div class="form-group">		            	 
			            	 <div class="form-control" style="height:100%;">
			            	    <strong>작성자 : ${message.guest_name}</strong>
			            	    <br>
			            	 	${message.message}
								<br>
								<a href="${pageContext.request.contextPath}/deleteGuestBook.do?message_id=${message.message_id}">삭제하기</a>	
							</div>											
						</div>
					</div>
	           </div> <!--// Guestbook list -->
           </c:forEach>
            
        </div><!--//container-->
    </section><!--//about-->
    
  
      
    <!-- ******FOOTER****** --> 
    <footer class="footer">
        <div class="container text-center">
            <small class="copyright">Copyright haroo @2015</small>
        </div><!--//container-->
    </footer><!--//footer-->
     
    <!-- Javascript -->          
    <script type="text/javascript" src="assets/plugins/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="assets/plugins/jquery-migrate-1.2.1.min.js"></script>    
    <script type="text/javascript" src="assets/plugins/jquery.easing.1.3.js"></script>   
    <script type="text/javascript" src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>     
    <script type="text/javascript" src="assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script> 
    <script type="text/javascript" src="assets/plugins/prism/prism.js"></script>    
    <script type="text/javascript" src="assets/js/main.js"></script>       
</body>
</html> 