<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
 <%@ page trimDirectiveWhitespaces="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>!!JSPStudy!</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Plugins CSS -->    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/prism/prism.css">
    <!-- Theme CSS -->  
    <link id="theme-style" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<decorator:head/>
</head>
<body>
<!-- ******HEADER****** --> 
    <header id="header" class="header">  
        <div class="container">            
            <h1 class="logo pull-left">
                <a class="scrollto" href="#promo">
                    <span class="logo-title"><a href="${pageContext.request.contextPath}/index.do">Haroo</a></span>
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
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/guestbook.do">방명록</a></li>
                        <li class="nav-item"><a href="${pageContext.request.contextPath}/freeboard/list.do">게시판</a></li>
                        <li class="nav-item"><a class="scrollto" href="#license">License</a></li>    
                        <c:set var="username" value="${UNAME }"></c:set>                   
                        <c:choose>
	                        <c:when test="${empty username}">
	                        	<li class="nav-item last"><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>                        
	                        </c:when>
	                        <c:otherwise>
	                        	<%-- <li class="nav-item"><a class="scrollto">${username }</a></li> --%>
	                        	<li class="nav-item last">
								<!-- <div class="dropdown"> -->
									<button class="btn btn-default dropdown-toggle" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">${username } <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu1">
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="${pageContext.request.contextPath}/logout.do">Logout</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="#">Setting</a></li>										
									</ul>
								<!-- </div> -->
								</li>
							</c:otherwise>
                        </c:choose>
                    </ul><!--//nav-->
                </div><!--//navabr-collapse-->
            </nav><!--//main-nav-->
        </div>
    </header><!--//header-->
    
    <decorator:body/>
    
     <!-- ******FOOTER****** --> 
    <footer class="footer">
        <div class="container text-center">
            <small class="copyright">Copyright haroo @2015</small>
        </div><!--//container-->
    </footer><!--//footer-->
     
    <!-- Javascript -->          
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery-migrate-1.2.1.min.js"></script>    
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery.easing.1.3.js"></script>   
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>     
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/plugins/prism/prism.js"></script>    
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>       
 
</body>
</html>