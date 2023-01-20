<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ inclube file="/WEB-INF/lib/views/common/header.jsp" %>
<%@ %>


<!DOCTYPE html>

<html>

<meta charset="UTF-8">
<title>공지사항</title>


<section id = "notice-container">
	<h2> 공지사항 </h2>
	<table id = "notice-board">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<% if(boardList.isEmpty()){  %>
			<tr>
				<td colspan="6">조회된 게시물이 없습니다.</td>
			</tr>
		<%
			} else {
				for(Notice notice : notice List) {
			%>
				<tr>
					<td><% notice.getNo() %></td>
					<td>
						<a href="<%= request.getContextPath() %>/notice"	><%= notice.getTitle() %></a>
					</td>
					<td><%= notice.getReadCount() %></td>
				</tr>
					
		</tbody>	
	</table>
	
	<a href="noticeEnroll.jsp">글 쓰기</a>
	
	<div id = 'pagebar'></div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
