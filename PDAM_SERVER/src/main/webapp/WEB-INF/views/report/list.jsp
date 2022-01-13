<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script type="text/javascript">
$(document).ready( function() {
    $('#submitBtn').click( function() {
    	$('#searchForm').submit();
    });
	
    $("#aa").scroll(function () {
        $("#bb").scrollTop($("#bb").scrollTop());
        $("#bb").scrollLeft($("#bb").scrollLeft());
    });
    $("#bb").scroll(function () {
        $("#aa").scrollTop($("#aa").scrollTop());
        $("#aa").scrollLeft($("#aa").scrollLeft());
    });
    
    /* $("table tbody tr td").on("click", function() {
        $(this).closest("table").find("tr").css({
           backgroundColor: "white"
        });
        $(this).css({
           backgroundColor: "blue"
        });
     }); */
});

function doRestore(){
	var index = getCheckdCheckboxIndex();
	if(index != null){
		var isDel = document.getElementsByName("isDel");
		var selectIsDel = Number(isDel[index].value);
		
		if(selectIsDel == 1){
			var id = document.getElementsByName("id");
			var idx = Number(id[index].value);
			var result = confirm("삭제된 항목을 복구하시겠습니까?");
			if(result){
				if(result){
					jQuery.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/report/doRestore",
						data: {
							id : idx
						}, 
						dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
						success : function(data) {
							if(data == true){
								alert('복구되었습니다.');
								history.go(0);
							}
						},
						complete : function(data) {
						},
						error : function(xhr, status, error) {
						}
					}); 
				}
			}
		}else{
			alert("삭제되지 않은 항목입니다.");
		}
	}else{
		alert('선택된 항목이 없습니다.');
	}
}

function doDelete(){
	var index = getCheckdCheckboxIndex();
	if(index != null){
		var id = document.getElementsByName("id");
		var idx = Number(id[index].value);
		
		var result = confirm("정말 삭제하시겠습니까? 삭제 시 기록은 저장되지 않으며 복구할 수 없습니다.");
		if(result){
			jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/report/doDelete",
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
		}
	}else{
		alert('선택된 항목이 없습니다.');
	}
}

function onClickReportUpdate(){
	
	var role = ${sessionInfo.role};
	var index = getCheckdCheckboxIndex();
	
	if(index != null){
		var isDel = document.getElementsByName("isDel");
		var selectIsDel = Number(isDel[index].value);
		if(selectIsDel == 1){
			alert('삭제된 항목은 수정할 수 없습니다.');
			var obj = document.getElementsByName("selectOne");
			obj[index].checked = false;
			return;
		}
	}else{
		alert('선택된 항목이 없습니다.');
		return;
	}
	
	
	
	var id = document.getElementsByName("id");
	var pileType = document.getElementsByName("pileType");
	var method = document.getElementsByName("method");
	var location = document.getElementsByName("location");
	var pileNo = document.getElementsByName("pileNo");
	var pileStandard = document.getElementsByName("pileStandard");
	
	var drillingDepth = document.getElementsByName("drillingDepth");
	var intrusionDepth = document.getElementsByName("intrusionDepth");
	var hammaT = document.getElementsByName("hammaT");
	var fallMeter = document.getElementsByName("fallMeter");
	var managedStandard = document.getElementsByName("managedStandard");
	var totalPenetrationValue = document.getElementsByName("totalPenetrationValue");
	var avgPenetrationValue = document.getElementsByName("avgPenetrationValue");
	var crossSection = document.getElementsByName("crossSection");
	var hammaEfficiency = document.getElementsByName("hammaEfficiency");
	var modulusElasticity = document.getElementsByName("modulusElasticity");
	var bigo = document.getElementsByName("bigo");
	
	var piece = document.getElementsByName("piece[" + index + "]");
	var pieceId = document.getElementsByName("pieceId[" + index + "]");
	var pieceName = document.getElementsByName("pieceName[" + index + "]");
	
	if(role == 0){
		var penetrations = document.getElementsByName("penetrations[" + index + "]");
		var penetrationsId = document.getElementsByName("penetrationsId[" + index + "]");
		var penetrationsName = document.getElementsByName("penetrationsName[" + index + "]");
	}

	

	if(index != null){
		var pieces = [];
		
		for(var i=0; i<piece.length; i++){
			var onePiece = {
					name: pieceName[i].value != "" ? pieceName[i].value : "null",
					value : piece[i].value != "" ? 	piece[i].value : "0",
					id : Number(pieceId[i].value) != Number(0) ? Number(pieceId[i].value) : Number(0),
					reportIdx :Number(id[index].value)
			};
			pieces.push(onePiece);	
		}
		if(role == 0){
			var penetrationss = [];
			for(var i=0; i<penetrations.length; i++){
				var onePenetrations = {
						name: penetrationsName[i].value != "" ? penetrationsName[i].value : "null",
						value : penetrations[i].value != "" ? 	penetrations[i].value : "0",
						id : Number(penetrationsId[i].value) != Number(0) ? Number(penetrationsId[i].value) : Number(0),
						reportIdx :Number(id[index].value)
				};
				penetrationss.push(onePenetrations);
			}
		}
		
		
		if(role == 0){
			var data = {
					id: Number(id[index].value), 
					pileType: pileType[index].value != "" ? pileType[index].value : "null", 
					method: method[index].value != "" ? method[index].value : "null", 
					location: location[index].value != "" ? location[index].value : "null", 
					pileNo: pileNo[index].value != "" ? pileNo[index].value : "null", 
					pileStandard: pileStandard[index].value != "" ? pileStandard[index].value : "null", 
					piece: pieces, 
					penetrations : penetrationss, 
					drillingDepth: drillingDepth[index].value != "" ? drillingDepth[index].value  : "0", 
					intrusionDepth: intrusionDepth[index].value != "" ? intrusionDepth[index].value  : "0", 
					hammaT: hammaT[index].value != "" ? hammaT[index].value  : "0",
					fallMeter: fallMeter[index].value != "" ? fallMeter[index].value  : "0",
					managedStandard: managedStandard[index].value != "" ? managedStandard[index].value  : "0"
					, totalPenetrationValue : totalPenetrationValue[index].value != "" ? totalPenetrationValue[index].value  : "0"
					, avgPenetrationValue : avgPenetrationValue[index].value != "" ? avgPenetrationValue[index].value  : "0"
					, crossSection : crossSection[index].value != "" ? crossSection[index].value  : "0"
					, bigo : bigo[index].value != "" ? bigo[index].value  : "null"
					, hammaEfficiency : hammaEfficiency[index].value != "" ? hammaEfficiency[index].value  : "0"
					, modulusElasticity : modulusElasticity[index].value != "" ? modulusElasticity[index].value  : "0"
				//,	ultimateBearingCapacity : ultimateBearingCapacity[index].value
	        };
		}else{
			var data = {
					id: Number(id[index].value), 
					pileType: pileType[index].value != "" ? pileType[index].value : "null", 
					method: method[index].value != "" ? method[index].value : "null", 
					location: location[index].value != "" ? location[index].value : "null", 
					pileNo: pileNo[index].value != "" ? pileNo[index].value : "null", 
					pileStandard: pileStandard[index].value != "" ? pileStandard[index].value : "null", 
					piece: pieces, 
					drillingDepth: drillingDepth[index].value != "" ? drillingDepth[index].value  : "0", 
					intrusionDepth: intrusionDepth[index].value != "" ? intrusionDepth[index].value  : "0", 
					hammaT: hammaT[index].value != "" ? hammaT[index].value  : "0",
					fallMeter: fallMeter[index].value != "" ? fallMeter[index].value  : "0",
					managedStandard: managedStandard[index].value != "" ? managedStandard[index].value  : "0"
					, totalPenetrationValue : totalPenetrationValue[index].value != "" ? totalPenetrationValue[index].value  : "0"
					, avgPenetrationValue : avgPenetrationValue[index].value != "" ? avgPenetrationValue[index].value  : "0"
					, crossSection : crossSection[index].value != "" ? crossSection[index].value  : "0"
					, bigo : bigo[index].value != "" ? bigo[index].value  : "null"
					, hammaEfficiency : hammaEfficiency[index].value != "" ? hammaEfficiency[index].value  : "0"
					, modulusElasticity : modulusElasticity[index].value != "" ? modulusElasticity[index].value  : "0"
				//,	ultimateBearingCapacity : ultimateBearingCapacity[index].value
	        };
		}
		
		
		//alert(JSON.stringify(data));
		
		var result = confirm("수정하시겠습니까?");
		if(result){
			jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/report/update/report",
				data: JSON.stringify(data), 
				dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
				contentType : "application/json",
				success : function(data) {
					if(data == true){
						//history.go(0);
						//alert(document.location.href);
						//document.location.href= document.location.href;
						//window.location.reload()

						$('#searchForm').submit();
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
	alert('선택된 항목이 없습니다.');
	return;
}


	function doOpenCheck(chk, rowindex) {
				
		var role = ${sessionInfo.role};
		var obj = document.getElementsByName("selectOne");
		var index = rowindex;
		
		for (var i = 0; i < $('#reportTable tr').length; i++) {
			if (i == rowindex) {
				if (obj[rowindex].checked) {
					var isDel = document.getElementsByName("isDel");
					var selectIsDel = Number(isDel[i].value);
					if(selectIsDel == 1){
						$('#reportTable tr:eq(' + i + ')').css("background-color", "#ff0000");
					}else{
						$('#reportTable tr:eq(' + i + ')').css("background-color", "#8dc5fc");
					}
				} else {
					var isDel = document.getElementsByName("isDel");
					var selectIsDel = Number(isDel[i].value);
					if(selectIsDel == 1){
						$('#reportTable tr:eq(' + i + ')').css("background-color", "#ff0000");
					}else{
						$('#reportTable tr:eq(' + i + ')').css("background-color", "white");
					}
					
				}
			} else {
				//선택하지 않은것
				var isDel = document.getElementsByName("isDel");
				var selectIsDel = Number(isDel[i].value);
				if(selectIsDel == 1){
					$('#reportTable tr:eq(' + i + ')').css("background-color", "#ff0000");
				}else{
					$('#reportTable tr:eq(' + i + ')').css("background-color","white");
				}
			}
		}

		var id = document.getElementsByName("id");
		var pileType = document.getElementsByName("pileType");
		var method = document.getElementsByName("method");
		var location = document.getElementsByName("location");
		var pileNo = document.getElementsByName("pileNo");
		var pileStandard = document.getElementsByName("pileStandard");

		var drillingDepth = document.getElementsByName("drillingDepth");
		var intrusionDepth = document.getElementsByName("intrusionDepth");
		var hammaT = document.getElementsByName("hammaT");
		var fallMeter = document.getElementsByName("fallMeter");
		var managedStandard = document.getElementsByName("managedStandard");
		
		//var ultimateBearingCapacity = document.getElementsByName("ultimateBearingCapacity");
		var crossSection = document.getElementsByName("crossSection");
		var hammaEfficiency = document.getElementsByName("hammaEfficiency");
		var modulusElasticity = document.getElementsByName("modulusElasticity");
		var bigo = document.getElementsByName("bigo");
		
		var pieceId = document.getElementsByName("pieceId[" + index + "]");
		var pieceName = document.getElementsByName("pieceName[" + index + "]");

		for (var j = 0; j < obj.length; j++) {

			var pie = document.getElementsByName("piece[" + j + "]");
			for (var k = 0; k < pie.length; k++) {
				pie[k].disabled = true;
			}
			
			if(role == 0){
				var pntrs = document.getElementsByName("penetrations[" + j + "]");
				for (var l = 0; l < pntrs.length; l++) {
					pntrs[l].disabled = true;
				}
			}
			
			pileType[j].disabled = true;
			method[j].disabled = true;
			location[j].disabled = true;
			pileNo[j].disabled = true;
			pileStandard[j].disabled = true;

			drillingDepth[j].disabled = true;
			intrusionDepth[j].disabled = true;
			hammaT[j].disabled = true;
			fallMeter[j].disabled = true;
			managedStandard[j].disabled = true;
			
			crossSection[j].disabled = true;
			hammaEfficiency[j].disabled = true;
			modulusElasticity[j].disabled = true;
			bigo[j].disabled = true;
			
			//ultimateBearingCapacity[j].disabled = true;
		}


		var piece = document.getElementsByName("piece[" + index + "]");
		
		if(role == 0){
			var pntrs = document.getElementsByName("penetrations[" + index + "]");	
		}
		
		
		if (obj[index].checked) {
			var isDel = document.getElementsByName("isDel");
			var selectIsDel = Number(isDel[index].value);
			
			if(selectIsDel == 0){
				for (var y = 0; y < piece.length; y++) {
					piece[y].disabled = false;
				}
				if(role == 0){
					for (var l = 0; l < pntrs.length; l++) {
						pntrs[l].disabled = false;
					}
				}
				pileType[index].disabled = false;
				method[index].disabled = false;
				location[index].disabled = false;
				pileNo[index].disabled = false;
				pileStandard[index].disabled = false;

				drillingDepth[index].disabled = false;
				intrusionDepth[index].disabled = false;
				hammaT[index].disabled = false;
				fallMeter[index].disabled = false;
				managedStandard[index].disabled = false;
				
				crossSection[index].disabled = false;
				hammaEfficiency[index].disabled = false;
				modulusElasticity[index].disabled = false;
				bigo[index].disabled = false;
			} 
			//ultimateBearingCapacity[index].disabled = false;
		}else{
			for (var y = 0; y < piece.length; y++) {
				piece[y].disabled = true;
			}
			if(role == 0){
				for (var y = 0; y < pntrs.length; y++) {
					pntrs[y].disabled = true;
				}
			}
			
			
			pileType[index].disabled = true;
			method[index].disabled = true;
			location[index].disabled = true;
			pileNo[index].disabled = true;
			pileStandard[index].disabled = true;

			drillingDepth[index].disabled = true;
			intrusionDepth[index].disabled = true;
			hammaT[index].disabled = true;
			fallMeter[index].disabled = true;
			managedStandard[index].disabled = true;
			
			crossSection[index].disabled = true;
			hammaEfficiency[index].disabled = true;
			modulusElasticity[index].disabled = true;
			bigo[index].disabled = true;
			//ultimateBearingCapacity[index].disabled = true;
		}
		

		//pileType, method, location, pileNo, pileStandard, drillingDepth , intrusionDepth , hammaT , fallMeter, managedStandard
		for (var l = 0; l < obj.length; l++) {
			if (obj[l] != chk) {
				obj[l].checked = false;

			}
		}
	}

	function getCheckdCheckboxIndex() {
		var obj = document.getElementsByName("selectOne");
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].checked) {
				return i;
			}
		}
		return null;
	}

	function chg(rowid) {
		chgTableColor(rowid, 'yellow');
	}
	function chgTableColor(rowid, chgcolor) {
		$('#reportTable tr').css("background-color", "");
		for (var i = 0; i < $('#reportTable tr').length; i++) {
			if (i == rowid) {
				$('#reportTable tr:rowindex:eq(' + i + ')').css("background-color", "#8dc5fc");
			}
		}
	}
	function clearTableColor() {
		$('#reportTable tr').css("background-color", "");
	}

	function searchDate() {
		var jb = $('#startDate').val();
		var endDate = $('#endDate').val();
		var id = ${param.id};
		if (jb == '') {
			alert('날짜를 입력하세요.');
		} else {
			location.href = '${pageContext.request.contextPath}/report/list?id='+ id + '&startDate=' + jb + '&endDate=' + endDate;
		}
	}

	function downloadExcel() {
		var para = document.location.href.split("?");
		location.href = '${pageContext.request.contextPath}/report/download/excel?' + para[1];
	}

	function doChecked(rowindex) {

	}
</script>
<div class="right_content">
	<div class="tab_menu">
		<ul>
			<li class="on">
				${device.machineNumber} 시공현황
			</li>
			<li>
				<%-- <a href="${pageContext.request.contextPath}/ips/list?deviceIdx=${param.id}&constructionIdx=${param.constructionIdx}">
					투입인원현황
				</a> --%>
				<a href="#">
					투입인원현황
				</a>
			</li>
			<li>
				<%-- <a href="${pageContext.request.contextPath}/eus/list?deviceIdx=${param.id}&constructionIdx=${param.constructionIdx}&erpDiv=1">
					장비사용현황
				</a> --%>
				<a href="#">
					장비사용현황
				</a>
			</li>
			<li>
				<%-- <a href="${pageContext.request.contextPath}/oiluse/list?deviceIdx=${param.id}&constructionIdx=${param.constructionIdx}&erpDiv=2">
					유류사용현황
				</a> --%>
				<a href="#">
					유류사용현황
				</a>
			</li>
			<li>
				<%-- <a href="${pageContext.request.contextPath}/mis/list?deviceIdx=${param.id}&constructionIdx=${param.constructionIdx}&erpDiv=3">
					
				</a> --%>
				<a href="#">
					항타자재현황
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
	
			<c:choose>
				<c:when test="${param.mode != 'simple'}">				     
					<c:choose>												 
						<c:when test="${sessionInfo.role == 0 || sessionInfo.hiddenManager == true}">
							<div class="search_form01" style="float: left;">
								<input type="button" class="input01" value="기록지 수정" style="float: left;" onclick="javascript:onClickReportUpdate();">
							</div>
						</c:when>
					</c:choose>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${param.mode == 'simple'}">
					<div  style="float: left; height: 100%; margin-top:20px;" class="input01">
						<font color="white" size="6">총시공량 ${totalConstruction} 공</font> 
					</div>
				</c:when>
			</c:choose>
			
			<div class="search_form01" style="float: right;">
			
				<input type="button" class="input01" value="총작업내역" onclick="location.href='${pageContext.request.contextPath}/report/list?id=${param.id}&type=all'">
				<%-- <input type="button" class="input01" value="일별작업내역" onclick="location.href='${pageContext.request.contextPath}/report/list?id=${param.id}&type=date'"> --%>
				<input type="button" class="input01" value="금일작업내역" onclick="location.href='${pageContext.request.contextPath}/report/list?id=${param.id}&date=today'">
				<%-- <form:input path="startDate" class="input01"/><span>~</span><form:input path="endDate" class="input01"/> --%> 
				<input type="text" class="input01" id="startDate" name="startDate" value=""><span>~</span><input type="text" class="input01" id="endDate" name="endDate" value="">
				<input type="button" class="input01" value="일자별검색" onclick="javascript:searchDate();">
				<c:choose>
				<c:when test="${param.mode != 'simple'}">
					<input type="button" class="input01"  value="엑셀출력" onclick="javascript:downloadExcel();">
				</c:when>
				
				</c:choose>
				<input type="button" class="input01" value="휴지통">
			</div>
			</form:form>
		</div>
		<!--search_div end-->
		<!--table 리스트-->
		<c:choose>
			<c:when test="${param.mode eq 'simple'}">
				<div id="aa" class="table_list" style="overflow-y: hidden; overflow-x: hidden; overflow-y:scroll;">
					<table  class="table01">
						<tr>
							<th style="width: 20%;">시공일</th>
							<th style="width: 20%;">금일시공량</th>
							<th style="width: 20%;">&nbsp;</th>
							<!-- <th style="width: 20%;">금일누적시공량</th> -->
							<th style="width: 20%;">&nbsp;</th>
							<th style="width: 20%;">&nbsp;</th>
						</tr>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<div id="aa" class="table_list2" style="overflow-y: hidden; overflow-x: hidden; overflow-y:scroll;    ">
				<table  class="table03" >
					<tr>
						<c:choose>
							<c:when test="${sessionInfo.role == 0  || sessionInfo.hiddenManager == true}">
								<th style="width: 2%;" rowspan="2">선택</th>
							</c:when>
						</c:choose>
						<th style="width: 2%;" rowspan="2">순번</th>
						<th style="width: 4%;" rowspan="2">시공일</th>
						<th style="width: 3%;" rowspan="2">파일종류</th>
						<th style="width: 5%;" rowspan="2">시공공법</th>
						<th style="width: 5%;" rowspan="2">시공위치</th>
						<th style="width: 4%;" rowspan="2">파일번호</th>
						<th style="width: 3%;" rowspan="2">파일규격</th>
						<th colspan="6">파일구분</th>
						
						<th style="width: 2%;" rowspan="2">이음<br>(개소)</th>
						<th style="width: 3%;" rowspan="2">천공깊이(M)</th>
						<th style="width: 3%;" rowspan="2">관입깊이(M)</th>
						<th style="width: 3%;" rowspan="2">파일잔량(M)</th>
						<th style="width: 3%;" rowspan="2">공삭공<br>(M)</th>
						<th style="width: 3%;" rowspan="2">드롭헤머(Ton)</th>
						<th style="width: 3%;" rowspan="2">낙하높이(m)</th>
						<th style="width: 3%;" rowspan="2">관리기준(mm)</th>
						
						<th style="width: 3%;" rowspan="2">1회측정(mm)</th>
						<th style="width: 3%;" rowspan="2">2회측정(mm)</th>
						<th style="width: 3%;" rowspan="2">3회측정(mm)</th>
						<th style="width: 3%;" rowspan="2">4회측정(mm)</th>
						<th style="width: 3%;" rowspan="2">5회측정(mm)</th>
						<!-- <th rowspan="2">6회측정(mm)</th>
						<th rowspan="2">7회측정(mm)</th>
						<th rowspan="2">8회측정(mm)</th>
						<th rowspan="2">9회측정(mm)</th>
						<th rowspan="2">10회측정(mm)</th> -->
						<th style="width: 3%;" rowspan="2">평균관입(mm)</th>
						<th style="width: 3%;" rowspan="2">최종관입(mm)</th>
						<th style="width: 3%;" rowspan="2">극한지지력<br>(kN)</th>
						<c:choose>
						<c:when test="${sessionInfo.role > 0}">
							<th style="width: 4%;" rowspan="2">비고</th>
						</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${sessionInfo.role == 0}">
								<th style="width: 3%;" rowspan="2">헤머효율(%)</th>
								<th style="width: 3%;" rowspan="2">탄성계수(t/cm2)</th>
								<th style="width: 4%;" rowspan="2">파일단면적(cm2)</th>
								<th style="" rowspan="2">비고<br>&nbsp;</th>
							</c:when>
						</c:choose>
					</tr>	
					<tr>
						<th  style="width: 2%;">단본(M)</th>
						<th  style="width: 2%;">하단(M)</th>
						<th  style="width: 2%;">중단(M)</th>
						<th  style="width: 2%;">중단(M)</th>
						<th  style="width: 2%;">상단(M) </th>
						<th  style="width: 2%;">합계(M)</th>
					</tr>
				</table>
			</div>
			</c:otherwise>
		</c:choose>
		<!-- <div class="table_list"  > -->
	<c:choose>
		<c:when test="${param.mode eq 'simple'}">
		<div id="bb" class="table_list" style="overflow-y:scroll;" onScroll="javascript:document.all.aa.scrollLeft = document.all.bb.scrollLeft">
			<table class="table01" id="reportTable"  name="reportTable" >
		</c:when>
		<c:otherwise>
		<div id="bb"  class="table_list2" style="overflow-y:scroll;" onScroll="javascript:document.all.aa.scrollLeft = document.all.bb.scrollLeft">
			<table class="table03" id="reportTable" name="reportTable">
		</c:otherwise>
	</c:choose>
	<c:forEach var="domain" items="${domainList}"  varStatus="status">
		<c:choose>
			<c:when test="${param.mode eq 'simple'}">
				<tr style="background: white;">
					<td  style="width: 20%;">
						<c:choose>
							<c:when test="${sessionInfo.role == 0}">
								${domain.currentDateTime} <input type="button" value="기록지확인"  onclick="location.href='${pageContext.request.contextPath}/report/list?id=${param.id}&date=${domain.currentDateTime}&constructionIdx=${param.constructionIdx}'"/>
							</c:when>
							<c:when test="${sessionInfo.role == 1}">
								${domain.currentDateTime} <input type="button" value="기록지확인"  onclick="location.href='${pageContext.request.contextPath}/report/list?id=${param.id}&date=${domain.currentDateTime}&constructionIdx=${sessionInfo.constructionIdx}'"/>
							</c:when>
							<c:when test="${sessionInfo.role == 2}">
								${domain.currentDateTime} <input type="button" value="기록지확인"  onclick="location.href='${pageContext.request.contextPath}/report/list?id=${param.id}&date=${domain.currentDateTime}&constructionIdx=${param.constructionIdx}'"/>
							</c:when>
						</c:choose>		
					</td>
					<td  style="width: 20%;">
						${domain.todayConstruction} 공
					</td>
					<td  style="width: 20%;">
						&nbsp;
					</td>
					<td  style="width: 20%;">
						&nbsp;
					</td>
					<td  style="width: 20%;">
						&nbsp;
					</td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(domainList) == 0}">
						<tr>
							<c:choose>
								<c:when test="${sessionInfo.role == 0}">
									<td colspan="5">등록된 데이터가 없습니다.</td>
								</c:when>
								<c:otherwise>
									<td colspan="5">등록된 데이터가 없습니다.</td>
								</c:otherwise>
							</c:choose>
							
						</tr>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${domain.isDel == 1}">
						<tr style="background-color: #ff0000;">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
				
					<c:choose>
						<c:when test="${sessionInfo.role == 0  || sessionInfo.hiddenManager == true}">
							<td style="width: 2%;">
								<c:choose>
									<c:when test="${domain.isDel == 1}">
										<!-- 삭제된것에 클릭 -->
										<input type="checkbox" id="selectOne" name="selectOne" onclick="doOpenCheck(this, this.parentNode.parentNode.rowIndex);">
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="selectOne" name="selectOne" onclick="doOpenCheck(this, this.parentNode.parentNode.rowIndex);">
									</c:otherwise>
								</c:choose>
							</td>
						</c:when>
					</c:choose>
					<td style="width: 2%;">
						${domain.rownum}
					</td>
					<td style="width: 4%;">
						<a>
							<font style="font-size: 14px;">${domain.currentDateTime}</font>
							<input type="hidden" id="id" name="id" value="${domain.id}" >
							<input type="hidden" id="isDel" name="isDel" value="${domain.isDel}" >
						</a>
					</td>
				 	<td style="width: 3%;">
				 		<input type="text" id="pileType" name="pileType" disabled="disabled" style="width: 50px;" value="${domain.pileType}">
				 	</td>
					<td style="width: 5%;">
						<input type="text" id="method" name="method" disabled="disabled" style="width: 90px;" value="${domain.method}">
					</td>
					<td style="width: 5%;">
						<input type="text" id="location" name="location" disabled="disabled" style="width: 90px;" value="${domain.location}">
					</td>
					<td style="width: 4%;">
						<input type="text" id="pileNo" name="pileNo" disabled="disabled" style="width: 70px;" value="${domain.pileNo}">
					</td>
					<td style="width: 3%;">
						<input type="text" id="pileStandard" name="pileStandard" disabled="disabled" style="width: 50px;" value="${domain.pileStandard}">
					</td>
					<c:choose>
						<c:when test="${fn:length(domain.piece) == 3}">
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]"  disabled="disabled" style="width: 30px;" value="${domain.piece[0].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[0].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[0].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[1].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[1].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[1].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[2].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[2].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[2].name}">
							</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${fn:length(domain.piece) == 4}">
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[0].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[0].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[0].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[1].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[1].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[1].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[2].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[2].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[2].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[3].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[3].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[3].name}">
							</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${fn:length(domain.piece) >= 5}">
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[0].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[0].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[0].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[1].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[1].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[1].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[2].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[2].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[2].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[3].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[3].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[3].name}">
							</td>
							<td style="width: 2%;">
								<input type="text" id="piece[${status.index}]" name="piece[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.piece[4].value}">
								<input type="hidden" id="pieceId[${status.index}]" name="pieceId[${status.index}]" value="${domain.piece[4].id}">
								<input type="hidden" id="pieceName[${status.index}]" name="pieceName[${status.index}]" value="${domain.piece[4].name}">
							</td>
						</c:when>
					</c:choose>
					<td style="width: 2%;">${domain.totalConnectWidth}</td>
					<td style="width: 2%;">
						${domain.connectLength}
					</td>
					<td style="width: 3%;"><input type="text" id="drillingDepth" name="drillingDepth" disabled="disabled" style="width: 50px;" value="${domain.drillingDepth}"/></td>
					<td style="width: 3%;" ><input type="text" id="intrusionDepth" name="intrusionDepth" disabled="disabled" style="width: 50px;" value="${domain.intrusionDepth}"/></td>
					<td style="width: 3%;" >${domain.balance}</td>
					<td style="width: 3%;" >
						${domain.gongSac}
					</td>
					<td style="width: 3%;" ><input type="text" id="hammaT" name="hammaT"  disabled="disabled" style="width: 50px;"  value="${domain.hammaT}"/></td>
					<td style="width: 3%;" ><input type="text" id="fallMeter" name="fallMeter"  disabled="disabled" style="width: 50px;"  value="${domain.fallMeter}"/></td>
					<td style="width: 3%;" ><input type="text" id="managedStandard" name="managedStandard"  disabled="disabled" style="width: 50px;"  value="${domain.managedStandard}"/></td>
					<%-- 10회 일 경우 	
					<c:forEach var="penetrations" items="${domain.penetrations}"  varStatus="status">
						<td>${penetrations.value}</td>
					</c:forEach>
					<c:forEach var="i" begin="1" end="${10 - fn:length(domain.penetrations)}">
						<td>&nbsp;</td>
					</c:forEach>
					 --%>
					 <!-- 5회 -->
					<c:forEach var="i" begin="0" end="${4}">
						<c:choose>
							<c:when test="${sessionInfo.role == 0}">
								<td style="width: 3%;">
									<input type="text" id="penetrations[${status.index}]" name="penetrations[${status.index}]" disabled="disabled" style="width: 30px;" value="${domain.penetrations[i].value}">
									<input type="hidden" id="penetrationsId[${status.index}]" name="penetrationsId[${status.index}]" value="${domain.penetrations[i].id}">
									<c:choose>
										<c:when test="${domain.penetrations[i].name != null}">
											<input type="hidden" id="penetrationsName[${status.index}]" name="penetrationsName[${status.index}]" value="${domain.penetrations[i].name}">	
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${i == 0}">
													<input type="hidden" id="penetrationsName[${status.index}]" name="penetrationsName[${status.index}]" value="1회">	
												</c:when>
												<c:when test="${i == 1}">
													<input type="hidden" id="penetrationsName[${status.index}]" name="penetrationsName[${status.index}]" value="2회">	
												</c:when>
												<c:when test="${i == 2}">
													<input type="hidden" id="penetrationsName[${status.index}]" name="penetrationsName[${status.index}]" value="3회">	
												</c:when>
												<c:when test="${i == 3}">
													<input type="hidden" id="penetrationsName[${status.index}]" name="penetrationsName[${status.index}]" value="4회">	
												</c:when>
												<c:when test="${i == 4}">
													<input type="hidden" id="penetrationsName[${status.index}]" name="penetrationsName[${status.index}]" value="5회">	
												</c:when>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
							</c:when>
							<c:otherwise>
								<td style="width: 3%;">${domain.penetrations[i].value}</td> 
							</c:otherwise>
						</c:choose>											
						
					</c:forEach>
					<!-- END 5회 -->
					<c:choose>
						<c:when test="${domain.managedStandard + 0 < domain.avgPenetrationValue + 0}">
							<td style="background-color: red; color: white; width: 3%;">
								${domain.avgPenetrationValue}
								<input type="hidden" id="avgPenetrationValue" name="avgPenetrationValue" value="${domain.avgPenetrationValue}" >
							</td>
						</c:when>
						<c:otherwise>
							<td style="width: 3%;">
								${domain.avgPenetrationValue}
								<input type="hidden" id="avgPenetrationValue" name="avgPenetrationValue" value="${domain.avgPenetrationValue}">
							</td>
						</c:otherwise>
					</c:choose>
					<td style="width: 3%;">
						${domain.totalPenetrationValue}
						<input type="hidden" id="totalPenetrationValue" name="totalPenetrationValue" value="${domain.totalPenetrationValue}" >
					</td>
					<td style="width: 3%;">
						${domain.ultimateBearingCapacity}
					</td>
					<c:choose>
						<c:when test="${sessionInfo.role > 0}">
							<c:choose>
								<c:when test="${sessionInfo.hiddenManager == true}">
									<td style="width: 4%;">
										<input type="text" id="bigo" name="bigo"  disabled="disabled" style="width: 60px;"  value="${domain.bigo}"/>
									</td>
								</c:when>
								<c:otherwise>
									<td style="width: 4%;">
										${domain.bigo}
										<input type="hidden" id="bigo" name="bigo"  disabled="disabled" style="width: 70px;"  value="${domain.bigo}"/>
									</td>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${sessionInfo.role == 0}">
							<td style="width: 3%;">
								<input type="text" id="hammaEfficiency" name="hammaEfficiency"  disabled="disabled" style="width: 50px;"  value="${domain.hammaEfficiency}"/>
							</td> 
							<td style="width: 3%;">
								<input type="text" id="modulusElasticity" name="modulusElasticity"  disabled="disabled" style="width: 50px;"  value="${domain.modulusElasticity}"/>
							</td> 
							<td style="width: 4%;">
								<input type="text" id="crossSection" name="crossSection"  disabled="disabled" style="width: 70px;"  value="${domain.crossSection}"/>
							</td> 
							<td style="">
								<input type="text" id="bigo" name="bigo"  disabled="disabled" style="width: 60px;"  value="${domain.bigo}"/>
							</td>
						</c:when>
						<c:otherwise>
							<input type="hidden" id="hammaEfficiency" name="hammaEfficiency"  disabled="disabled" style="width: 50px;"  value="${domain.hammaEfficiency}"/>
							<input type="hidden" id="modulusElasticity" name="modulusElasticity"  disabled="disabled" style="width: 50px;"  value="${domain.modulusElasticity}"/>
							<input type="hidden" id="crossSection" name="crossSection"  disabled="disabled" style="width: 70px;"  value="${domain.crossSection}"/>
							<%-- <input type="hidden" id="bigo" name="bigo"  disabled="disabled" style="width: 70px;"  value="${domain.bigo}"/> --%>
						</c:otherwise>
					</c:choose> 
				</tr>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${fn:length(domainList) == 0}">
				<tr>
				<c:choose>
					<c:when test="${sessionInfo.role == 0}">
						<td style="background: white;" colspan="28">등록된 데이터가 없습니다.</td>
					</c:when>
					<c:otherwise>
						<td style="background: white;" colspan="28">등록된 데이터가 없습니다.</td>
					</c:otherwise>
				</c:choose>
				</tr>
			</c:when>
		</c:choose>
		</table>
		</div>
		<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
			<c:choose>
				<c:when test="${param.mode != 'simple'}">
					 <c:choose>
						<c:when test="${sessionInfo.role == 0}"> 
							<div class="white_btn">
								<a style="color: white;" href="javascript:doDelete()">삭제</a>
							</div>
							<div class="white_btn2">
								<a style="color: white;" href="javascript:doRestore()">복구</a>
							</div>
						 </c:when>
						 <c:when test="${sessionInfo.hiddenManager == true}">
					 		<div class="white_btn">
								<a style="color: white;" href="javascript:doDelete()">삭제</a>
							</div>
						 </c:when>
					</c:choose>
				</c:when>
			</c:choose>
		<!--페이지 넘버end-->
	</div>
	<!--table01_content end-->
</div>