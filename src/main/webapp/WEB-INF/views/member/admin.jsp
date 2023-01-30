<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cafe.jawa.store.model.dto.Store" %>
<%@ page import="cafe.jawa.product.model.dto.Product" %>
<%@ page import="java.util.List" %>
<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
%>

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
<section id="price_Change">
	<h2>가격 변경</h2>
	<form name="priceChangeFrm" method="post" action="<%= request.getContextPath() %>/product/productPriceChange">
		<label for="cars">수정할 물품 선택 : </label>
		<input type="hidden" name="productName" id="productName">
		<select id="product" name="product" onchange=productChoose(this.value)>
			<option value="">수정할 물품 선택</option>
			<% for(Product product : productList){ %>
			<option value="<%= product.getProductName() %>"><%= product.getProductName() %> </option>
			<%} %>
			<input type="text" name="price" id="price" placeholder="수정할 가격 입력" /><br>
			<input type="submit" name="priceChange" id="priceChange" value="변경" />
			<input type="button" value="취소" onclick="history.go(-1)" />
		</select>
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
	
	const productChoose = function(value) {
		$("#productName").val(value);
	}
	
	document.priceChangeFrm.onsubmit = (e) => {
		const productName = document.querySelector("#productName");
		const price = document.querySelector("#price");
		
		if(!/^[0-9]{4,}$/.test(price.value)) {
			alert("올바른 가격을 입력해 주세요.");
			price.select();
			return false;
		}
	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>