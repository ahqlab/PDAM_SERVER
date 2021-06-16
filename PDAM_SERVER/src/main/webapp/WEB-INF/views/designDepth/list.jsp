<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp"%>
<script type="text/javascript">
$( document ).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
});

function doDelete(){
	var index = getCheckdCheckboxIndex();
	if(index != null){
		var ddIdx = document.getElementsByName("ddIdx");
		var idx = Number(ddIdx[index].value);
		
		var result = confirm("정말 삭제하시겠습니까? 삭제 시 기록은 저장되지 않으며 복구할 수 없습니다.");
		if(result){
			jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/designDepth/doDelete",
				data: {
					ddIdx : idx
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
		}
	}else{
		alert('선택된 항목이 없습니다.');
	}

}

function doOpenCheck(chk, rowindex) {
	
	var obj = document.getElementsByName("delCheck");
	var index = rowindex;
	
	for (var l = 0; l < obj.length; l++) {
		if (obj[l] != chk) {
			obj[l].checked = false;

		}
	}
}

function getCheckdCheckboxIndex() {
	var obj = document.getElementsByName("delCheck");
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			return i;
		}
	}
	return null;
}

</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li class="on">
				<a href="#">
					<img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">설계심도
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/designDepth/registAj?deviceIdx=${param.deviceIdx}"> 
					<img src="${pageContext.request.contextPath}/images/icon03_off.png" class="icon03">등록
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
			</form:form>
		</div>
		<!--search_div end-->
		<!--table 리스트-->
		<div class="table_list">
			<table class="table01" id="designDepthTable">
				<thead>
					<tr>
						<th style="width: 5%;">선택</th>
						<th style="width: 10%;">파일타입</th>
						<th style="width: 10%;">공법</th>
						<th style="width: 10%;">위치</th>
						<th style="width: 10%;">파일번호</th>
						<th style="width: 10%;">파일규격</th>
						<th style="width: 10%;">설계심도</th>
					</tr>
				</thead>
				<tbody id="designDepthTbpdy">
				<c:forEach var="domain" items="${domainList}"  varStatus="status">
					<tr>
						<td>
							<input type="checkbox" name="delCheck" id="delCheck" onclick="doOpenCheck(this, this.parentNode.parentNode.rowIndex);">
							<input type="hidden" name="ddIdx" id="ddIdx" value="${domain.ddIdx}">
						</td>
						<td>${domain.pileType}</td>
						<td>${domain.method}</td>
						<td>${domain.location}</td>
						<td>${domain.pileNo}</td>
						<td>${domain.pileStandard}</td>
						<td>${domain.designDepth}</td>						
					</tr>
				</c:forEach>
				</tbody>
				<c:choose>
					<c:when test="${fn:length(domainList) == 0}">
						<tr>
							<td colspan="6">등록된 데이터가 없습니다.</td>
						</tr>
					</c:when>
				</c:choose>
			</table>
		</div>
		<br/>
		<br/>
		<!--table 리스트 end-->
		<div class="white_btn">
			<a href="javascript:doDelete();">삭제</a>
		</div>
		<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
		<!--페이지 넘버end-->
	</div>
	<!--table01_content end-->
</div>