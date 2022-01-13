<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$( document ).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
  });
function deleteConfirm(url){
	var result = confirm("삭제하시겠습니까?");
	if(result){
		document.location.href=url;
	}
}
</script>
<div class="right_content">
	<!--table01_content-->
	<div class="table01_content">
		<!--search_div-->
		<div class="search_div">
			<form:form id="searchForm" commandName="domainParam" method="POST">
				<form:hidden path="currentPage"/>
				<!--등록일 검색폼-->
				<!-- <div class="search_form01">
					<form:input path="startDate" class="input01"/><span>~</span><form:input path="endDate" class="input01"/> 
				</div> -->
				<!--등록일 검색폼 end-->
	
				<!--제목 검색폼-->
				
				<div class="search_form02">
					<form:input path="startDate" class="input01"/><span>~</span><form:input path="endDate" class="input01"/> 
					
					<form:select id="searchForm" class="select01" path="searchField">
						<form:option value="">선택</form:option>
	                	<form:option value="pileType">종류</form:option>
	                    <form:option value="pileStandard">규격</form:option>
	                    <form:option value="fileWeight">두께</form:option>
					</form:select>
					<form:input path="searchWord" class="input01"/>
					<img id="submitBtn" src="${pageContext.request.contextPath}/images/search.png" class="search_icon">
					
				</div>
				 
			</form:form>
			<!--제목 검색폼 end-->
		</div>
		<!-- Modal -->
		<!--search_div end-->
		<!--table 리스트-->
		<div class="table_list">
			<table class="table01">
				<tr>
					<th style="width: 5%;">등록일</th>
					<th style="width: 5%;">종류</th>
					<th style="width: 5%;">규격</th>
					<th style="width: 5%;">두께</th>
					<th>반입수량</th>
					<th style="width: 5%;">수정</th>
					<th style="width: 5%;">삭제</th>
				</tr>
				<c:forEach var="domain" items="${domainList}" varStatus="status">
				<tr>
					<td>${domain.registDate}</td>
					<td>${domain.pileType}</td>
					<td>${domain.pileStandard}</td>
					<td>${domain.fileWeight}</td>
					<td>${domain.meterof51} EA</td>
					<td><a href='${pageContext.request.contextPath}/fileinventory/update2?constructionIdx=${param.constructionIdx}&id=${domain.fiIdx}'>수정</a></td>
					<td><a href="javascript:deleteConfirm('${pageContext.request.contextPath}/fileinventory/delete?id=${domain.fiIdx}')">삭제</a></td>
				</tr>
				</c:forEach>
			</table>
			
			
		</div>
		<!--table 리스트 end-->
		<div>
			<input class="white_btn" type="button" onclick="location.href='${pageContext.request.contextPath}/fileinventory/regist2?constructionIdx=${param.constructionIdx}&pileType=PHC'" value="등록"/>
		</div>
	<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
		<!--페이지 넘버end-->
	</div>
</div>
