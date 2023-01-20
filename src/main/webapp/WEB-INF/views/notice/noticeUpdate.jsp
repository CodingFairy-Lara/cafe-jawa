<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<section id="notice-container">
	<h2>공지사항 수정</h2>
	<form 
		name="noticeUpdateFrm"
		action="<%=request.getContextPath() %>/notice/noticeUpdate"
		enctype="multipart/form-data"
		method="post">
		<table id="tbl-notice-view">
			<tr>
				<th>제 목</th>
				<td><input type="text" name="title" value="<%= notice.getTitle() %>" required></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>
					<textarea rows="5" cols="40" name="content"><%= notice.getContent() %></textarea>
				</td>		
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<%
						List<Attachment> attachments = board.getAttachments();
						if(!attachment.isEmpty()) {
							for(int i =0; i < attachments.size(); i++) {
								Attachment attach = attachments.get(i);
					 %>
					 	<img src="<%= request.getContextPath() %>/images/file.png" width="16px" />
					 	<% attach.getOriginalFilename() %>
					 	<input type="checkbox" name="delFile" id="delFile<%= i  %>" value="<%= attach.getNo() %>" />
					 	<label for="delFile<%= i %>">삭제</label>
					 	<br />
					 				
							}
						}
					%>
					<input type="file" name="upFile1">
					<input type="file" name="upFile2">
					<input type="file" name="upFile3">
				</td>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기"/>
					<input type="button" value="취소" onclick="history.go(-1);"/>
				</th>		
			</tr>	
		</table>
		<input type="hidden" name="no" value="<%= board.getNo() %>" />
	</form>
	</section>
	
	<script>
	document.noticeUpdateFrm.onsubmit = (e) => {
		const frm = e.target;
		// 제목을 작성하지 않은 경우 폼 제출 불가
		const titleval = frm.title.value.trim(); // 좌우 공백
		if(!/^.+$/.test(titleVal)){
			alert("제목을 작성해주세요.");
			frm.title.select();
			retrun false;
		}
		
		// 내용을 작성하지 않은 경우 폼 제출 불가
		const  contentVal = frm.content.value.trim();
		if(!/^(.|\n)+$/.test(contentVal)){
			alert("내용을 작성해주세요.");
			frm.content.select();
			return false;
		}
	}
	</script>
	<%@ include file="WEB-INF/views/common/footer.jsp" %>	
		
		
	}




</body>
	<script>
</html>