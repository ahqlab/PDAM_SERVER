<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$( document ).ready( function() 
	{
	    $('#submitBtn').click( function() {
	    	$('#searchForm').submit();	    	
	    });
	    getConstructionName();
	    getPileTypeList();
  	}
);


function doDelete(idx){
	var result = confirm("삭제하시겠습니까?");
	if(result){
		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/device/doDelete",
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

function changeInfo(id){
	location.href='${pageContext.request.contextPath}/device/update?id=' + id
}

function getConstructionName(){
	var idx = ${param.constructionIdx};
	jQuery.ajax({
		type : "POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		url : "${pageContext.request.contextPath}/construction/get/name",
		data: {
			id : idx
		}, 
		success : function(data) {
			$('#constructionSetName').text(data);
		},
		complete : function(data) {
		},
		error : function(xhr, status, error) {
		}
	}); 
}
function getPileTypeList(){
	
	var idx = ${param.constructionIdx};
	jQuery.ajax({
		type : "POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		url : "${pageContext.request.contextPath}/fileinventory/get/pile/type/list",
		data: {
			constructionIdx : idx
		}, 
		success : function(data) {				
			 $.each(data, function (i, item) {
				 if(item.pileType == "PHC"){
					 var option = item.pileType + " " + item.pileStandard;
					 //var valueOption = item.pileType + "|" + item.pileStandard;
				 }else{
					 var option = item.pileType + " " + item.fileWeight + " " + item.pileStandard;
					// var valueOption = item.pileType + "|" + item.fileWeight + "|" + item.pileStandard;
				 }
                 $("#select1").append("<option class='text-success text-center' value='"+option+"'>"+option+"</option>")
        	});
		},
		complete : function(data) {
		},
		error : function(xhr, status, error) {
		}
	}); 
}
function fileChange(value){
	var constructionIdx = ${param.constructionIdx};
	location.href = '${pageContext.request.contextPath}/file/using/chart/download/excel?constructionIdx=' + constructionIdx + '&pile=' + value + '&dateTime=' + value;
	$('#select1 option:eq(0)').prop('selected', true);
}

function conductSel(idx, selectVal){
	
	var alertMsg = "";
	if(selectVal == '0'){
		alertMsg = "본사";
	}else if(selectVal == '1'){
		alertMsg = "종료";
	}else if(selectVal == '2'){
		alertMsg = "가맹";
	}

	var result = confirm(alertMsg +  ' 상태로 변경하시겠습니까?');
	if(result){
		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/device/update/conduct",
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

function setQuantity(){
	var cIdx = ${param.constructionIdx};
	var value = $('#quantity').val();
	jQuery.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/quantity/set",
		async : false,  // 요청 시 동기화 여부. 기본은 비동기(asynchronous) 요청 (default: true)
		data: {
			constructionIdx : cIdx
			, quantity : value
		}, 
		success : function(data) {				
			 if(data > 0){
				// alert(data);
				 $('#searchForm').submit();	
			 }else{
				 alert('저장에 실패했습니다. 관리자에게 문의하세요.');
			 }
		},
		complete : function(data) {
			
		},
		error : function(xhr, status, error) {
			
		}
	}); 
	
	alert(result);
}

function getQuantity(){
	var cIdx = ${param.constructionIdx};
	alert('cIdx : '  + cIdx);
	jQuery.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/quantity/get",
		async : false,  // 요청 시 동기화 여부. 기본은 비동기(asynchronous) 요청 (default: true)
		data: {
			constructionIdx : cIdx
		}, 
		success : function(data) {				
			 if(data.constructionIdx = 'undefined'){
				 $('#workSummary').html('공정률 0%&nbsp;&nbsp;&nbsp;남은수량 0 본 &nbsp;&nbsp;&nbsp;총 작업수량 <input type="text" class="input01" style="width: 100px;">&nbsp;본&nbsp;<input type="button" value="저장" onclick="javascript:setQuantity();">');
				 
			 }else{
				 alert('????');
			 }
		},
		complete : function(data) {
			
		},
		error : function(xhr, status, error) {
			
		}
	}); 
}


function getQuantityResult(){
	var cIdx = ${param.constructionIdx};
	
	jQuery.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/quantity/get",
		async : false,  // 요청 시 동기화 여부. 기본은 비동기(asynchronous) 요청 (default: true)
		data: {
			constructionIdx : cIdx
		}, 
		success : function(data) {	
			alert(data);
			 if(data.constructionIdx = 'undefined'){
				 return false;
			 }else{
				 return true;
			 }
		},
		complete : function(data) {
			
		},
		error : function(xhr, status, error) {
			
		}
	}); 
	return false;
}

</script>
<div class="right_content">
	<c:choose>
	<c:when test="${sessionInfo.role == 0}">
	<%-- <div class="tab_menu">
		<ul>
			<li class="on"><a href="${pageContext.request.contextPath}/device/list"><img src="${pageContext.request.contextPath}/images/icon04_on.png" class="icon03">기기등록 리스트</a></li>
			<li><a href="${pageContext.request.contextPath}/device/regist"><img src="${pageContext.request.contextPath}/images/icon04_off.png" class="icon03">기기등록</a></li>
		</ul>
	</div> --%>
	</c:when>
	</c:choose>
	<!--table01_content-->
	<div class="table01_content">
		<!--search_div-->
		<div class="search_div">
			<form:form id="searchForm" commandName="domainParam" method="POST">
				<form:hidden path="currentPage"/>
				<!--등록일 검색폼-->
				<div id="constructionSetName"  name="constructionSetName" class="search_form01" style="float: left; font-size : 30px; color: #ffffff;"></div>
				
				<div class="search_form01" style="float: right;">   
					<select  class="input01" id="select1" name="select1" onchange="javascript:fileChange(this.value);">
						<option class="text-success" selected disabled value=""><h6>총 파일집계표 ▼</h6></option>
					</select>
				</div>
			</form:form>
			<!--제목 검색폼 end-->
		</div>
		<!-- Modal -->
		<div style="font-size : 22px;" id="workSummary" name="workSummary">
			공정률 ${totalWorkQuantity.processRate}%
			&nbsp;&nbsp;&nbsp;남은수량 ${totalWorkQuantity.quantityLeft} 공
			&nbsp;&nbsp;&nbsp;총 작업수량 <input type="text" class="input01" id="quantity" name="quantity" value="${totalWorkQuantity.quantity}"style="width: 100px;">&nbsp;공
			<input type="button" value="저장" onclick="javascript:setQuantity();">
		</div>
		<br>
			<!--search_div end-->
			<!--table 리스트-->
			<div class="table_list">
				<table class="table01"  id="userListTable">
					<tr>
						<th style="width: 10%;">호기</th>
						<th style="width: 10%;">태블릿 ID</th>
						<th style="width: 10%;">블루투스 No</th>
						<th style="width: 10%;">자동측정기 S/N</th>
						<th style="width: 8%;">WE매니저</th>
						<th style="width: 8%;">연락처</th>
						<th style="width: 8%;">시작일</th>
						<th style="width: 8%;">종료일</th>
						<c:choose>
							<c:when test="${sessionInfo.role == 0}">
								<th style="width: 5%;">정보변경</th>
								<th style="width: 5%;">상태</th>
							</c:when>
						</c:choose>
					</tr>
					<c:forEach var="domain" items="${domainList}" varStatus="status">
						<c:choose>
							<c:when test="${sessionInfo.role == 0}">
								<tr>
									<td><a
										href='${pageContext.request.contextPath}/report/list?id=${domain.id}&constructionIdx=${domain.constructionIdx}&type=date&mode=simple'>${domain.machineNumber}</a></td>
									<td>${domain.tabletNo}</td>
									<td>${domain.bluetoothNo}</td>
									<td>${domain.lavelNo}</td>
									<td>${domain.tabletManager}</td>
									<td>${domain.weContact}</td>
									<td>${domain.startDate}</td>
									<td>${domain.endDate}</td>
									<td><a href="javascript:changeInfo('${domain.id}')">[정보변경]</a></td>
									<td>
										<select id="conductSel" class="select01" style="height:30px;" onchange="conductSel('${domain.id}', this.value)">
											<option value="0" ${domain.conduct == 0 ? 'selected="selected"' : '' }>본사</option>
											<option value="2" ${domain.conduct == 2 ? 'selected="selected"' : '' }>가맹</option>
											<option value="1" ${domain.conduct == 1 ? 'selected="selected"' : '' }>종료</option>
										</select>
									</td>
									<%-- <td><a href="javascript:doDelete('${domain.id}')">[삭제]</a></td> --%>
								</tr>
							</c:when>
							<c:when test="${sessionInfo.role == 2}">
								<tr>
									<td><a
										href='${pageContext.request.contextPath}/report/list?id=${domain.id}&constructionIdx=${param.constructionIdx}&type=date&mode=simple'>${domain.machineNumber}</a></td>
									<td>${domain.tabletNo}</td>
									<td>${domain.bluetoothNo}</td>
									<td>${domain.lavelNo}</td>
									<td>${domain.tabletManager}</td>
									<td>${domain.weContact}</td>
									<td>${domain.startDate}</td>
									<td>${domain.endDate}</td>
									<%-- <td><a href="javascript:doDelete('${domain.id}')">[삭제]</a></td> --%>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td><a
										href='${pageContext.request.contextPath}/report/list?id=${domain.id}&type=date&mode=simple'>${domain.machineNumber}</a></td>
									<td>${domain.tabletNo}</td>
									<td>${domain.bluetoothNo}</td>
									<td>${domain.lavelNo}</td>
									<td>${domain.tabletManager}</td>
									<td>${domain.weContact}</td>
									<td>${domain.startDate}</td>
									<td>${domain.endDate}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${fn:length(domainList) == 0}">
							<tr>
								<c:choose>
									<c:when test="${sessionInfo.role == 0}">
										<td colspan="10">등록된 데이터가 없습니다.</td>
									</c:when>
									<c:otherwise>
										<td colspan="8">등록된 데이터가 없습니다.</td>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:when>
					</c:choose>
				</table>
			</div>
			<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
			<!--페이지 넘버end-->
		</div>
		<!--table01_content end-->
</div>
<!-- Modal -->
<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Modal title</h4>
	  </div>
	  <div class="modal-body">
		<select required id="ddlcolors" style="height: 50px;" class="text-success form-control input-sm">
			<option class="text-success" selected disabled value=""><h6>-- 선택 --</h6></option>
			<option class="text-success text-center" value="R">PHC-D 400</option>
			<option class="text-success text-center" value="G">PHC-D 500</option>
			<option class="text-success text-center" value="B">PHC-D 600</option>
		</select>
	  </div>
	  <div class="modal-footer">
	  </div>
	</div>
  </div>
</div> -->