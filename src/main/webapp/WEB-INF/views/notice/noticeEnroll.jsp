<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
</head>
<body>
<section id ="notice-container">
	<h2>공지사항 작성</h2>
	<form
		name="noticeEnrollFrm"
		action="<%=request.getContextPath() %>/notice/noticeEnroll"
		enctype="multipart/form-data"
		method="post">
		
		<table id="tbl-notice-view">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="title" required></td>
			</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="40" name="content"></textarea></td>
			</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type = "file" name="upFile1">
				<input type = "file" name="upFile2">
				<input type = "file" name="upFile3">
			</td>		
		<tr>
			<th colspan="2">
				<input type = "submit" value = "등록하기">
				</th>
		</tr>	
	</table>
	</form>
</section>

<script>
/**
 *  noticeEnrollFrm 유효성 검사
 */
document.noticeEnrollFrm.onsubmit = (e) => {
	const title = e.target.title;
	const content = e.target.content;
	console.log(title, content);

	// 제목을 작성하지 않은 경우 폼 제출 불가
	if (!/^.+$/.test(title.value)){
		alert("글 제목을 작성해주세요.");
		title.select();
		return flase;
		}
	
	
	
	// 내용을 작성하지 않은 경우 폼 제출 불가
	if (!^(.|\n)+$/.test(content.vlaue)){
		alert("내용을 작성해주세요.");
		content.select();
		return false;
		}
	}
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	
}


</body>
</html>