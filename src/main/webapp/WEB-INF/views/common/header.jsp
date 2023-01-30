<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cafe.jawa.member.model.dto.Member" %>
<%@ page import="cafe.jawa.member.model.dto.MemberRole" %>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	MemberRole user = MemberRole.valueOf("U");
	MemberRole admin = MemberRole.valueOf("A");
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
  <script>
  window.addEventListener('load', () => {
    <% if(msg != null) { %>
      alert("<%= msg %>"); 
    <% } %>
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
      <% } else if(loginMember.getMemberRole() == user){ %>
      	<ul class="loginMenu">
      	  <li>
      		<a href="<%= request.getContextPath() %>/member/logout">Sign out</a>
      	  </li>
      	  <li>
      	  	<a href="<%= request.getContextPath() %>/member/memberView">My CAFE JAWA</a>
      	  </li>
      	  <li>
            <a href="javascript:viod(0)">ADMIN</a>
          </li>
      	  <li>
            <a href="<%= request.getContextPath() %>/cart/cartView">My Cart</a>
          </li>
      	 </ul>
      <% } else if(loginMember.getMemberRole() == admin) {%>
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
                        <h4>CAFE JAWA</h4>
                        <ul>
                          <li>팀원소개</li>
                          <li>CAFE JAWA 이용안내</li>
                        </ul>
                      </li>
                      <li>
                        <h4>공지사항</h4>
                        <ul>
                          <li class="notice_1"><a href="<%=request.getContextPath() %>/notice/noticeList">공지사항 게시판</a></li>
                          <li>공지사항 게시판2</li>
                          <li>공지사항 게시판3</li>
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
                        <h4>매장 찾기</h4>
                        <ul>
                          <li>지역 검색</li>
                          <li>My 매장</li>
                        </ul>
                      </li>
                      <li>
                        <h4>매장 이야기</h4>
                        <ul>
                          	<li>
								<a href="<%= request.getContextPath() %>/store/jamsil">CAFE JAWA 잠실점</a>
							</li>
							<li>
                          		<a href="<%= request.getContextPath() %>/store/incheon">CAFE JAWA 인천점</a>
							</li>
							<li>
								<a href="<%= request.getContextPath() %>/store/dongtan">CAFE JAWA 동탄점</a>
							</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="contents__texture">
                    <div class="inner">
                      <h4>매장 찾기</h4>
                      <p>보다 빠르게 매장을 찾아보세요.</p>
                      <h4 class="new">청담스타</h4>
                      <p>CAFE JAWA 1,000호점인 청담스타점을 만나보세요.</p>
                    </div>
                  </div>
                </div>
              </li>
              <li class="item">
                <div class="item__name">CUSTOMER SEVICE</div>
                <div class="item__contents">
                  <div class="contents__menu">
                    <ul class="inner">
                      <li>
                        <h4>FAQ</h4>
                        <ul>
                          <li>자주하는 질문</li>
                        </ul>
                      </li>
                      <li>
                        <h4>고객의 소리</h4>
                        <ul>
                          <li>문의하기</li>
                          <li>문의 내역</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="contents__texture">
                    <div class="inner">
                      <h4>커피원두 재활용</h4>
                      <p>CAFE JAWA 커피 원두를 재활용 해보세요.</p>
                    </div>
                  </div>
                </div>
            </li>
          </ul>

        </div>
      <hr>
    </header>
	<section id="content">