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
											<li><input type="checkbox" name="product_tea" id="product_tea"> <label for="product_tea">티</label></li>
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
			<div class="product_list">
				<!-- 음료메뉴 시작 -->
				<dl>
					<!-- 콜드 브루 커피 -->
					<dt style="display: block;">
						<a href="javascript:void(0);">콜드 브루 커피</a>
					</dt>
					<dd style="display: block;">
						<ul class="product_cold_brew">
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000000479">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000000479]_20210426091843897.jpg" alt="나이트로 콜드 브루">
										</a>
									</dt>
									<dd>나이트로 콜드 브루</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000002081">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002081]_20210415133656839.jpg" alt="돌체 콜드 브루">
										</a>
									</dt>
									<dd>돌체 콜드 브루</dd>
								</dl>
							</li><li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000004312">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2022/10/[9200000004312]_20221005145029134.jpg" alt="민트 콜드 브루">
										</a>
									</dt>
									<dd>민트 콜드 브루</dd>
								</dl>
							</li><li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000000487">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000000487]_20210430112319040.jpg" alt="바닐라 크림 콜드 브루">
										</a>
									</dt>
									<dd>바닐라 크림 콜드 브루</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000000038">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000000038]_20210430113202458.jpg" alt="콜드 브루">
										</a>
									</dt>
									<dd>콜드 브루</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>             
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000001636">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000001636]_20210225093600536.jpg" alt="콜드 브루 몰트">
										</a>
									</dt>
									<dd>콜드 브루 몰트</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000003285">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000003285]_20210416154437069.jpg" alt="콜드 브루 오트 라떼">
										</a>
									</dt>
									<dd>콜드 브루 오트 라떼</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000001635">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000001635]_20210225092236748.jpg" alt="콜드 브루 플로트">
										</a>
									</dt>
									<dd>콜드 브루 플로트</dd>
								</dl>
							</li>
						</ul>
					</dd>

					<!-- 에스프레소 -->
					<dt style="display: block;">
						<a href="javascript:void(0)">에스프레소</a>
					</dt>
					<dd style="display: block;">
						<ul class="product_espresso">
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="110563">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110563]_20210426095937808.jpg" alt="아이스 카페 아메리카노">
										</a>
									</dt>
									<dd>아이스 카페 아메리카노</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="126197">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[126197]_20210415154609863.jpg" alt="카라멜 마키아또">
										</a>
									</dt>
									<dd>카라멜 마키아또</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="38">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[38]_20210415154821846.jpg" alt="카푸치노">
										</a>
									</dt>
									<dd>카푸치노</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000001939">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000001939]_20210225094313315.jpg" alt="바닐라 빈 라떼">
										</a>
									</dt>
									<dd>바닐라 빈 라떼</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="110569">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110569]_20210415143035989.jpg" alt="아이스 카페 라떼">
										</a>
									</dt>
									<dd>아이스 카페 라떼</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000004313">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2022/10/[9200000004313]_20221005145156959.jpg" alt="클래식 민트 모카">
										</a>
									</dt>
									<dd>클래식 민트 모카</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="20">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[20]_20210415144112678.jpg" alt="에스프레소">
										</a>
									</dt>
									<dd>에스프레소</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000001631">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/02/[9200000001631]_20210225090916684.jpg" alt="클래식 아포가토">
										</a>
									</dt>
									<dd>클래식 아포가토</dd>
								</dl>
							</li>
						</ul>
					</dd>

					<!-- 프라푸치노 -->
					<dt style="display: block;">
						<a href="javascript:void(0)">프라푸치노</a>
					</dt>
					<dd style="display: block;">
						<ul class="product_frappuccino">
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000002760">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002760]_20210415133558068.jpg" alt="더블 에스프레소 칩 프라푸치노">
										</a>
									</dt>
									<dd>더블 에스프레소 칩 프라푸치노</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="168010">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[168010]_20210415154711116.jpg" alt="카라멜 프라푸치노">
										</a>
									</dt>
									<dd>카라멜 프라푸치노</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="168054">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[168054]_20210415135751840.jpg" alt="바닐라 크림 프라푸치노">
										</a>
									</dt>
									<dd>바닐라 크림 프라푸치노</dd>         
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000002502">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000002502]_20210426100408048.jpg" alt="제주 유기농 말차로 만든 크림 프라푸치노">
										</a>
									</dt>
									<dd>제주 유기농 말차로 만든 크림 프라푸치노</dd>
								</dl>
							</li>
						</ul>
					</dd>

					<!-- 블렌디드 -->
					<dt style="display: block;">
						<a href="javascript:void(0)">블렌디드</a>
					</dt>
					<dd style="display: block;">
						<ul class="product_blended">
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="167004">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[167004]_20210419130801597.jpg" alt="망고 패션 프루트 블렌디드">
										</a>
									</dt>
									<dd>망고 패션 프루트 블렌디드</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000003276">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000003276]_20210416154001403.jpg" alt="딸기 딜라이트 요거트 블렌디드">
										</a>
									</dt>
									<dd>딸기 딜라이트 요거트 블렌디드</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="169001">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[169001]_20210419130701792.jpg" alt="망고 바나나 블렌디드">
										</a>
									</dt>
									<dd>망고 바나나 블렌디드</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="9200000003443">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9200000003443]_20210416154107695.jpg" alt="민트 초콜릿 칩 블렌디드">
										</a>
									</dt>
									<dd>민트 초콜릿 칩 블렌디드</dd>
								</dl>
							</li>
						</ul>
					</dd>

					<!-- 티 -->
					<dt style="display: block;">
						<a href="javascript:void(0)">티</a>
					</dt>
					<dd style="display: block;">
						<ul class="product_tea">
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="400400000094">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[400400000094]_20210415230316469.jpg" alt="아이스 제주 유기 녹차">
										</a>
									</dt>
									<dd>아이스 제주 유기 녹차</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="4004000000079">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[4004000000079]_20210415143641139.jpg" alt="아이스 캐모마일 블렌드 티">
										</a>
									</dt>
									<dd>아이스 캐모마일 블렌드 티</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="4004000000069">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[4004000000069]_20210415143811231.jpg" alt="아이스 히비스커스 블렌드 티">
										</a>
									</dt>
									<dd>아이스 히비스커스 블렌드 티</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="400400000091">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[400400000091]_20210415132229904.jpg" alt="제주 유기 녹차">
										</a>
									</dt>
									<dd>제주 유기 녹차</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="4004000000076">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[4004000000076]_20210415154920731.jpg" alt="캐모마일 블렌드 티">
										</a>
									</dt>
									<dd>캐모마일 블렌드 티</dd>
								</dl>
							</li>
							<li class="menuDataSet" new="N" sell="0" recomm="0" sold="N">
								<dl>
									<dt>
										<a href="javascript:void(0)" class="goDrinkView" prod="4004000000066">
											<img src="https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[4004000000066]_20210415155836395.jpg" alt="히비스커스 블렌드 티">
										</a>
									</dt>
									<dd>히비스커스 블렌드 티</dd>
								</dl>
							</li>
						</ul>
					</dd>
				</dl>
				<!-- 음료메뉴 끝 -->

			</div>
		</div>
		    
		    
    </div>
</section>

<script>

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
