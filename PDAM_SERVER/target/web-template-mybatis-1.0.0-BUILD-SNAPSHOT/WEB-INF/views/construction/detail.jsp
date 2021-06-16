<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
	function formCheck(){
		if($('#name').val() == ''){
			alert('협력사를 입력하세요.');
			return false;
		}else if($('#location').val() == ''){
			alert('현장위치를 입력하세요.');
			return false;
		}else if($('#partner').val() == ''){
			alert('현력사를 입력하세요.');
			return false;
		}else if($('#manager').val() == ''){
			alert('현장담당자를 입력하세요.');
			return false;
		}else if($('#password').val() == ''){
			alert('비밀번호를 입력하세요.');
			return false;
		}else if($('#contact').val() == ''){
			alert('연락처를 입력하세요.');
			return false;
		}
		return true;
	}
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/construction/list"><img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">협력사리스트</a></li>
			<li class="on"><a href="#"><img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">협력사등록</a></li>
		</ul>
	</div>
	<!--table01_content-->
	<div class="table01_content">
		<!--sub_title-->
		<div class="sub_title">
			협력사 등록
		</div>
		<!--sub_title end-->
		
		<!--table_white-->
		<form:form method="POST" id="customerForm" commandName="domain" >
		<div class="table_white_form">
			
			<table class="table_white">
				<tr>
					<td>협력사</td>
					<td>
						<form:input path="name" class="input01" placeholder="협력사"/>
					</td>
				</tr>
				<tr>
					<td>현장위치</td>
					<td><form:input path="location" class="input01" placeholder="현장위치"/></td>
				</tr>
				<tr>
					<td>협력사</td>
					<td><form:input path="partner" class="input01" placeholder="협력사"/></td>
				</tr>
				<tr>
					<td>현장담당자</td>
					<td><form:input path="manager" class="input01" placeholder="현장담당자"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><form:input path="password" class="input01" placeholder="비밀번호"/></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><form:input path="contact" class="input01" placeholder="연락처"/></td>
				</tr>
				<%-- <tr>
					<td>등록일</td>
					<td><form:input path="name" class="input01" placeholder="등록일"/></td>
				</tr> --%>
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
