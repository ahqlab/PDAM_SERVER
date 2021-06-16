<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
	function formCheck(){
		//숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
		var passwordRegex = /^[A-Za-z0-9]{6,12}$/;
		
		if($('#constructionIdx').val() == 0){
			alert('시공사를 선택하세요.');
			return false;
		}else if($('#lavelNo').val() == ''){
			alert('라벨기 번호를 입력하세요.');
			return false;
		}else if($('#bluetoothNo').val() == ''){
			alert('블루투스 번호를 입력하세요.');
			return false;
		}else if($('#tabletManager').val() == ''){
			alert('기기 담당자를 입력하세요.');
			return false;
		}else if($('#tabletNo').val() == ''){
			alert('태블릿PC 번호를 입력하세요.');
			return false;
		}else if($('#password').val() == ''){
			alert('비밀번호를 입력하세요.');
			return false;
		}else if(!passwordRegex.test($('#password').val())){
			alert('숫자와 문자 포함  6~12자리 비밀번호를 입력하세요.');
			return false;
		}else if($('#confirmPassword').val() == ''){
			alert('확인 비밀번호를 입력하세요.');
			return false;
		}else if(!passwordRegex.test($('#confirmPassword').val())){
			alert('숫자와 문자 포함  6~12자리 비밀번호를 입력하세요.');
			return false;
		}else if($('#password').val() != $('#confirmPassword').val()){
			alert('비밀번호가 맞지 않습니다. 비밀번호를 확인하세요.');
			return false;
		}else if($('#startDate').val() == ''){
			alert('시작일을 입력하세요.');
			return false;
		}else if($('#endDate').val() == ''){
			alert('종료일을 입력하세요.');
			return false;
		}else if($('#isDuplicate').val() == 'false'){
			alert('연락처 중복확인을 체크하시기 바랍니다.');
			return false;
		}
		
		return true;
	}
	
	function pressContact(){
		$('#isDuplicate').val("false");
	}
	
	function duplicateContactCheck(){
		
		if($('#tabletNo').val() == ''){
			alert('태블릿PC 번호를 입력하세요.');
			$('#tabletNo').focus();
		} else {
			//연락처가 입력되었음.
			jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/device/duplicate/tabletNo/confirm",
				data: { tabletNo: $('#tabletNo').val() }, 
				dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
				success : function(data) {
					if(data.length > 0){
						alert("이미 등록된 태블릿PC 번호입니다.");
					}else{
						$('#isDuplicate').val("true");
						alert("사용가능한 태블릿PC 번호입니다.");
					}
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
				}
			});
		}
		
	}
	
	$(document).ready(
			function() {
			jQuery.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/construction/get/list",
				dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
				success : function(data) {
					// 통신이 성공적으로 이루어졌을 때 이 함수를 타게 된다.
					// TODO
					if(data.length > 0){
						var role = ${sessionInfo.role};
						if(role > 0){
							$.each(data, function(index, item) {
								$('#constructionName').val(item.name);
								$('#constructionIdx').val(item.id);
							});
						}else{
							$('#constructionIdx').append("<option value=\"0\">선택</option>");
							$.each(data, function(index, item) {
								$('#constructionIdx').append("<option value='" + item.id + "'>"+ item.name + "</option>");
								
							});
						}
						
					}
					
				},
				complete : function(data) {
					// 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
					//$('#ctmIdx').append("<option value=\"0\">선택</option>");
					//alert("서버와 통신에 실패했습니다. 계속 실패할 경우 관리자에게 문의하세요.");
				},
				error : function(xhr, status, error) {
					$('#constructionIdx').append("<option value=\"0\">선택</option>");
					alert("에러발생");
				}
			});
		});
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/device/list"><img src="${pageContext.request.contextPath}/images/icon04_off.png" class="icon03">기기리스트</a></li>
			<li class="on"><a href="${pageContext.request.contextPath}/device/regist"><img src="${pageContext.request.contextPath}/images/icon04_on.png" class="icon03">기기등록</a></li>
		</ul>
	</div>
	<!--table01_content-->
	<div class="table01_content">
		<!--sub_title-->
		<div class="sub_title">
			기기 등록
		</div>
		<!--sub_title end-->
		
		<!--table_white-->
		<form:form method="POST" id="customerForm" commandName="domain" >
		<div class="table_white_form">
			
			<table class="table_white">
				<tr>
					<td>시공사</td>
					<td>
						<c:choose>
							<c:when test="${sessionInfo.role gt 0}">
								<input type="text" disabled="disabled" class="input01" id="constructionName" name="constructionName"   >
								<input type="hidden" id="constructionIdx" name="constructionIdx">
							</c:when>
							<c:otherwise>
								<select id="constructionIdx" disabled="disabled" name="constructionIdx">
								</select>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>라벨기 번호</td>
					<td>
						<form:input path="lavelNo" class="input01"/>
					</td>
				</tr>
				<tr>
					<td>블루투스 번호</td>
					<td><form:input path="bluetoothNo" class="input01"/></td>
				</tr>
				<tr>
					<td>기기 담당자</td>
					<td><form:input path="tabletManager" class="input01" /></td>
				</tr>
					<tr>
					<td>태블릿PC 번호</td>
					<td><form:input path="tabletNo" class="input02" onkeypress="javascript:pressContact();"/>
					<input type="button" class="button02 button05" value="중복확인" onclick="javascript:duplicateContactCheck();">
					<input type="hidden" id="isDuplicate" name="isDuplicate" value="false">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><form:password path="password" class="input01"/></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id="confirmPassword" name="confirmPassword" class="input01"/></td>
				</tr>
				<tr>
					<td>시작일</td>
					<td><form:input path="startDate" class="input01"/></td>
				</tr>
					<tr>
					<td>종료일</td>
					<td><form:input path="endDate" class="input01"/></td>
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
