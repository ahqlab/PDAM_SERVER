<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<div class="right_content">
	<div class="table01_content">
		<div class="search_div">
			<div id="constructionSetName"  name="constructionSetName" class="search_form01" style="float: left; font-size : 30px; color: #ffffff;">
				슈퍼관리자
			</div>
				<div class="search_form01" style="float: right;">
					<input type="button" class="input01" value="파일반입 및 수정" onclick="document.location.href='${pageContext.request.contextPath}/fileinventory/regist'"  />  
					<select  class="input01" onchange="javascript:fileChange(this.value);">
						<option class="text-success" selected disabled value=""><h6>총 파일집계표 ▼</h6></option>
						<option class="text-success text-center" value="R">PHC-D 400</option>
						<option class="text-success text-center" value="G">PHC-D 500</option>
						<option class="text-success text-center" value="B">PHC-D 600</option>
					</select>
				</div>
		</div>
		<div class="table_list">
			<table class="table01">
				<tr>
					<th style="width: 100%;">호기</th>
				</tr>
				<tr>	
					<td>1</td>
				</tr>
			</table>
		</div>
		
		
		
		
	</div>
</div>