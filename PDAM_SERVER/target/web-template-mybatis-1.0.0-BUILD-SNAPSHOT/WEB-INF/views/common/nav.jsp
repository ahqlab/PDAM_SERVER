<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/tagLib.jsp" %>
<div class="left_menu">
	<div class="menu_logo">
		<c:choose>
			<c:when test="${sessionInfo.role == 0}">
				<a href="${pageContext.request.contextPath}/group/list"><img src="${pageContext.request.contextPath}/images/logo.png" class="로고"></a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/device/list?constructionIdx=${sessionInfo.constructionIdx}"><img src="${pageContext.request.contextPath}/images/logo.png" class="로고"></a>
			</c:otherwise>
		</c:choose>
	</div>
	<ul id="gnb">
		<c:choose>
			<c:when test="${sessionInfo.role == 0}">
			<li class='sub-menu'>
				<a href="${pageContext.request.contextPath}/group/list"><img src="${pageContext.request.contextPath}/images/menu_icon01.png" class="menu_icon">시공사</a>
			</li>
			<li class='sub-menu'>
				<a href="${pageContext.request.contextPath}/construction/list"><img src="${pageContext.request.contextPath}/images/menu_icon01.png" class="menu_icon">전체 협력사</a>
			</li>
			</c:when>
			<c:when test="${sessionInfo.role > 0}">
				<li class='sub-menu'>
					<a href="${pageContext.request.contextPath}/device/list?constructionIdx=${sessionInfo.constructionIdx}"><img src="${pageContext.request.contextPath}/images/menu_icon02.png" class="menu_icon">기기관리</a>
				</li>
			</c:when>
		</c:choose>
	</ul>
</div>
<script>
$('.sub-menu ul').hide();
$(".sub-menu a").click(function () {
  $(this).parent(".sub-menu").children("ul").slideToggle("100");
  $(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
});
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".menu2").click(function() {
            $("#m_nav2").animate({
                "left": "0%"
            }, 500);
        });
        $(".close").click(function() {
            $("#m_nav2").animate({
                "left": "-150%"
            }, 500);
        });
    });
</script>
<div class="top">
	<!--모바일 메뉴 -->
	<div class="menu2">
		  <%-- <img src="${pageContext.request.contextPath}/images/gnb.png" class="home_gnb"> --%>
	</div>
	<div id="m_nav2" style="right: -150%;">
	<div class="m_nav_logo">
	       <img src="${pageContext.request.contextPath}/images/close2.png" alt="" class="close" style="width:15px;margin-right:20px;opacity:1;">
	</div>
	    <!-- <div class="sp10"></div>
	    <div class="sp10"></div>
	    <div class="sp10"></div>
	    <div class="sp10"></div>
	    <ul class="m_nav2_ul">
	        <li><a href="./list01_white.php" class="scroll">시공사등록</a></li>
	        <li><a href="./list01.php" class="scroll">시공사리스트</a></li>
	        <li><a href="./list02_white.php" class="scroll">기기등록</a></li>
	        <li><a href="./list02.php" class="scroll">기기등록리스트</a></li>
	        <li><a href="./list03.php" class="scroll">기록표리스트</a></li>
	    </ul> -->
	</div>				
	<!--紐⑤컮�씪 �꽕鍮� end-->
	<p class="logout">
		<a href="${pageContext.request.contextPath}/logout" style="color: #000000">로그아웃</a>
	</p>
</div>