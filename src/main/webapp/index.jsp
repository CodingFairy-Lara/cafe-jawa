<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<section id="main-view-container">
	<div class="inner main">
		

			<% if(loginMember == null) { %>	
			<h1 style="    display: block;
			font-size: 2.5em;
			font-weight: bold;
			padding-top: 275px;
			padding-left: 61px;">안녕하세요, 카페 자와 입니다.</h1>
			<% } else { %>
			<h1 style="    display: block;
			font-size: 2.5em;
			font-weight: bold;
			line-height: 160%;
			padding-top: 235px;
			padding-left: 61px;"><%= loginMember.getMemberName()%> 님,<br/>안녕하세요, 카페 자와 입니다.</h1>
			<% } %>

			<div>
				<img  src="<%= request.getContextPath() %>/images/common/coffe_img.png" alt="CAFE JAWA" 
				style="
				position: absolute;
				margin: 0 auto;
				top: 90px;
				right: -110px;
				width: 42%;">
			</div>

	</div>		
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>