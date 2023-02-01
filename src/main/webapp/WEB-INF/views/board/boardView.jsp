<%@page import="java.util.List"%>
<%@page import="cafe.jawa.board.model.dto.Attachment"%>
<%@page import="cafe.jawa.board.model.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board board = (Board) request.getAttribute("board");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/board.css" />
<section id="board-container">
<br>
	<h2>공지사항</h2>
	<br>
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%= board.getNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= board.getTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= board.getWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= board.getReadCount() %></td>
		</tr>
		<% 
			if(!board.getAttachments().isEmpty()) {
				for(Attachment attach : board.getAttachments()){
		%>
		<tr>
			<th>첨부파일</th>
			<td>
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath() %>/board/fileDownload?no=<%= attach.getNo() %>"><%= attach.getOriginalFilename() %></a>
			</td>
		</tr>
		
		<% 
				}
			} 
		%>
		<tr>
			<th>내 용</th>
			<td><%= board.getContent() %></td>
		</tr>
		<% 
			boolean canEdit = loginMember != null && 
								(loginMember.getMemberRole() == admin);
			if(canEdit){
		%>
		<tr>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateBoard()">
				<input type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
		<% 
			}
		%>
	</table>
	
	<hr style="margin-top:30px;" />	

</section>
<form 
	action="<%= request.getContextPath() %>/board/boardCommentDelete" 
	name="boardCommentDelFrm"
	method="POST">
	<input type="hidden" name="no" />
	<input type="hidden" name="boardNo" value="<%= board.getNo() %>"/>
</form>

   <script>
document.querySelectorAll(".btn-delete").forEach((button) => {
	button.onclick = (e) => {
		if(confirm("해당 댓글을 삭제하시겠습니까?")){
			const frm = document.boardCommentDelFrm;
			frm.no.value = e.target.value;
			frm.submit();
		}
	}; 
});	
</script>

<script>
document.querySelectorAll(".btn-reply").forEach((button) => {
	button.onclick = (e) => {
		console.log(e.target.value);
		
		<% if(loginMember == null){ %>
			loginAlert();
		<% } else { %>
			const tr = `
			<tr>
				<td colspan="2" style="text-align:left">
					<form
						action="<%=request.getContextPath()%>/board/boardCommentEnroll" method="post" name="boardCommentFrm">
		                <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
		                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
		                <input type="hidden" name="commentLevel" value="2" />
		                <input type="hidden" name="commentRef" value="\${e.target.value}" />    
						<textarea name="content" cols="58" rows="1"></textarea>
		                <button type="submit" class="btn-comment-enroll2">등록</button>
		            </form>
		      	</td>
		    </tr>
			`;
			
			const target = e.target.parentElement.parentElement; // tr
			console.log(target);
			target.insertAdjacentHTML('afterend', tr);
			
			button.onclick = null; // 이벤트핸들러 제거
		
		<% } %>
	};
});


/**
 * 이벤트버블링을 통해 부모요소에서 이벤트 핸들링
 */
document.body.addEventListener('submit', (e) => {
	console.log(e.target);
	
	if(e.target.name === 'boardCommentFrm'){
		
		<% if(loginMember == null){ %>
			loginAlert();
			e.preventDefault();
			return; // 조기리턴
		<% } %>
				
		// 유효성검사
		const content = e.target.content;
		if(!/^(.|\n)+$/.test(content.value)){
			e.preventDefault();
			alert('내용을 작성해주세요');
			content.focus();
		}
	}
	
	
});

// textarea
document.boardCommentFrm.content.addEventListener('focus', (e) => {
	<% if(loginMember == null){ %>
		loginAlert();
	<% } %>
});

const loginAlert = () => {
	alert("로그인 후 이용할 수 있습니다.");
	document.querySelector("#memberId").focus();
};

</script>

<% if(canEdit){ %>
<form action="<%= request.getContextPath() %>/board/boardDelete" name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= board.getNo() %>" />
</form>
<script>
const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/board/boardUpdate?no=<%= board.getNo() %>";
};
const deleteBoard = () => {
	if(confirm("정말 게시글을 삭제하겠습니까?")){
		document.boardDeleteFrm.submit();
	}
};
</script>
<% } %>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
