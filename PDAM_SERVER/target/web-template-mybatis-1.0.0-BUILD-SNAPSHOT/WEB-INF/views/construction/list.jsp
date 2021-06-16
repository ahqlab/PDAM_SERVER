<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$( document ).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
    
    
    /* $("#conductSel").change(function(){
    	
    	var result = confirm('상태를 변경하시겠습니까?');
        alert($(this).text());
    }); */
    
});

function conductSel(idx, selectVal){

	var result = confirm((selectVal == '0' ? '\'시행\'' : '\'종료\'') +  ' 상태로 변경하시겠습니까?');
	if(result){
		
		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/construction/update/conduct",
			data: {
				id : idx
				, conduct : selectVal
			}, 
			dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
			success : function(data) {
				if(data){
					alert('변경이 완료되었습니다.');
				}
			},
			complete : function(data) {
			},
			error : function(xhr, status, error) {
			}
		}); 	
	}
	
	
}


function doDelete(idx){
	var result = confirm("삭제하시겠습니까?");
	if(result){
		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/construction/doDelete",
			data: {
				id : idx
			}, 
			dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
			success : function(data) {
				if(data == true){
					alert('삭제되었습니다.');
					history.go(0);
				}
			},
			complete : function(data) {
			},
			error : function(xhr, status, error) {
			}
		}); 
		return;
	}
	return;
}
</script>
<div class="right_content">

	<div class="tab_menu">
		<ul>
			<li class="on"><a href="${pageContext.request.contextPath}/construction/list"><img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">협력사리스트</a></li>
			<li><a href="${pageContext.request.contextPath}/construction/regist"><img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">협력사등록</a></li>
		</ul>
	</div>
	<!--table01_content-->
	<div class="table01_content">
		<!--search_div-->
		<div class="search_div">
			<form:form id="searchForm" commandName="domainParam" method="POST">
				<form:hidden path="currentPage"/>
				<!--등록일 검색폼-->
				<div class="search_form01">
					<form:input path="startDate" class="input01"/><span>~</span><form:input path="endDate" class="input01"/> 
				</div>
				<!--등록일 검색폼 end-->
	
				<!--제목 검색폼-->
				<div class="search_form02">
					<form:select id="searchForm" class="select01" path="searchField">
						<form:option value="">선택</form:option>
	                	<form:option value="name">협력사</form:option>
	                	<form:option value="location">현장명</form:option>
	                    <form:option value="manager">현장담당자</form:option>
	                    <form:option value="contact">연락처</form:option>
	                    <form:option value="userId">아이디</form:option>
					</form:select>
					<form:input path="searchWord" class="input01"/>
					<img id="submitBtn" src="${pageContext.request.contextPath}/images/search.png" class="search_icon">
				</div>
			<!--제목 검색폼 end-->
			</form:form>
		</div>
		<!--search_div end-->
		<!--table 리스트-->
		<div class="table_list">
			<table class="table01">
				<tr>
					<th style="width: 8%;">등록일</th>
					<th style="width: 10%;">협력사</th>
					<th style="width: 10%;">현장명</th>
					<th>현장주소</th>
					<th style="width: 8%;">현장담당자</th>
					<th style="width: 10%;">연락처</th>
					<th style="width: 8%;">아이디</th>
					<th style="width: 5%;">기기등록</th>
					<th style="width: 5%;">정보변경</th>
					<c:choose>
						<c:when test="${sessionInfo.role == 0}">
							<th style="width: 5%;">상태</th>
						</c:when>
					</c:choose>
					
				</tr>
				<c:forEach var="domain" items="${domainList}"  varStatus="status">
					<tr onclick="">
						<td>${domain.createDate}</td>
						<td><a href="${pageContext.request.contextPath}/device/list?constructionIdx=${domain.id}">${domain.name}</a></td>
						<td>${domain.location}</td>
						<td>${domain.address}</td>
						<td>${domain.manager}</td>
						<td>${domain.contact}</td>
						<td>${domain.userId}</td>
						<td><a href="${pageContext.request.contextPath}/device/regist2?constructionIdx=${domain.id}">[기기등록]</a></td>
						<td><a href="${pageContext.request.contextPath}/construction/update?id=${domain.id}">[정보변경]</a></td>
						<c:choose>
							<c:when test="${sessionInfo.role == 0}">
								<td>
									<!-- <a href="javascript:doDelete('${domain.id}')">[삭제]</a> -->
									<select id="conductSel" class="select01" style="height:30px;" onchange="conductSel('${domain.id}', this.value)">
										<option value="0" ${domain.conduct == 0 ? 'selected="selected"' : '' }>시행</option>
										<option value="1" ${domain.conduct == 1 ? 'selected="selected"' : '' }>종료</option>
									</select>
								</td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
				<c:choose>
					<c:when test="${fn:length(domainList) == 0}">
						<tr>
							<c:choose>
								<c:when test="${sessionInfo.role == 0}">
									<td colspan="10">등록된 데이터가 없습니다.</td>
								</c:when>
								<c:otherwise>
									<td colspan="9">등록된 데이터가 없습니다.</td>
								</c:otherwise>
							</c:choose>
							
						</tr>
					</c:when>
				</c:choose>
			</table>
		</div>
		<!--table 리스트 end-->
		<div class="white_btn">
			<a href="${pageContext.request.contextPath}/construction/regist">등록</a>
		</div>
		<!--페이지 넘버-->
		<!-- <div class="page_number">
			<ul>
				<li class=""><a href="#">처음</a></li>
				<li><a href="#">이전</a></li>
				<li class="on"><a href="#">1</a></li>
				<li><a href="#">다음</a></li>
				<li><a href="#">맨끝</a></li>
			</ul>
		</div> -->
		<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
		<!--페이지 넘버end-->
	</div>
	<!--table01_content end-->
</div>