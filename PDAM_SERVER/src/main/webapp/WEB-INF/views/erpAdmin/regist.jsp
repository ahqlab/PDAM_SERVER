<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$(document).ready(
		
});
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/erpAdmin/list">
					<img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">관리자리스트
				</a>
			</li>
			<li class="on">
				<a href="${pageContext.request.contextPath}/erpAdmin/regist">
					<img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">관리자등록
				</a>
			</li>
		</ul>
	</div>
	<!--table01_content-->
	<div class="table01_content">
		<!--sub_title-->
		<div class="sub_title">
			관리자등록
		</div>
		<!--sub_title end-->
		<!--table_white-->
		<form:form method="POST" id="erpAdminForm" commandName="domain" >
		<div class="table_white_form">		
			<table class="table_white">
				<tr>
					<td>출력일</td>
					<td><form:input path="printDate" class="input01"/><form:hidden path="eaidx" class="input01"/></td>
				</tr>
				<tr>
					<td>소장</td>
					<td><form:input path="manager" class="input01"/></td>
				</tr>
				<tr>
					<td>공무</td>
					<td><form:input path="publicService" class="input01"/></td>
				</tr>
				<tr>
					<td>공사</td>
					<td><form:input path="construction" class="input01"/></td>
				</tr>
				<tr>
					<td>안전</td>
					<td><form:input path="safety" class="input01"/></td>
				</tr>
				<tr>
					<td>측량</td>
					<td><form:input path="measurement" class="input01"/></td>
				</tr>
				<tr>
					<td>두부정리</td>
					<td><form:input path="pileCuttingWork" class="input01"/></td>
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
