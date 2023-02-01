<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<section id="main-view-container">
	<div class="inner main">
		

			<% if(loginMember == null) { %>	
			<h1>안녕하세요, 카페 자와 입니다.</h1>
			<% } else { %>
			<h1>안녕하세요, 카페 자와 입니다.</h1>
			<% } %>



	</div>		
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>