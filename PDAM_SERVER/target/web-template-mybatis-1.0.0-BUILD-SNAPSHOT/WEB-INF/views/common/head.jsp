<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<% 
	response.setHeader("Cache-Control","no-store"); 
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader("Expires",0); 
	if (request.getProtocol().equals("HTTP/1.1")) 
		response.setHeader("Cache-Control", "no-cache"); 
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<META http-equiv="Expires" content="-1">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="No-Cache">
<title>PDAM System</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen and (min-width:1024px)" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" type="text/css" media="screen and (max-width:1023px)" />
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<!-- // jQuery UI CSS파일 --> 
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />   --> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" type="text/css" />
<!-- // jQuery 기본 js파일 -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- // jQuery UI 라이브러리 js파일 -->
<!-- <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> -->
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>

<!-- 
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script> 
-->

<script src="${pageContext.request.contextPath}/js/common.js"></script>  
<script src="${pageContext.request.contextPath}/js/TableToJson.min.js"></script>  
<script src="${pageContext.request.contextPath}/js/TableToJson.js"></script>  