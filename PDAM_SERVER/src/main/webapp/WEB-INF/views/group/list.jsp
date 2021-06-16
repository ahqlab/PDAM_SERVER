<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script>
$( document ).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
  });
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li class="on"><a href="${pageContext.request.contextPath}/group/list">
				<img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">시공사리스트</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/group/regist">
					<img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">시공사등록</a>
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
				<%-- <div class="search_form01">
					<form:input path="startDate" class="input01"/><span>~</span><form:input path="endDate" class="input01"/> 
				</div> --%>
				<!--등록일 검색폼 end-->
				<!--제목 검색폼-->
				<div class="search_form02">
					<form:select id="searchForm" class="select01" path="searchField">
						<%-- <form:option value="">선택</form:option> --%>
	                	<form:option value="groupName">시공사</form:option>
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
					<th style="width: 10%;">시공사</th>
					<th style="width: 5%;">협력사 ${constructionCount}</th>
					<th style="width: 5%;">운영장비 ${deviceCount}</th>
					<th></th>
				</tr>
				<c:forEach var="domain" items="${domainList}"  varStatus="status">
					<tr onclick="">
						<td>
							<a href='${pageContext.request.contextPath}/construction/list?groupIdx=${domain.idx}'>${domain.groupName}</a>
						</td>
						<td>${domain.cprtCompanyAmount}&nbsp;개</td>
						<td>${domain.deviceAmount}&nbsp;대</td>
						<td></td>
					</tr>
				</c:forEach>
				<c:choose>
					<c:when test="${fn:length(domainList) == 0}">
						<tr>
							<c:choose>
								<c:when test="${sessionInfo.role == 0}">
									<td colspan="4">등록된 데이터가 없습니다.</td>
								</c:when>
								<c:otherwise>
									<td colspan="3">등록된 데이터가 없습니다.</td>
								</c:otherwise>
							</c:choose>
							
						</tr>
					</c:when>
				</c:choose>
			</table>
		</div>
		<!--table 리스트 end-->
		<div class="white_btn">
			<a href="${pageContext.request.contextPath}/group/regist">등록</a>
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