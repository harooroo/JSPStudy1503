<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>JSPStudy</title>
   </head> 

<body data-spy="scroll">
    
   
      
    
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
			            	 <div class="form-control" style="height:20%;" > <!-- -->
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
    
      
</body>
</html> 