<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<!-- head -->
	<tiles:insertAttribute name="head"/>
	<!-- end head -->
</head>
<!-- 페이지인식 -->
<script type="text/javascript">
$(function(){
	var menuIndex = ${menuIndex};
	//alert('menuIndex : ' + menuIndex);
    //$('#gnb').children().eq(menuIndex).find('a').eq(0).addClass('on');
});
</script>
<body>
	<!--wrap-->
	<div id="wrap">
		<!-- nav -->
		<tiles:insertAttribute name="nav"/>
		<!-- end nav -->
		<tiles:insertAttribute name="content"/>
	</div>
	<!--wrap end-->
</body>
</html>
