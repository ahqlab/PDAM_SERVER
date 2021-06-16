<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page_number">
    <ul>
	<c:if test="${page.priorPageGroup > 0}">
		<li class=""><a href="javascript:movePage(1)">처음</a></li>
	</c:if>
	<!-- 페이징 -->
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" step="1">
		<c:if test="${page.currentPage != i }">
			 <li><a href="javascript:movePage(${i})">${i}</a></li>
		</c:if>
		<c:if test="${page.currentPage == i }">
		 	<li class="on"><a href="#">${i}</a></li>
		</c:if>
	</c:forEach>

	<c:if test="${page.nextPageGroup > 0}">
		<li><a href="javascript:movePage(${page.nextPageGroup})">맨끝</a></li>
	</c:if>
 	</ul>
 </div>