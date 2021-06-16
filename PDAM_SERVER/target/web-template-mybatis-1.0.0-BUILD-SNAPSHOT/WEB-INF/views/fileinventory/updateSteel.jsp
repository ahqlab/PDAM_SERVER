<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<script>
	$(document).ready(function() {
		getConstructionName();		
	});	

	
	function getConstructionName(){
		
		var idx = '${param.constructionIdx}';
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
</script>
<div class="right_content">
	<div class="table01_content">
		<div class="search_div">
			<div id="constructionSetName"  name="constructionSetName" class="search_form01" style="float: left; font-size : 30px; color: #ffffff;"></div>
				<!-- <div class="search_form01" style="float: right;">  
					<select  id="select1" class="input01" onchange="javascript:fileChange(this.value);">
						<option class="text-success" selected disabled value=""><h6>총 파일집계표 ▼</h6></option>
					</select>
				</div> -->
		</div>
		<div class="table_list">
			<form:form method="POST" action="${pageContext.request.contextPath}/fileinventory/update" commandName="domain" >
			<table class="table01">
				<tr>
					<th style="vertical-align: center;">
						날짜				
					</th>
					<th>
						종류 :
						<form:select path="pileType"  disabled="true" class="input01" style="width: 80px; font-size: 20px; color: black;" >
							<form:option value="PHC" selected="true" >PHC</form:option>
							<form:option value="STEEL" >STEEL</form:option>
						</form:select>
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
						<form:input path="registDate"  disabled="true" style="width: 120px; font-size: 20px; vertical-align: center; color: black;" class="input01" />
						<form:hidden path="constructionIdx" value="${param.constructionIdx}"/>
					</td>
					<td>단본</td>
					<td><form:input path="meterof51" style="width: 40px;" class="input01" /></td>
					<td><form:input path="meterof61" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof71" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof81" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof91" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof101" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof111" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof121" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof131" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof141" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof151" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof161" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof171" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof181" style="width: 40px;" value="" class="steelInput"/></td>
					
				</tr>
				<tr>
					<td>
						규격 :
						<form:select path="pileStandard"  disabled="true" class="input01" style="width: 80px; font-size: 20px; color: black;">
							<form:option value="406.4">406.4</form:option>
							<form:option value="508">508</form:option>
							<form:option value="609.6">609.6</form:option>
							<form:option value="711.2">711.2</form:option>
							<form:option value="812.8">812.8</form:option>
							<form:option value="914.4">914.4</form:option>
							<form:option value="1016">1016</form:option>
							<form:option value="1117.6">1117.6</form:option>
							<form:option value="1219.2">1219.2</form:option>
						</form:select>
					</td>
					<td>하단</td>
					<td><form:input path="meterof52" style="width: 40px;" class="input01" /></td>
					<td><form:input path="meterof62" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof72" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof82" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof92" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof102" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof112" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof122" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof132" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof142" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof152" style="width: 40px;"  class="input01" value=""/></td>
					<td><form:input path="meterof162" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof172" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof182" style="width: 40px;" value="" class="steelInput"/></td>
				</tr>
				<tr>
					<td>
						두께 :
						<form:select path="fileWeight" disabled="true"  class="input01" style="width: 80px; font-size: 20px; color: black;">
							<form:option value="8T">8T</form:option>
							<form:option value="9T">9T</form:option>
							<form:option value="10T">10T</form:option>
							<form:option value="12T">12T</form:option>
							<form:option value="14T">14T</form:option>
							<form:option value="15T">15T</form:option>
							<form:option value="16T">16T</form:option>
							<form:option value="18T">18T</form:option>	
						</form:select>
					</td>
					<td>중단</td>
					<td><form:input path="meterof53" style="width: 40px;" class="input01" /></td>
					<td><form:input path="meterof63" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof73" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof83" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof93" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof103" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof113" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof123" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof133" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof143" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof153" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof163" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof173" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof183" style="width: 40px;" value="" class="steelInput"/></td>
				</tr>
				<tr>	
					<td>&nbsp;</td>
					<td>상단</td>
					<td><form:input path="meterof54" style="width: 40px;" class="input01" /></td>
					<td><form:input path="meterof64" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof74" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof84" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof94" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof104" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof114" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof124" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof134" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof144" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof154" style="width: 40px;" class="input01" value=""/></td>
					<td><form:input path="meterof164" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof174" style="width: 40px;" value="" class="steelInput"/></td>
					<td><form:input path="meterof184" style="width: 40px;" value="" class="steelInput"/></td>
				</tr>
			</table>
			<div class="btn_box">
				<input type="submit" class="button02 button05" style="margin-right: 10px;" value="수정하기" onclick="return checkValidation();">
				&nbsp;
				<input class="button01 button02"  type="button" style="background-color: black;" onclick="location.href='${pageContext.request.contextPath}/fileinventory/list?constructionIdx=${param.constructionIdx}'" value="뒤로가기"/>
			</div>
			</form:form>
		</div>
	</div>
</div>