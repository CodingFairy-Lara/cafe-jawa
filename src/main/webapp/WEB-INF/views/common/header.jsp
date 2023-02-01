<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cafe.jawa.member.model.dto.Member" %>
<%@ page import="cafe.jawa.member.model.dto.MemberRole" %>
<%@ page import="cafe.jawa.order.model.dto.Order" %>
<%@ page import="cafe.jawa.order.model.dto.Payment" %>
<%@ page import="java.util.List"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
 	List<Order> orderListAll = (List<Order>) session.getAttribute("orderListAll");
 	List<Order> userOrderList = (List<Order>) session.getAttribute("userOrderList");
 	List<Payment> paymentList = (List<Payment>) session.getAttribute("paymentList");
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	MemberRole user = MemberRole.valueOf("U");
	MemberRole admin = MemberRole.valueOf("A");
	String user_orderStats = null;
	String user_ordermsg = null;
	String admin_orderStats = null;
	String admin_ordermsg = null;
	String user_store = null;
%>
	
<%	
	// 사용자 계정 로그인시 
	if (userOrderList != null) {
	for (Order order : userOrderList) {
        switch (order.getStoreId()) {
        case "003":  user_store = "CAFE JAWA 동탄점";
                 break;
        case "002":  user_store = "CAFE JAWA 인천점";
                 break;
        case "001":  user_store = "CAFE JAWA 잠실점";
                 break;
    	}
		
        switch (order.getStatus()) {
        case 5:  user_orderStats = "상품 준비완료";
	 		 user_ordermsg = "" + loginMember.getMemberName() + "님의 주문 상품이 모두 준비되었습니다! 😉 \\n"+"[ " + user_store + "] 에서 상품을 픽업해주세요 🤍"; break;
        case 4:  user_orderStats = "상품 준비중...";
			 user_ordermsg = ""+ loginMember.getMemberName() + "님의 주문 상품이 10초 뒤에 준비 완료될 예정입니다! ☕ ️\\n"+"상품이 준비되면 "+"[ " + user_store + "] 에서 픽업해주세요 🤍"; break;
        case 3:  user_orderStats = "상품 준비중";
 			 user_ordermsg = "[ " + user_store + " ]에서 "+ loginMember.getMemberName() + "님의 주문을 확인했으며, 상품을 준비하고 있습니다! ☕ ️\\n"+"상품이 준비가 완료되면 픽업해주세요 🤍"; break;
        case 2:  user_orderStats = "결제 완료";
		 	 user_ordermsg = "" + loginMember.getMemberName() + "님의 주문 결제가 완료되었습니다! 🍰 \\n"+"[ " + user_store + "] 에서 주문을 확인중입니다. \\n"+"잠시만 기다려주세요 🤍"; break;
        case 1:  user_orderStats = "결제 오류";
   			 user_ordermsg = "" + loginMember.getMemberName() + "님의 주문이 완료되었으나, 결제가 되지 않았습니다. 🥹 "+"주문 목록을 지우고 다시 결제를 시도해주세요!"; break;
        default: user_orderStats = null; user_ordermsg = null; break;
    	}
	}
		session.setAttribute("user_orderStats", user_orderStats);
		session.setAttribute("user_ordermsg", user_ordermsg);
	}
%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<!-- 웹페이지가 화면(Viewport)에 표현되는 방식을 설정합니다. 모바일 환경에서 적용됩니다. -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>CAFE JAWA</title>
	<!-- css초기화 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" />
	<!-- 폰트(나눔고딕) -->
	<link rel="preconnect" href="https://fonts.gstatic.com" />
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet" />
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.elevatezoom.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/productMenu.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/adminMenu.js"></script>
	<!-- Toastr -->
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>  
  <script>
  window.addEventListener('load', () => {
    <% if(msg != null) { %>
      alert("<%= msg %>"); 
    <% } %>
    
    toastr.options = {
    		  "closeButton": true,
    		  "debug": false,
    		  "newestOnTop": false,
    		  "progressBar": false,
    		  "positionClass": "toast-bottom-full-width",
    		  "preventDuplicates": true,
    		  "onclick": null,
    		  "showDuration": "300",
    		  "hideDuration": "1000",
    		  "timeOut": "3000",
    		  "extendedTimeOut": "0",
    		  "showEasing": "swing",
    		  "hideEasing": "linear",
    		  "showMethod": "fadeIn",
    		  "hideMethod": "fadeOut"
    		}
    
    
    /* toastr - user */
    setInterval(function() {
    	
	    <% if(user_orderStats != null) { 
	    	if(user_orderStats.equals("결제 오류")) { %>
	    	toastr.error('<%= user_ordermsg %>','<%= user_orderStats %>');
	    <%	} else if(user_orderStats.equals("결제 완료")) { %>
	    	toastr.warning('<%= user_ordermsg %>','<%= user_orderStats %>');
	    	<% } else if (user_orderStats.equals("상품 준비중")) { %>
	    	toastr.info('<%= user_ordermsg %>','<%= user_orderStats %>');
	    	<% } else if (user_orderStats.equals("상품 준비중...")) { %>
	    	toastr.error('<%= user_ordermsg %>','<%= user_orderStats %>');
	    	<% } else if (user_orderStats.equals("상품 준비완료")) { %>
	    	toastr.success('<%= user_ordermsg %>','<%= user_orderStats %>');
	    <% }} %>
	    /* toastr - admin */
	    <% if (orderListAll != null) {
		for (Order order : orderListAll) {
		    switch (order.getStoreId()) {
		    case "003":  user_store = "CAFE JAWA 동탄점";
		             break;
		    case "002":  user_store = "CAFE JAWA 인천점";
		             break;
		    case "001":  user_store = "CAFE JAWA 잠실점";
		             break;
			}
			
		   		 if (order.getStatus() == 5) {
				admin_orderStats = "상품 준비완료 / 수령대기";
				admin_ordermsg = "[ " + user_store + " ]에서 준비중인 [ 주문번호 : " + order.getOrderNum() + "] 상품이 준비 완료되었습니다! 😉 "; %>
					toastr.success('<%= admin_ordermsg %>','<%= admin_orderStats %>');
          setTimeout(() => updateOrderStatus, 60000, "<%= order.getOrderNum() %>", "<%= order.getStatus() %>");
			<% } else if (order.getStatus() == 4) {
				admin_orderStats = "상품 준비중...";
				admin_ordermsg = "[ " + user_store + " ]에서 준비중인 [ 주문번호 : " + order.getOrderNum() + "] 상품이 10초 뒤에 준비 완료될 예정입니다! 🤍"; %>
					toastr.error('<%= admin_ordermsg %>','<%= admin_orderStats %>');
          setTimeout(() => updateOrderStatus, 10000, "<%= order.getOrderNum() %>", "<%= order.getStatus() %>");
			<% } else if (order.getStatus() == 3) {
				admin_orderStats = "상품 준비중";
				admin_ordermsg = "[ " + user_store + " ]에서 [ 주문번호 : " + order.getOrderNum() + "] 요청을 수락했으며, 상품을 준비하고 있습니다! ☕ "; %>
					toastr.info('<%= admin_ordermsg %>','<%= admin_orderStats %>');
          setTimeout(() => updateOrderStatus, 60000, "<%= order.getOrderNum() %>", "<%= order.getStatus() %>");
			<% } else if (order.getStatus() == 2) {
				admin_orderStats = "주문확인 요청";
				admin_ordermsg = "[ " + user_store + "] 에 새로운 주문이 들어왔습니다! 🍰 \\n"+"[ 주문번호 : " + order.getOrderNum() + "] 주문요청을 확인하고 상품준비를 시작하세요."; %>
					toastr.warning('<%= admin_ordermsg %>','<%= admin_orderStats %>');
			<% } else { admin_orderStats = null; admin_ordermsg = null;  }

			}} %>
    	
    	}, 5000);
    
    
    /* user setInterval - ajax로 5초마다 user회원정보 불러옴 */
    <% if(loginMember != null && loginMember.getMemberRole() == MemberRole.valueOf("U")) { %>
    	var intervalId = setInterval(getNewOrderList_user, 3000)
    	<% session.setAttribute("orderListAll", orderListAll); } %>
    /* admin setInterval - ajax로 5초마다 회원정보 불러옴 */
    <% if(loginMember != null && loginMember.getMemberRole() == MemberRole.valueOf("A")) { %>
    	var intervalId = setInterval(getNewOrderList, 3000)
    	<% session.setAttribute("userOrderList", userOrderList); } %>
    
    	<% if (loginMember != null && loginMember.getMemberRole() == MemberRole.valueOf("A")) {
    		boolean flag = false;
         for (Order order : orderListAll) {
	   		 if ((order.getStatus() == 5 &&  flag == false) || (order.getStatus() == 4 && flag == true) || (order.getStatus() == 3 && flag == false)) { %>
      	updateUserStatus("<%= order.getOrderNum() %>", "<%= order.getStatus() %>");
    	<% flag = true;
    	}}} %>
    	
    	
  });

</script>
</head>
<body>
	<div id="container">
    <header>
        <div class="inner">

          <!-- 로고 -->
          <a href="<%= request.getContextPath() %>/" class="logo">
            <img src="<%= request.getContextPath() %>/images/cafe_jawa_logo.png" alt="CAFE JAWA" style="width: 100px;  height: 100px;">
          </a>

      <div class="sub-menu">
      <% if(loginMember == null) { %>
        <ul class="menu">
          <li>
            <a href="<%= request.getContextPath() %>/member/memberEnroll">Sign Up</a>
          </li>
          <li>
          	<a href="<%= request.getContextPath() %>/member/login">Sign In</a>
          </li>
          <li>
            <a href="<%= request.getContextPath() %>/member/login">My CAFE JAWA</a>
          </li>
        </ul>
      <% } else if (loginMember.getMemberRole() == user) { %>
      	<ul class="loginMenu">
      	  <li>
      		<a href="<%= request.getContextPath() %>/member/logout">Sign out</a>
      	  </li>
      	  <li>
      	  	<a href="<%= request.getContextPath() %>/member/memberView">My CAFE JAWA</a>
      	  </li>
      	  <li>
            <a href="<%= request.getContextPath() %>/cart/cartView">My Cart 🛒</a>
          </li>
      	 </ul>
      <% } else if (loginMember.getMemberRole() == admin) {%>
    	<ul class="loginMenu">
    	  <li>
    		<a href="<%= request.getContextPath() %>/member/logout">Sign out</a>
    	  </li>
    	  <li>
    	  	<a href="<%= request.getContextPath() %>/member/memberView">My CAFE JAWA</a>
    	  </li>
    	  <li>
          <a href="<%= request.getContextPath() %>/member/admin">ADMIN</a>
        	</li>
    	 </ul>
	<% } %>
      </div>

          <ul class="main-menu">
            <li class="item">
              <div class="item__name">NOTICE</div>
                <div class="item__contents">
                  <div class="contents__menu">
                    <ul class="inner">
                      <li>
                        <h4>공지사항</h4>
                        <ul>
                          <li onclick="location.href='<%= request.getContextPath() %>/board/boardList';">공지사항 게시판</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="contents__texture">
                    <div class="inner">
                      <h4>나와 어울리는 커피 찾기</h4>
                      <p>CAFE JAWA가 여러분에게 어울리는 커피를 찾아드립니다.</p>
                      <h4>최상의 커피를 즐기는 법</h4>
                      <p>여러가지 방법을 통해 다양한 풍미의 커피를 즐겨보세요.</p>
                    </div>
                  </div>
                </div>
              </li>
              <li class="item">
                <div class="item__name">
                      <h4 onclick="location.href='<%= request.getContextPath() %>/product/menu';">MENU</h4>
                </div>
                <div class="item__contents">
                  <div class="contents__menu">
                    <ul class="inner">
                      <li>
                        <h4>음료</h4>
                        <ul>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_cold_brew';">콜드 브루</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_espresso';">에스프레소</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_frappuccino';">프라푸치노</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_blended';">블렌디드 음료</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_tea';">티</li>
                        </ul>
                      </li>
                      <li>
                        <h4>푸드</h4>
                        <ul>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_bakery';">베이커리</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_cake';">케이크</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_sandwich';">샌드위치 &amp; 샐러드</li>
                          <li onclick="location.href='<%= request.getContextPath() %>/product/menu?sub_cate=product_icecream';">아이스크림</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="contents__texture">
                    <div class="inner">
                      <h4 class="new">CAFE JAWA 티바나</h4>
                      <p>다양한 찻잎과 향신료 등 개성있는 재료로 새로운 맛과 향의 티를 선보입니다.</p>
                    </div>
                  </div>
                </div>
              </li>
              <li class="item">
                <div class="item__name">STORE</div>
                <div class="item__contents">
                  <div class="contents__menu">
                    <ul class="inner">
                      <li>
                        <h4>매장 이야기</h4>
                        <ul>
                          	<li onclick="location.href='<%= request.getContextPath() %>/store/jamsil';">CAFE JAWA 잠실점</li>
							<li onclick="location.href='<%= request.getContextPath() %>/store/incheon';">CAFE JAWA 인천점</li>
							<li onclick="location.href='<%= request.getContextPath() %>/store/dongtan';">CAFE JAWA 동탄점</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="contents__texture">
                    <div class="inner">
                      <h4 class="new">잠실자와</h4>
                      <p>CAFE JAWA 3호점인 잠실자와점을 만나보세요.</p>
                    </div>
                  </div>
                </div>
              </li>
          </ul>

        </div>
      <hr>
    </header>
	<section id="content">            </li>
          </ul>

        </div>
      <hr>
    </header>
	<section id="content">