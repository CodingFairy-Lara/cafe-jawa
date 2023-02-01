<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cafe.jawa.store.model.dto.Store" %>
<%@ page import="cafe.jawa.product.model.dto.Product" %>
<%@ page import="java.util.List" %>
<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="inner">
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
<section>
	<h2>주문 관리</h2>
	<input type="button" onclick="location.href='<%= request.getContextPath() %>/admin/orderControl';" value="주문 관리 페이지" class="abutton" />
</section>
</div>

<script>
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