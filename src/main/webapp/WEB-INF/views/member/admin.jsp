<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cafe.jawa.store.model.dto.Store" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id="storeControl">
	<h2>매장 오픈/클로즈</h2>
	<form name="StoreOpenFrm" method="post" action="<%= request.getContextPath() %>/store/storeOpen">
		<table>
			<tr>
				<th>매장번호</th>
				<td><input type="text" name="store_id" id="store_id"></td>
			</tr>
			<tr>
				<th>오픈/클로즈</th>
				<td><input type="text" name="storeStatus" id="storeStatus" placeholder="오픈시 O 클로즈시 C를 입력">
			</tr>
			<tr>
				<td>
					<input type="submit" name="storeOpen" id="storeOpen" value="매장 오픈/클로즈" />
				</td>
				<td>
					<input type="button" value="취소" onclick="history.go(-1)" />
				</td>
			</tr>
		</table>
	</form>
</section>
<script>
	document.StoreOpenFrm.onsubmit = (e) => {
		const store_id = document.querySelector("#store_id");
		
		if(!/^00[1-3]{1}$/.test(store_id.value)) {
			alert("매장번호가 유효하지 않습니다.");
			store_id.select();
			return false;
		}
	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>