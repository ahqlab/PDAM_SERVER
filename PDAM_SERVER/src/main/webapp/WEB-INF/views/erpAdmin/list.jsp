<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$( document ).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
});
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li class="on">
				<a href="${pageContext.request.contextPath}/erpAdmin/list">
					<img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">관리자리스트
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/erpAdmin/regist">
					<img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">관리자등록
				</a>
			</li>
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
					<th>출력일</th>
					<th style="width: 10%;">소장</th>
					<th style="width: 10%;">공무</th>
					<th style="width: 10%;">공사</th>
					<th style="width: 10%;">안전</th>
					<th style="width: 10%;">측량</th>
					<th style="width: 10%;">두부정리</th>
				</tr>
				<c:forEach var="domain" items="${domainList}"  varStatus="status">
					<tr onclick="">
						<td>${domain.printDate}</td>
						<td>${domain.manager}</td>
						<td>${domain.publicService}</td>
						<td>${domain.construction}</td>
						<td>${domain.safety}</td>
						<td>${domain.measurement}</td>
						<td>${domain.pileCuttingWork}</td>
					</tr>
				</c:forEach>
				<c:choose>
					<c:when test="${fn:length(domainList) == 0}">
						<tr>
							<td colspan="7">등록된 데이터가 없습니다.</td>
						</tr>
					</c:when>
				</c:choose>
			</table>
		</div>
		
		<!--table 리스트 end-->
		<div class="white_btn">
			<a href="${pageContext.request.contextPath}/construction/regist">등록</a>
		</div>
		<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
		<!--페이지 넘버end-->
	</div>
	<!--table01_content end-->
</div>