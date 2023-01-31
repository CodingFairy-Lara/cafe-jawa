<%@page import="cafe.jawa.product.model.dto.Product"%>
<%@page import="cafe.jawa.product.model.dto.Attachment"%>
<%@page import="cafe.jawa.product.model.dto.OrderedProduct"%>
<%@page import="cafe.jawa.cart.model.dto.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
	List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
	List<OrderedProduct> orderedProductList = (List<OrderedProduct>) request.getAttribute("orderedProductList");
	List<Cart> cartList = (List<Cart>) request.getAttribute("cartList");
    List<String> opIdList = (List<String>) request.getAttribute("opIdList");
    String storeId = (String) request.getAttribute("storeId");
    int totalPrice = (int) request.getAttribute("totalPrice");
%>

<section id=payment-view-container>
    <div class="inner">
        <!-- 결제화면 헤더 -->
	    <div class="payment_header">
	        <div class="inner">
	            <h1 class="payment_header">결제하기</h1>
	        </div>
	    </div>
	    <div class="payment_content inner">
				<div class="payment_totPrice_info">
					<h2>결제 정보</h2>
				</div>
				<div class="payment_payment_totPrice_info_container">
					<input type="hidden" name="totalPrice" id="totalPrice" value="<%= totalPrice %>">
					<div class="payment_price_info">
						<h3 id="total_price">주문 금액 : 0 원</h3>
						<h3>할인 금액 : 0 원</h3>
						<h2 id="total_price_final" class="">총 주문 금액 : 0 원</h2>
					</div>
				</div>
				<div class="payment_method_header">
					<h2>결제 수단</h2>
				</div>
				<div class="payment_method_container">
					<div class="payment_option_div">
						<div id="option_credit_card" class="payment_option selected">
							신용카드
						</div>
						<div id="option_mobile_check" class="payment_option">
							모바일 결제
						</div>
					</div>
					<!-- 신용카드 결제 폼 -->
					<div id="credit_card_div" class="credit_card_div">
						<div class="subdiv credit_card_form" id="credit_card_form_div">
							<form action="<%= request.getContextPath() %>/order/control" method="post" class="credit_card_form">
								<h1 class="credit_card_form">신용카드</h1>
								<section class="credit_card_form">        
								  <label for="cc-number" class="credit_card_form">카드 번호</label>
								  <input id="cc-number" name="cc-number" inputmode="numeric" autocomplete="cc-number" maxlength="50" pattern="[\d ]{10,30}" class="credit_card_form" required>
								</section>
						
								<section class="credit_card_form" >        
								  <label for="cc-name" class="credit_card_form">소유주 성명</label>
								  <input id="cc-name" name="cc-name" autocomplete="cc-name" maxlength="30" pattern="[\p{L} \-\.]+" required class="credit_card_form">
								</section>
								
								<section id="cc-exp-csc" class="credit_card_form">      
								  <div>
									<label for="cc-exp" class="credit_card_form">유효기간</label>
									<input id="cc-exp" name="cc-exp" placeholder="MM/YY" maxlength="5" autocomplete="cc-exp" required class="credit_card_form">
								  </div> 
								  <div>
									<label for="cc-csc" class="credit_card_form">보안코드</label>
									<input id="cc-csc" name="cc-csc" inputmode="numeric" maxlength="3" autocomplete="cc-csc" required class="credit_card_form">
									<div class="explanation credit_card_form" >카드 뒷면 3 자리를 입력하세요.</div>
								  </div>
								</section>
								<div style="display : none;">
									<input type="hidden" name="memberId" class="credit_card_form" value="<%= loginMember.getMemberId() %>">
									<input type="hidden" name="payment_totPrice" class="credit_card_form" value="<%= totalPrice %>">
									<input type="hidden" name="payment_type" class="credit_card_form" value="card">
									<input type="hidden" name="storeId" class="credit_card_form" value="<%= storeId %>">
				<% for(String opId : opIdList) { %>
									<input type="hidden" name="opIdList" class="credit_card_form" value="<%= opId %>">
				<% } %>
								</div>  
								<button id="complete-payment_cc" class="credit_card_form">결제하기</button>
							  </form>
						</div>
					</div>

					<!-- 모바일 결제 폼 -->
					<div id="mobile_check_div" class="mobile_check_div" style="display: none;">
						<div class="subdiv mobile_check_form" id="mobile_check_form_div">
							<form action="<%= request.getContextPath() %>/order/control" method="post" class="mobile_check_form">
								<h1 class="mobile_check_form">모바일 결제</h1>
								<section class="mobile_check_form">        
								  <label for="mobile-number" class="mobile_check_form">전화 번호</label>
								  <input id="mobile-number" name="mobile-number" oninput="javascript:hypenTel(this)" inputmode="numeric" autocomplete="mobile-number" maxlength="13" pattern="[\d ]{13, 15}" class="mobile_check_form" required>
								</section>
								<section id="mobile-exp-csc" class="mobile_check_form">      
									<div>
										<label for="mobile-csc" class="mobile_check_form">결제 인증번호</label>
										<input id="mobile-csc" name="mobile-csc" inputmode="numeric" maxlength="6" autocomplete="mobile-csc" required disabled class="mobile_check_form">
										<div>
											<button class="abutton" id="mobile_certifi_num_btn" onclick="javascript:sendText();">인증번호 전송</button>
										</div>
										<div id="explanation_mobile_check" class="explanation mobile_check_form" style="visibility : hidden;">결제 인증번호 6자리를 입력하세요.</div>
									</div>
								</section>
								<div style="display : none;">
									<input type="hidden" name="memberId" class="mobile_check_form" value="<%= loginMember.getMemberId() %>">
									<input type="hidden" name="payment_totPrice" class="mobile_check_form" value="<%= totalPrice %>">
									<input type="hidden" name="payment_type" class="mobile_check_form" value="mobile">
									<input type="hidden" name="storeId" class="mobile_check_form" value="<%= storeId %>">
				<% for(String opId : opIdList) { %>
									<input type="hidden" name="opIdList" class="mobile_check_form" value="<%= opId %>">
				<% } %>
								</div>  
								<button id="complete-payment_mb" class="mobile_check_form">결제하기</button>
							  </form>
						</div>
					<div class="subdiv"></div>
				</div>
        
		</div>

	</div>
</section>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/productOrder.js"></script>
<script type="text/javascript">

	window.addEventListener('load', () => {
		changeIntoNumber();
	});

	function sendText() {
		event.preventDefault();
		if ( $("#mobile-number").val().length === 13) {
			alert("CAFEJAWA : 결제 인증번호 [001234] 입니다.");
			$("div#explanation_mobile_check").css('visibility', 'visible');
			$("input#mobile-csc").attr("disabled", false);
		}else {
			alert("올바른 전화번호를 입력해주세요!");
		}
	}


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>