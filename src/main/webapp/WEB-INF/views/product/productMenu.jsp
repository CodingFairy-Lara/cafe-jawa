<%@page import="cafe.jawa.product.model.dto.Product"%>
<%@page import="cafe.jawa.product.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
	List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
	String sub_cate = (String) request.getAttribute("sub_cate");
%>
<!-- sub_category 메뉴 클릭해서 들어왔을때 발생 -->
<% if(sub_cate != null) { %>
	<script>
		$(document).ready(function(){
			go_to_sub_cate("<%= sub_cate %>"); 
		});
	</script>
<% } %>

<section id=product-container>
    <div class="inner">
		<h1>메뉴</h1>
		<div id="menu-category">
			<div class="product_kind_wrap">
				<h2>분류보기</h2>
				<hr>
				<dl class="product_kind_tab">
					<dt class="dt1">
						<a href="javascript:void(0)" id="drinkTab" class="selected">음료</a>
					</dt>
					<dd>
						<div class="product_drink_select">
							<form method="post">
								<fieldset>
									<!-- 음료 카테고리 별 분류 보기 -->
									<div id="drink_menu_container" class="product_menu">
										<ul class="drink_menu">
											<li><input type="checkbox" name="product_all_drink" id="product_all_drink" checked="checked"> <label for="product_all_drink">전체 상품보기</label></li>
											<li><input type="checkbox" name="product_drink" id="product_cold_brew" checked="checked"> <label for="product_cold_brew">콜드 브루 커피</label></li>
											<li><input type="checkbox" name="product_drink" id="product_espresso" checked="checked"> <label for="product_espresso">에스프레소</label></li>
											<li><input type="checkbox" name="product_drink" id="product_frappuccino" checked="checked"> <label for="product_frappuccino">프라푸치노</label></li>
											<li><input type="checkbox" name="product_drink" id="product_blended" checked="checked"> <label for="product_blended">블렌디드</label></li>
											<li><input type="checkbox" name="product_drink" id="product_tea" checked="checked"> <label for="product_tea">티</label></li>
										</ul>
									</div>
								</fieldset>
							</form>
						</div>
					</dd>
					<dt class="dt2">
						<a href="javascript:void(0)" id="foodTab">푸드</a>
					</dt>
					<dd>
						<div class="product_food_select" style="display: none;">
							<form method="post">
								<fieldset>
									<!-- 푸드 카테고리 별 분류 보기 -->
									<div id="food_menu_container" class="product_menu" style="display: relative;">
										<ul class="food_menu">
											<li><input type="checkbox" name="product_all_food" id="product_all_food" checked="checked"> <label for="product_all_food">전체 상품보기</label></li>
											<li><input type="checkbox" name="product_food" id="product_bakery" checked="checked"> <label for="product_bakery">브레드</label></li>
											<li><input type="checkbox" name="product_food" id="product_cake" checked="checked"> <label for="product_cake">케이크</label></li>
											<li style="width: 190px;"><input type="checkbox" name="product_food" id="product_sandwich" checked="checked" > <label for="product_sandwich">샌드위치 &amp; 샐러드</label></li>
											<li><input type="checkbox" name="product_food" id="product_icecream" checked="checked"> <label for="product_icecream">아이스크림</label></li>
										</ul>
									</div>
								</fieldset>
							</form>
						</div>
					</dd>
				</dl>
			</div>
		</div>
		<div id="menu-container">
			<div class="product_list">
				<!-- 음료메뉴 시작 -->
				<dl class="product_list_drink">
					<!-- 콜드 브루 커피 -->
					<dt id="menu_list_coldbrew">
						<a href="javascript:void(0);">콜드 브루 커피</a>
					</dt>
					<dd id="menu_list_coldbrew">
						<ul>
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("CBR  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="콜드 브루 음료 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 에스프레소 -->
					<dt id="menu_list_espresso">
						<a href="javascript:void(0)">에스프레소</a>
					</dt>
					<dd id="menu_list_espresso">
						<ul class="product_espresso">
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("ESP  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="에스프레소 음료 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 프라푸치노 -->
					<dt id="menu_list_frappuccino">
						<a href="javascript:void(0)">프라푸치노</a>
					</dt>
					<dd id="menu_list_frappuccino">
						<ul class="product_frappuccino">
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("FRP  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="프라푸치노 음료 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 블렌디드 -->
					<dt id="menu_list_blended">
						<a href="javascript:void(0)">블렌디드</a>
					</dt>
					<dd id="menu_list_blended">
						<ul class="product_blended">
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("BLD  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="블랜디드 음료 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 티 -->
					<dt id="menu_list_tea">
						<a href="javascript:void(0)">티</a>
					</dt>
					<dd id="menu_list_tea">
						<ul class="product_tea">
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("TEA  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="티 음료 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>
				</dl>
				<!-- 음료메뉴 끝 -->
				
				<!-- 푸드메뉴 시작 -->
				<dl class="product_list_food" class="hide" style="display: none;">
					<!-- 브레드 -->
					<dt id="menu_list_bread">
						<a href="javascript:void(0);">브레드</a>
					</dt>
					<dd id="menu_list_bread">
						<ul>
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("BRD  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" alt="브레드 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 케이크 -->
					<dt id="menu_list_cake">
						<a href="javascript:void(0)">케이크</a>
					</dt>
					<dd id="menu_list_cake">
						<ul>
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("CKE  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" alt="케이크 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 샌드위치 & 샐러드 -->
					<dt id="menu_list_sandwich">
						<a href="javascript:void(0)">샌드위치 & 샐러드</a>
					</dt>
					<dd id="menu_list_sandwich">
						<ul>
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("SND  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" alt="샌드위치 & 샐러드 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>

					<!-- 아이스크림 -->
					<dt id="menu_list_icecream">
						<a href="javascript:void(0)">아이스크림</a>
					</dt>
					<dd id="menu_list_icecream">
						<ul>
		<% 
			for(Product product : productList){
				if(product.getSubCategory().equals("ICR  ")) {
		%>
							<li class="menuDataSet">
								<dl>
									<dt class="goDrinkView">
										<a href="javascript:void(0);" class="add_to_cart">
											<img src="<%= request.getContextPath() %>/images/add_to_cart.png" alt="장바구니 추가" onclick="javascript:testEncoding('<%= product.getProductId()%>', '<%= product.getSubCategory()%>');">
										</a>
										<a href="<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>" class="goDrinkView" name="<%= product.getProductId() %>">
		<% 
			for(Attachment attachment : attachmentList){
				if(attachment.getProductId() == product.getProductId()) {
		%>
											<img src="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" alt="아이스크림 이미지">
		<% 	}}	%>						
										</a>
									</dt>
									<dd><%= product.getProductName() %></dd>
								</dl>
							</li>
		<% }}	%>
						</ul>
					</dd>
				</dl>
				<!-- 푸드메뉴 끝 -->
			</div>
		</div>
		    
		    
    </div>
</section>
<script>

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
