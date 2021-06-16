<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp"%>
<script type="text/javascript">
$( document ).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
});


function delExcelRow(){
	
}

function uploadExcelFile(){
	//var formData = $("#excelUploadForm").serialize();
	var formData = new FormData($("#excelUploadForm")[0]);
	$.ajax({             
    	type: "POST",          
    	contentType: 'multipart/form-data', 
    	 dataType: 'json',
        url: "${pageContext.request.contextPath}/designDepth/uploadExcel",        
        data: formData,          
        processData: false,    
        contentType: false,      
        cache: false,           
        timeout: 600000,       
        success: function (data) { 
        	var html = '';
        	for(var i = 0; i < data.length; i++){
        			html += '<tr>';
        			html += '<td><input type="checkbox" name="delCheck" id="delCheck"></td>';
        			html += '<td>'+data[i].pileType+'</td>';
        			html += '<td>'+data[i].method+'</td>';
        			html += '<td>'+data[i].location+'</td>';
        			html += '<td>'+data[i].pileNo+'</td>';
        			html += '<td>'+data[i].pileStandard+'</td>';
        			html += '<td>'+data[i].designDepth+'</td>';
        			html += '</tr>';
    		}
        	$("#designDepthTbpdy").empty();	
        	$("#designDepthTbpdy").append(html);	
        },          
        error: function (e) {  
        	console.log("ERROR : ", e);     
            $("#btnSubmit").prop("disabled", false);    
            alert("fail");      
         }     
	});  
	
	
	return false;
}


function onRegist(){
	//alert('${param.deviceIdx}');
	var json = JSON.stringify(makeJsonFromTable('designDepthTable', '${param.deviceIdx}'));
	alert("json : " + json);
	var result = confirm("입력하시겠습니까?");
	if(result){
		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/designDepth/registList",
			data: JSON.stringify(makeJsonFromTable('designDepthTable', '${param.deviceIdx}')),
			dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
			contentType : "application/json",
			success : function(data) {
				if(data == true){
					
					alert(data);
					//alert('${pageContext.request.contextPath}/designDepth/list?deviceIdx=${param.deviceIdx}');
					document.location.href= '${pageContext.request.contextPath}/designDepth/list?deviceIdx=${param.deviceIdx}';
				}
			},
			complete : function(data) {
			},
			error : function(xhr, status, error) {
			}
		});
		return; 
	}else{
		return;
	}
}



function rowCheDel() {
	var $obj = $("input[name='delCheck']");
	var checkCount = $obj.size();
	for (var i = 0; i < checkCount; i++) {
		if ($obj.eq(i).is(":checked")) {
			$obj.eq(i).parent().parent().remove();
		}
	}
}
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/designDepth/list?deviceIdx=${param.deviceIdx}">
					<img src="${pageContext.request.contextPath}/images/icon03_on.png" class="icon03">설계심도
				</a>
			</li>
			<li class="on">
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
			<!--<form:form id="searchForm" commandName="domainParam" method="POST">
				<form:hidden path="currentPage"/>
			</form:form>-->
			<form id="excelUploadForm"
				action="${pageContext.request.contextPath}/designDepth/uploadExcel"
				method="POST" enctype="multipart/form-data">
				<div class="search_form01">
					<input type="file" id="reportFile" name="reportFile" /><input
						type="button" class="input01" value="업로드"
						onclick="return uploadExcelFile()">
				</div>
			</form>
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

				</tbody>
				<!-- <tr onclick="">
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
				</tr> -->
			</table>
		</div>
		<br/>
		<br/>
		<!--table 리스트 end-->
		<!--<div style="float: left;">
			<div class="white_btn">
			<a href="javascript:onRegist();">등록</a>
		</div>
		<div class="white_btn" >
			<a href="javascript:onRegist();">삭제</a>
		</div>
		</div>-->
		<div align="right">
			<input type="button" class="nomal_btn" value="등록" onclick="avascript:onRegist();"> 
			<input type="button" class="nomal_btn" value="삭제" onclick="javascript:rowCheDel();"> 
		</div>
	
		<!--페이지 넘버end-->
	</div>
	<!--table01_content end-->
</div>