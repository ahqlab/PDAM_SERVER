<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript"></script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/group/list"><img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">시공사리스트</a></li>
			<li class="on"><a href="#"><img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">시공사등록</a></li>
		</ul>
	</div>
	<!--table01_content-->
	<div class="table01_content">
		<div class="sub_title">
			시공사 등록
		</div>
		<!-- sub_title end-->
		<!--table_white-->
		<form:form method="POST" commandName="domain" >
		<div class="table_white_form">
			<table class="table_white">
				<tr>
					<td>시공사명</td>
					<td><form:input path="groupName" class="input01"/></td>
				</tr>
			</table>
		</div>
		
		<!--table_white-->
		<div class="btn_box">
			<input type="submit" class="button02 button05" value="등록하기" onclick="return formCheck();">
		</div>
		</form:form>
	</div>
	<!--table01_content end-->
</div>
