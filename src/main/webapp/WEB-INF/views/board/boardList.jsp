<%@page import="cafe.jawa.board.model.dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board.css" />
<%
	List<Board> boardList = (List<Board>) request.getAttribute("boardList");
%>
<section id="board-container">
<br>
	<h2>공지사항 </h2>
	
	<% if(loginMember != null){ %>
	<input type="button" value="글쓰기" id="btn-add"
		onclick="location.href = '<%= request.getContextPath() %>/board/boardEnroll';" />
	<% } %>
	<table id="tbl-board">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<% if(boardList.isEmpty()){ %>	
			<tr>
				<td colspan="6">조회된 게시물이 없습니다.</td>
			</tr>
		<% 
		   } else { 
			 for(Board board : boardList){
		%>
			<tr>
				<td><%= board.getNo() %></td>
				<td>
					<a href="<%= request.getContextPath() %>/board/boardView?no=<%= board.getNo() %>"><%= board.getTitle() %></a>
				</td>
				<td><%= board.getWriter() %></td>
				<td><%= board.getRegDate() %></td>
				<td>
					<% if(board.getAttachCnt() > 0){ %>
						<img src="<%= request.getContextPath() %>/images/file.png" style="width:16px; ">
					<% } %>
				</td>
				<td><%= board.getReadCount() %></td>
			</tr>
		<%
			 }
		   } 
		%>
		</tbody>
	</table>

	<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
