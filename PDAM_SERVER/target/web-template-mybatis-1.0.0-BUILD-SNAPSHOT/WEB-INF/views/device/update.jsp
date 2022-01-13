<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
	function formCheck(){
		//숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
		var passwordRegex = /^[A-Za-z0-9]{6,12}$/;

		if($('#constructionIdx').val() == 0){
			alert('시공사를 선택하세요.');
			return false;
		}else if($('#bluetoothNo').val() == ''){
			alert('블루투스 번호를 입력하세요.');
			return false;
		}else if($('#lavelNo').val() == ''){
			alert('자동특정기 번호를 입력하세요.');
			return false;
		}else if($('#machineNumber').val() == ''){
			alert('호기번호를 입력하세요.');
			return false;
		}else if($('#tabletManager').val() == ''){
			alert('WE매니저 이름을 입력하세요.');
			return false;
		}else if($('#weContact').val() == ''){
			alert('매니저 연락처를 입력하세요.');
			return false;
		}else if($('#startDate').val() == ''){
			alert('시작일을 입력하세요.');
			return false;
		}else if($('#endDate').val() == ''){
			alert('종료일을 입력하세요.');
			return false;
		}else if($('#startDate').val() > $('#endDate').val() || $('#startDate').val() == $('#endDate').val()){
			alert('종료일자가 잘못되었습니다.');
			$('#endDate').val("");
			return false;
		}
		
		 if($('#password').val() != '' && $('#confirmPassword').val() != '' ){
				//비밀번호가 입력되었다면
				if(!passwordRegex.test($('#password').val())){
					alert('숫자와 문자 포함  6~12자리 비밀번호를 입력하세요.');
					return false;
				}else if(!passwordRegex.test($('#confirmPassword').val())){
					alert('숫자와 문자 포함  6~12자리 비밀번호를 입력하세요.');
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
	
	function pressContact(){
		$('#isDuplicate').val("false");
	}
	
	function duplicateContactCheck(){
		
		if($('#tabletNo').val() == ''){
			alert('PDAM테블릿 번호를 입력하세요.');
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
						alert("이미 등록된 PDAM테블릿 번호입니다.");
					}else{
						$('#isDuplicate').val("true");
						alert("사용가능한 PDAM테블릿 번호입니다.");
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
					/* var idx = '<c:out value="${domain.constructionIdx}"/>';
					$('#constructionIdx').append("<option value=\"0\">선택</option>");
					$.each(data, function(index, item) {
						$('#constructionIdx').append("<option value='" + item.id + "'>"+ item.name + "</option>");
						
					}); */
					
					$('#constructionIdx').append("<option value=\"0\">선택</option>");
					$.each(data, function(index, item) {
						var idx = '<c:out value="${domain.constructionIdx}"/>';
						if(item.id == idx){
							$('#constructionIdx').append("<option selected=\"selected\" value='" + item.id + "'>"+ item.name + "</option>");
						}else{
							$('#constructionIdx').append("<option value='" + item.id + "'>"+ item.name + "</option>");
						}
					});
					$('#constructionIdx').attr('disabled', 'true');
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
	<%-- <div class="tab_menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/device/list"><img src="${pageContext.request.contextPath}/images/icon04_off.png" class="icon03">기기리스트</a></li>
			<li class="on"><a href="${pageContext.request.contextPath}/device/regist"><img src="${pageContext.request.contextPath}/images/icon04_on.png" class="icon03">기기등록</a></li>
		</ul>
	</div> --%>
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
					<td>시공사</td>
					<td>
						<select id="constructionIdx" class="input01"  name="constructionIdx">
						</select>
					</td>
				</tr>
				<tr>
					<td>자동측정기 S/N</td>
					<td>
						<form:input path="lavelNo" class="input01"/>
					</td>
				</tr>
				<tr>
					<td>호기</td>
					<td><form:input path="machineNumber" class="input01"/></td>
				</tr>
				<tr>
					<td>블루투스 No</td>
					<td><form:input path="bluetoothNo" class="input01"/></td>
				</tr>
				<tr>
					<td>WE매니저</td>
					<td><form:input path="tabletManager" class="input01" /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><form:input path="weContact" class="input01" /></td>
				</tr>
				<tr>
					<td>태블릿 ID</td>
					<td><form:input path="tabletNo" class="input01"  disabled="true" onkeypress="javascript:pressContact();"/>
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
			<input type="submit" class="button02 button05" value="정보수정" onclick="return formCheck();">
		</div>
		</form:form>
	</div>
	<!--table01_content end-->
</div>
