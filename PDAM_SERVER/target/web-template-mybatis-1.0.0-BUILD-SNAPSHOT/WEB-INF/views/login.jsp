<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <title>관측</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen and (min-width:1024px)" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" type="text/css" media="screen and (max-width:1023px)"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
    <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> -->
<title>우리기술</title>
</head>
<body>
	  <script type="text/javascript">
	    $( document ).ready(function() {
	    	var errorMessage = '${errorMessage}';
	    	if(errorMessage != ''){
	    		alert(errorMessage);	
	    	}
	    });
    </script>
	<!--login_wrap-->
	
    <div id="login_wrap" style="align-content: center; text-align:Center;"> 
		<!--로그인form-->
		<form:form  action="${pageContext.request.contextPath}/login" commandName="domain" method="POST">
		<div class="login_form">
			<!-- <p style="text-align:Center;"><img src="${pageContext.request.contextPath}/images/logo.png" class="로고"></p> -->
			<p style="text-align:Center;">
				<font color="#ffffff" size="6">파일 항타 관입량 자동측정 시스템 </font>
			</p>
		 	<p style="text-align:Center;">
				<font color="#ffffff" size="5">Pile Driving Automatic Measurement system</font>
			</p>
			<div class="login_div" >
				<p class="title">PDAM Login<p>
				<div class="form01">
					<img src="${pageContext.request.contextPath}/images/icon01.png" class="icon01">
					<form:input path="userId" class="input01" placeholder="연락처" value=""/>
				</div>
				<div class="form01">
					<img src="${pageContext.request.contextPath}/images/icon02.png" class="icon02">
					<form:password path="password" class="input01" placeholder="비밀번호" value="" />
				</div>
				<div class="form01">
					<input type="submit" class="button01" value="Login">
				</div>
			</div>
		</div>
		</form:form>
		<!--로그인form end-->
    </div>
	<!--login_wrap end-->
</body>
</html>