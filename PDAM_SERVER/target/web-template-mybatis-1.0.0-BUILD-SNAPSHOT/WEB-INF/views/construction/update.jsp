<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$(document).ready(
	function() {
		jQuery.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/group/get/list",
			dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
			success : function(data) {
				if(data.length > 0){
					$('#groupIdx').append("<option value=\"0\">선택</option>");
					$.each(data, function(index, item) {
						var groupIdx = '${domain.groupIdx}';
						if(groupIdx == item.idx){
							$('#groupIdx').append("<option selected value='" + item.idx + "'>"+ item.groupName + "</option>");
							//$('#groupIdx').attr('disabled', 'disabled');
						}else{
							$('#groupIdx').append("<option value='" + item.idx + "'>"+ item.groupName + "</option>");
						}
						
					});
				}
			},
			complete : function(data) {
				// 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
				//$('#ctmIdx').append("<option value=\"0\">선택</option>");
				//alert("서버와 통신에 실패했습니다. 계속 실패할 경우 관리자에게 문의하세요.");
			},
			error : function(xhr, status, error) {
				$('#groupIdx').append("<option value=\"0\">선택</option>");
				alert("에러발생");
			}
		});
	});

	function formCheck(){
		var groupIdx = $("#groupIdx option:selected").val().trim();		
		if(groupIdx == 0){
			alert('시공사를 선택하세요.');
			return false;
		}else if($('#name').val() == ''){
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
		}else if($('#contact').val() == ''){
			alert('연락처를 입력하세요.');
			return false;
		}
		
		 if($('#password').val() != '' && $('#confirmPassword').val() != '' ){
			//비밀번호가 입력되었다면
			if($('#password').val().length < 4){
				alert('4자리 이상의 비밀번호를 입력하세요.');
				return false;
			}else if($('#confirmPassword').val().length < 4){
				alert('4자리 이상의 비밀번호를 입력하세요.');
				$('#confirmPassword').focus();
				return false;
			}else if($('#password').val() != $('#confirmPassword').val()){
				alert('비밀번호를 다름니다. 비밀번호를 확인하세요.');
				$('#confirmPassword').focus();
				return false;
			}
		}else{
			if($('#password').val() != ''){
				alert('확인 비밀번호를 입력하세요.');
				$('#confirmPassword').focus();
				return false;
			}else if($('#confirmPassword').val() != ''){
				alert('비밀번호를 입력하세요.');
				$('#password').focus();
				return false;
			}
		}
		return true;
	}
</script>
<div class="right_content">
	<c:choose>
		<c:when test="${sessionInfo.role == 0}">
			<%-- <div class="tab_menu">
				<ul>
					<li><a href="${pageContext.request.contextPath}/construction/list"><img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">협력사리스트</a></li>
					<li class="on"><a href="#"><img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">협력사등록</a></li>
				</ul>
			</div> --%>
		</c:when>
	</c:choose>
	<!--table01_content-->
	<div class="table01_content">
		<!--sub_title-->
		<div class="sub_title">
			정보변경
		</div>
		<!--sub_title end-->
		
		<!--table_white-->
		<form:form method="POST" id="customerForm" commandName="domain" >
		<div class="table_white_form">
			<table class="table_white">
				<tr>
					<td>
						시공사
					</td>
					<td>
						<select id="groupIdx" class="input01" style="color: #000000;" name="groupIdx">
						</select>
					</td>
				</tr>
				<tr>
					<td>협력사</td>
					<td>
						<form:input path="name" class="input01"/>
					</td>
				</tr>
				<tr>
					<td>현장명</td>
					<td><form:input path="location" class="input01"/></td>
				</tr>
				<tr>
					<td>현장주소</td>
					<td><form:input path="address" class="input01"/></td>
				</tr>
				<tr>
					<td>현장담당자</td>
					<td><form:input path="manager" class="input01"/></td>
				</tr>
				<tr>
				<td>연락처</td>
					<td>
						<form:input path="contact" class="input02"/>
					</td>
				</tr>
					<tr>
					<td>아이디</td>
					<td>
						<form:input path="userId" class="input02" disabled="true" />
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><form:password path="password" class="input01"/></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id="confirmPassword" name="confirmPassword" class="input01"></td>
				</tr>
				<tr>
					<td>보안코드</td>
					<td><form:input path="secretCode" class="input01"/></td>
				</tr>
			</table>
		</div>
		<!--table_white-->
		<div class="btn_box">
			<input type="submit" class="button02 button05" value="정보수정" onclick="return formCheck();">
		</div>
		</form:form>
	</div>
	<!--table01_content end-->
</div>
