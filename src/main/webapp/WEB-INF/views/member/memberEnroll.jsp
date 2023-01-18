<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>회원 가입 정보 입력</h2>
	<form 
		name="memberEnrollFrm" 
		method="POST" 
		action="<%= request.getContextPath() %>/member/memberEnroll">
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="6글자이상" name="memberId" id="_memberId" required>
					<input type="button" value="아이디 중복확인" onclick="checkIdDuplicate();"/>
					<input type="hidden" id="idValid" name="idValid" value="0"/>
					<%-- 사용가능한 아이디인 경우 1, 이미 사용중인 아이디인 경우 0 --%>
				</td>
			</tr>
			<tr>
				<th>패스워드<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="_password" required><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인<sup>*</sup></th>
				<td>	
					<input type="password" id="passwordCheck" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="memberName" id="memberName" required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="1997-03-22"><br/>
				</td>
			</tr> 
			<tr>
				<th>이메일<sup>*</sup></th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" required><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="-없이 입력" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
	</form>
</section>
<form action="<%= request.getContextPath() %>/member/checkIdDuplicate" name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>
<script>
/**
 * 중복검사이후 다시 아이디를 수정한 경우.
 */
document.querySelector("#_memberId").onfocus = (e) => {
	document.querySelector("#idValid").value = "0";
};

const checkIdDuplicate = () => {
	const memberId = document.querySelector("#_memberId");
	if(!/^[A-Za-z0-9]{6,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 6글자이상이어야합니다.");
		memberId.select();
		return;
	};
	
	// frm의 action주소를 사용하기 때문에 open의 url은 비워둔다.
	const title = "checkIdDuplicatePopup"; 
	open("", title, "width=300px, height=200px, left=100px, top=100px");
	
	const frm = document.checkIdDuplicateFrm 
	frm.target = title; // 폼을 팝업에 제출
	frm.memberId.value = memberId.value;
	frm.submit();
	
};

document.memberEnrollFrm.onsubmit = (e) => {
	const memberId = document.querySelector("#_memberId");
	const idValid = document.querySelector("#idValid");
	const password = document.querySelector("#_password");
	const passwordCheck = document.querySelector("#passwordCheck");
	const memberName = document.querySelector("#memberName");
	const phone = document.querySelector("#phone");

	// 아이디 - 영문자/숫자 6글자이상
	if(!/^[A-Za-z0-9]{6,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 6글자이상이어야합니다.");
		memberId.select();
		return false;
	}
	
	// 아이디중복검사 통과여부
	if(idValid.value !== '1'){
		alert("아이디 중복검사 해주세요.");
		memberId.nextElementSibling.focus(); // 중복검사 버튼
		return false;
	}
	
	// 비밀번호 - 영문자/숫자/특수문자 !@#$% 8글자이상
	if(!/^[A-Za-z0-9!@#$%]{8,12}$/.test(password.value)){
		alert("비밀번호는 영문자/숫자/특수문자!@#$%로 구성된 8글자이상 12글자이하이어야합니다.");
		password.select();
		return false;
	}
	
	// 비밀번호/비밀번호확인 동일
	if(password.value !== passwordCheck.value){
		alert("두 비밀번호가 일치하지 않습니다.");
		password.select();
		return false;
	}
	
	// 이름 - 한글 2글자이상
	if(!/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		memberName.select();
		return false;
	}
	
	// 전화번호는 숫자 01012345678 형식
	if(!/^010[0-9]{8}$/.test(phone.value)){
		alert("전화번호가 유효하지 않습니다.");
		phone.select();
		return false;
	}
	
};
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
