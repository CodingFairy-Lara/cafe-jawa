<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/web-inf/views/common/header.jsp" %>

</head>
<body>
<section id="notice-container">
	<h2>공지사항</h2>
	<table id="tbl-notice-view">
		<tr>
			<th>번 호</th>
			<td><%= notice.getNo()  %></td>
		</tr>
		</tr>
			<th>제 목</th>
			<td><%= notice.getTitle() %></td>
		</tr>		
		<tr>
			<th>내 용</th>
			<td><%= notice.getContent() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<%-- 첨부파일이 있을 경우만, 이미지와 함께 orginal파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath() %>/notice/fileDownload?no=<%= attach.getNo() %>"><%= attach.getOriginalFilename() %></a>	
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= notice.getReadCount() %></td>
		</tr>				
		<%
		if(!notice.getAttachments().isEmpty()) {
				for(Attachment attach : notice.getAttachments()){
			%>
		}
	</table>
</section>




</body>
</html>