<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<section id=enroll-container>
	<h2>비밀번호 변경</h2>
	<form 
		name="passwordUpdateFrm" 
		method="post" >
		<table>
			<tr>
				<th>현재 비밀번호</th>
				<td><input type="password" name="oldPassword" id="oldPassword" required></td>
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td>
					<input type="password" name="newPassword" id="newPassword" required>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>	
					<input type="password" id="newPasswordCheck" required><br>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit"  value="변경" />
				</td>
				<td colspan="2" style="text-align: center;">
					<input type="button" value="취소" onclick="history.go(-1)" />
				</td>
			</tr>
		</table>
	</form>
</section>
<script>
const oldPassword = document.querySelector("#oldPassword");
const newPassword = document.querySelector("#newPassword");
const newPasswordCheck = document.querySelector("#newPasswordCheck");
/**
 * 폼제출 유효성검사
 */ 
document.passwordUpdateFrm.onsubmit = function(){
	
	if(!passwordValidate()){
		return false;
	}
	
	if(oldPassword.value == newPassword.value){
		alert("기존비밀번호와 일치합니다.");
		oldPassword.select();
		return false;
	}
};

document.querySelector("#newPasswordCheck").onblur(passwordValidate);

/**
 * 신규비밀번호 일치 검사 
 */ 
function passwordValidate(){
	if(newPassword.value !== newPasswordCheck.value){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		newPassword.select();
		return false;
	}
	return true;	
}
</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
