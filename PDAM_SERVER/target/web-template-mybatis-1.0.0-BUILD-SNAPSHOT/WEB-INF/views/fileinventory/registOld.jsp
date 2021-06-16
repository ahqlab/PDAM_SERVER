<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script>
	$(document).ready(function() {
		getConstructionName();
		getPileTypeList();
		//alert('ddddd');
		//alert('getTimeStamp() : ' + getTimeStamp());
		$('#registDate').val(getTimeStamp());
		search();
		$('#registDate').change(function () { 
			search();
		});
		
		
		$('#pileType').change(function () {
			//alert('type : ' + $('#pileType').val());
			if($('#pileType').val() == 'STEEL'){
				$('#fileWeightHidden').hide();
				$('#fileWeightSelect').show();
				$('#phcPileStandard').hide();
				$('#steelPileStandard').show();
				
				$('.steelInput').hide();
			}else{
				$('#fileWeightHidden').show();
				$('#fileWeightSelect').hide();
				$('#phcPileStandard').show();
				$('#steelPileStandard').hide();
				
				$('.steelInput').show();
			}
		});
		
	});
	function getConstructionName(){
		
		var idx = '${constructionIdx}';
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
		
		var idx = '${constructionIdx}';
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
					 }else{
						 var option = item.pileType + " " + item.fileWeight + " " + item.pileStandard;
					 }
	                 $("#select1").append("<option class='text-success text-center' value='R' value='"+option+"'>"+option+"</option>")
	        	});
			},
			complete : function(data) {
			},
			error : function(xhr, status, error) {
			}
		}); 
	}
	
	
	
	function submit(){
		var formSerializeArray = $('#inventoryForm').serializeArray();
		var object = {};
		for (var i = 0; i < formSerializeArray.length; i++){
			if(formSerializeArray[i]['value'] != ""){
				 object[formSerializeArray[i]['name']] = formSerializeArray[i]['value'];
			}
		}
		if($('#fiIdx').val() != ""){
			jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/fileinventory/update",
				data: JSON.stringify(object), 
				dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
				contentType : "application/json",
				success : function(data) {
					search();
					alert('저장되었습니다.');
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
				}
			});
		}else{
			jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/fileinventory/regist",
				data: JSON.stringify(object),  
				dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
				contentType : "application/json",
				success : function(data) {
					search();
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
				}
			});
		}
		return false;
	}
		
	
	function checkValidation(){
		if($('#registDate').val() == 0){
			alert('날짜를 선택하세요');
			return false;
		}
		submit();
		return false;
	}
	
	function getTimeStamp() {

	    var d = new Date();
	    var s =
	        leadingZeros(d.getFullYear(), 4) + '-' +
	        leadingZeros(d.getMonth() + 1, 2) + '-' +
	        leadingZeros(d.getDate(), 2);

	    return s;
	}
	function leadingZeros(n, digits) {

	    var zero = '';
	    n = n.toString();

	    if (n.length < digits) {
	        for (i = 0; i < digits - n.length; i++)
	            zero += '0';
	    }
	    return zero + n;
	}
	function search(){
		    jQuery.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/fileinventory/get",
				data: {
					registDate : $('#registDate').val()
					, constructionIdx : '${constructionIdx}'
				}, 
				dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
				success : function(data) {
					
					//alert(data);
					//alert(data.pileType);
					if(data != null){
						if(data.pileType == 'STEEL'){
							$('#fileWeightHidden').hide();
							$('#fileWeightSelect').show();
							$('#phcPileStandard').hide();
							$('#steelPileStandard').show();
						}else{
							$('#fileWeightHidden').show();
							$('#fileWeightSelect').hide();
							$('#phcPileStandard').show();
							$('#steelPileStandard').hide();
						}
						
						$('#fiIdx').val(data.fiIdx);
						$('#registDate').val(data.registDate);
						$('#constructionIdx').val('${constructionIdx}');
						
						$("#pileType").val(data.pileType).attr('selected','selected');
						$("#fileWeight option").each(function(){
							if($(this).val() == data.fileWeight){
								$(this).prop("selected", true);
							}
						});
						$("#pileStandard option").each(function(){
							if($(this).val() == data.pileStandard){
								$(this).prop("selected", true);
							}
						});
						
						
						$('#meterof51').val(data.meterof51);
						$('#meterof52').val(data.meterof52);
						$('#meterof53').val(data.meterof53);
						$('#meterof54').val(data.meterof54);
						$('#meterof61').val(data.meterof61);
						$('#meterof62').val(data.meterof62);
						$('#meterof63').val(data.meterof63);
						$('#meterof64').val(data.meterof64);
						$('#meterof71').val(data.meterof71);
						$('#meterof72').val(data.meterof72);
						$('#meterof73').val(data.meterof73);
						$('#meterof74').val(data.meterof74);
						$('#meterof81').val(data.meterof81);
						$('#meterof82').val(data.meterof82);
						$('#meterof83').val(data.meterof83);
						$('#meterof84').val(data.meterof84);
						$('#meterof91').val(data.meterof91);
						$('#meterof92').val(data.meterof92);
						$('#meterof93').val(data.meterof93);
						$('#meterof94').val(data.meterof94);
						$('#meterof101').val(data.meterof101);
						$('#meterof102').val(data.meterof102);
						$('#meterof103').val(data.meterof103);
						$('#meterof104').val(data.meterof104);
						$('#meterof111').val(data.meterof111);
						$('#meterof112').val(data.meterof112);
						$('#meterof113').val(data.meterof113);
						$('#meterof114').val(data.meterof114);
						$('#meterof121').val(data.meterof121);
						$('#meterof122').val(data.meterof122);
						$('#meterof123').val(data.meterof123);
						$('#meterof124').val(data.meterof124);
						$('#meterof131').val(data.meterof131);
						$('#meterof132').val(data.meterof132);
						$('#meterof133').val(data.meterof133);
						$('#meterof134').val(data.meterof134);
						$('#meterof141').val(data.meterof141);
						$('#meterof142').val(data.meterof142);
						$('#meterof143').val(data.meterof143);
						$('#meterof144').val(data.meterof144);
						$('#meterof151').val(data.meterof151);
						$('#meterof152').val(data.meterof152);
						$('#meterof153').val(data.meterof153);
						$('#meterof154').val(data.meterof154);
						$('#meterof161').val(data.meterof161);
						$('#meterof162').val(data.meterof162);
						$('#meterof163').val(data.meterof163);
						$('#meterof164').val(data.meterof164);
						$('#meterof171').val(data.meterof171);
						$('#meterof172').val(data.meterof172);
						$('#meterof173').val(data.meterof173);
						$('#meterof174').val(data.meterof174);
						$('#meterof181').val(data.meterof181);
						$('#meterof182').val(data.meterof182);
						$('#meterof183').val(data.meterof183);
						$('#meterof184').val(data.meterof184);
						
					}else{
						
						$('#fileWeightHidden').show();
						$('#fileWeightSelect').hide();
						$('#phcPileStandard').show();
						$('#steelPileStandard').hide();
						
						
						$('#fiIdx').val("");
						$('#constructionIdx').val('${constructionIdx}');
						$("#pileType option:eq(0)").prop("selected", true);
						$("#pileStandard option:eq(0)").prop("selected", true);		
						$("#fileWeight option:eq(0)").prop("selected", true);
						$('#meterof51').val("");
						$('#meterof52').val("");
						$('#meterof53').val("");
						$('#meterof54').val("");
						$('#meterof61').val("");
						$('#meterof62').val("");
						$('#meterof63').val("");
						$('#meterof64').val("");
						$('#meterof71').val("");
						$('#meterof72').val("");
						$('#meterof73').val("");
						$('#meterof74').val("");
						$('#meterof81').val("");
						$('#meterof82').val("");
						$('#meterof83').val("");
						$('#meterof84').val("");
						$('#meterof91').val("");
						$('#meterof92').val("");
						$('#meterof93').val("");
						$('#meterof94').val("");
						$('#meterof101').val("");
						$('#meterof102').val("");
						$('#meterof103').val("");
						$('#meterof104').val("");
						$('#meterof111').val("");
						$('#meterof112').val("");
						$('#meterof113').val("");
						$('#meterof114').val("");
						$('#meterof121').val("");
						$('#meterof122').val("");
						$('#meterof123').val("");
						$('#meterof124').val("");
						$('#meterof131').val("");
						$('#meterof132').val("");
						$('#meterof133').val("");
						$('#meterof134').val("");
						$('#meterof141').val("");
						$('#meterof142').val("");
						$('#meterof143').val("");
						$('#meterof144').val("");
						$('#meterof151').val("");
						$('#meterof152').val("");
						$('#meterof153').val("");
						$('#meterof154').val("");
						$('#meterof161').val("");
						$('#meterof162').val("");
						$('#meterof163').val("");
						$('#meterof164').val("");
						$('#meterof171').val("");
						$('#meterof172').val("");
						$('#meterof173').val("");
						$('#meterof174').val("");
						$('#meterof181').val("");
						$('#meterof182').val("");
						$('#meterof183').val("");
						$('#meterof184').val("");
					}
					
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
				}
			});
	}
	
	
	function fileChange(value){
		var constructionIdx = ${constructionIdx};
		location.href = '${pageContext.request.contextPath}/file/using/chart/download/excel?constructionIdx=' + constructionIdx + '&pile=' + value + '&dateTime=' + value;
		$('#select1 option:eq(0)').prop('selected', true);
	}
	
</script>
<div class="right_content">
	<div class="table01_content">
		<div class="search_div">
			<div id="constructionSetName"  name="constructionSetName" class="search_form01" style="float: left; font-size : 30px; color: #ffffff;"></div>
				<div class="search_form01" style="float: right;">
					<input type="button" class="input01" value="파일반입 및 수정" onclick="document.location.href='${pageContext.request.contextPath}/fileinventory/regist'"  />  
					<select  id="select1" class="input01" onchange="javascript:fileChange(this.value);">
						<option class="text-success" selected disabled value=""><h6>총 파일집계표 ▼</h6></option>
					</select>
				</div>
		</div>
		<div class="table_list">
			<form action="" id="inventoryForm" name="inventoryForm">
			<table class="table01">
				<tr>
					<th style="vertical-align: center;">
						날짜				
					</th>
					<th>
						종류 :
						<select id="pileType" name="pileType" class="input01" style="width: 80px; font-size: 20px;" onselect="javascript:setPileTypeChnage(this.value);">
							<option value="PHC">PHC</option>
							<option value="STEEL">STEEL</option>
						</select>
					</th>
					<th style="width:5%;">5m</th>
					<th style="width:5%;">6m</th>
					<th style="width:5%;">7m</th>
					<th style="width:5%;">8m</th>
					<th style="width:5%;">9m</th>
					<th style="width:5%;">10m</th>
					<th style="width:5%;">11m</th>
					<th style="width:5%;">12m</th>
					<th style="width:5%;">13m</th>
					<th style="width:5%;">14m</th>
					<th style="width:5%;">15m</th>
					<th style="width:5%;">16m</th>
					<th style="width:5%;">17m</th>
					<th style="width:5%;">18m</th>
				</tr>
				<tr>	
					<td>
						<input type="text" style="width: 120px; font-size: 20px; vertical-align: center;" class="input01" id="registDate" name="registDate" value="" >
						<input type="hidden" id="fiIdx" name="fiIdx" value=""/>
						<!-- <input type="hidden" id="pileType" name="pileType" value=""/> -->
						<!-- <input type="hidden" id="pileStandard" name="pileStandard" value=""/> -->
						<input type="hidden" id="constructionIdx" name="constructionIdx" value="${constructionIdx}"/>
					</td>
					<td>단본</td>
					<td><input type="text" id="meterof51" name="meterof51" style="width: 40px;" class="input01" /></td>
					<td><input type="text" id="meterof61" name="meterof61"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof71" name="meterof71"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof81" name="meterof81"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof91" name="meterof91"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof101" name="meterof101"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof111" name="meterof111"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof121" name="meterof121"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof131" name="meterof131"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof141" name="meterof141"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof151" name="meterof151"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof161" name="meterof161"  style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof171" name="meterof171"  style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof181" name="meterof181"  style="width: 40px;" value=""/></td>
				</tr>
				<tr>	
					<td id="phcPileStandard">
						규격 :
						<select id="pileStandard" name="pileStandard" class="input01" style="width: 80px; font-size: 20px;" >
							<option value="400">400</option>
							<option value="450">450</option>
							<option value="500">500</option>
							<option value="550">550</option>
							<option value="600">600</option>
							<option value="650">650</option>
							<option value="700">700</option>
							<option value="750">750</option>
							<option value="800">800</option>
							<option value="850">850</option>
							<option value="900">900</option>
							<option value="950">950</option>
							<option value="1000">1000</option>
						</select>
					</td>
					<td  id="steelPileStandard" style="display: none;">
						규격 :
						<select id="pileStandard" name="pileStandard" class="input01" style="width: 80px; font-size: 20px;" >
							<option value="406.4">406.4</option>
							<option value="508">508</option>
							<option value="609.6">609.6</option>
							<option value="711.2">711.2</option>
							<option value="812.8">812.8</option>
							<option value="914.4">914.4</option>
							<option value="1016">1016</option>
							<option value="1117.6">1117.6</option>
							<option value="1219.2">1219.2</option>
						</select>
					</td>
					<td>하단</td>
					<td><input type="text" id="meterof52" name="meterof52" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof62" name="meterof62" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof72" name="meterof72" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof82" name="meterof82" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof92" name="meterof92" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof102" name="meterof102" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof112" name="meterof112" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof122" name="meterof122" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof132" name="meterof132" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof142" name="meterof142" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof152" name="meterof152" style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof162" name="meterof162" style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof172" name="meterof172" style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof182" name="meterof182" style="width: 40px;" value=""/></td>
				</tr>
				<tr>	
					<td id="fileWeightSelect" style="display: none;">
						두께 :
						<select id="fileWeight" name="fileWeight" class="input01" style="width: 80px; font-size: 20px;" >
							<option value="8T">8T</option>
							<option value="9T">9T</option>
							<option value="10T">10T</option>
							<option value="12T">12T</option>
							<option value="14T">14T</option>
							<option value="15T">15T</option>
							<option value="16T">16T</option>
							<option value="18T">18T</option>	
						</select>
					</td>
					<td  id="fileWeightHidden" >
						<input type="hidden" id="fileWeight" name="fileWeight" value="">
					</td>
					<td>중단</td>
					<td><input type="text" id="meterof53" name="meterof53"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof63" name="meterof63"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof73" name="meterof73"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof83" name="meterof83"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof93" name="meterof93"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof103" name="meterof103"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof113" name="meterof113"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof123" name="meterof123"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof133" name="meterof133"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof143" name="meterof143"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof153" name="meterof153"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof163" name="meterof163"  style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof173" name="meterof173"  style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof183" name="meterof183"  style="width: 40px;" value=""/></td>
				</tr>
				<tr>	
					<td>&nbsp;</td>
					<td>상단</td>
					<td><input type="text" id="meterof54" name="meterof54"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof64" name="meterof64"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof74" name="meterof74"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof84" name="meterof84"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof94" name="meterof94"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof104" name="meterof104"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof114" name="meterof114"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof124" name="meterof124"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof134" name="meterof134"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof144" name="meterof144"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" id="meterof154" name="meterof154"  style="width: 40px;" class="input01" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof164" name="meterof164" style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof174" name="meterof174" style="width: 40px;" value=""/></td>
					<td><input type="text" class="steelInput" id="meterof184" name="meterof184" style="width: 40px;" value=""/></td>
				</tr>
			</table>
			</form>
		</div>
		
		
		<button style="width: 100px;  font-size: 22px;" onclick="return checkValidation();" class="white_btn">입력</button>
		
		
		<!-- <div class="white_btn">
			
			<a href="javascript:checkValidation();" onclick="return checkValidation();">입력</a>
		</div> -->
	</div>
</div>