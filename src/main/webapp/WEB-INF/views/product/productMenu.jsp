<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=product-container>
    <div class="inner">
		<h1>메뉴</h1>
		<div id="menu-category">
			<div class="product_kind_wrap">
				<h2>분류보기</h2>
				<hr>
				<dl class="product_kind_tab">
					<dt class="dt1"><a href="javascript:void(0)" id="categoryTab" class="selected" role="button">음료</a></dt>
					<dd>
						<div class="product_drink_select">
							<form method="post">
								<fieldset>
									<!-- 음료 카테고리 별 분류 보기 -->
									<div id="drink_menu_container" class="product_menu">
										<ul class="drink_menu">
											<li><input type="checkbox" name="product_all" id="product_all"> <label for="product_all">전체 상품보기</label></li>
											<li><input type="checkbox" name="product_cold_brew" id="product_cold_brew"> <label for="product_cold_brew">콜드 브루 커피</label></li>
											<li><input type="checkbox" name="product_espresso" id="product_espresso"> <label for="product_espresso">에스프레소</label></li>
											<li><input type="checkbox" name="product_frappuccino" id="product_frappuccino"> <label for="product_frappuccino">프라푸치노</label></li>
											<li><input type="checkbox" name="product_blended" id="product_blended"> <label for="product_blended">블렌디드</label></li>
											<li><input type="checkbox" name="product_tea" id="product_tea"> <label for="product_tea">티(티바나)</label></li>
										</ul>
									</div>
								</fieldset>
							</form>
						</div>
					</dd>
					<dt class="dt2"><a href="javascript:void(0)" id="themeTab" title="푸드 카테고리 보기">푸드</a></dt>
					<dd  style="display: none;">
						<div class="product_food_select">
							<form method="post">
								<fieldset>
									<!-- 푸드 카테고리 별 분류 보기 -->
									<div id="food_menu_container" class="product_menu" style="display: relative;">
										<ul class="food_menu">
											<li><input type="checkbox" name="product_all" id="product_all" checked="checked"> <label for="product_all">전체 상품보기</label></li>
											<li><input type="checkbox" name="product_bakery" id="product_bakery"> <label for="product_bakery">브레드</label></li>
											<li><input type="checkbox" name="product_cake" id="product_cake"> <label for="product_cake">케이크</label></li>
											<li style="width: 190px;"><input type="checkbox" name="product_sandwich" id="product_sandwich"> <label for="product_sandwich">샌드위치 &amp; 샐러드</label></li>
											<li><input type="checkbox" name="product_icecream" id="product_icecream"> <label for="product_icecream">아이스크림</label></li>
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
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		



			<p>메뉴입니다....</p>
		</div>
		
		
		
		
		    
		    
		    
    </div>
</section>
<script>
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
