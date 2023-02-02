<%@page import="cafe.jawa.product.model.dto.Product"%>
<%@page import="cafe.jawa.product.model.dto.Attachment"%>
<%@page import="cafe.jawa.product.model.dto.OrderedProduct"%>
<%@page import="cafe.jawa.cart.model.dto.Cart"%>
<%@page import="cafe.jawa.order.model.dto.Payment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section>
   <div id="admin_orderInfo_container">

        <!-- 주문목록 헤더 -->
        <div class="order_header admin_info" >
            <div class="inner">
                <h1 class="order_header">주문 정보 확인</h1>
            </div>
        </div>
        <!-- 주문목록 헤더 끝 -->
        <div class="order_content inner">
            <!-- 픽업지점 정보 헤더 -->
            <div class="order_list_header store_info">
                <h2>상품 픽업 지점 정보</h2>    
                <div class="order_list_header_container">
                </div>
            </div>
         </div>
         
         <div class="admin_orderInfo_box inner">

            <div id="admin_orderInfo_tbl_div" class="admin_info">
                <table border="1">
                    <tr>
                        <th> 주문번호 </th>
                        <th> 지점명 </th>
                        <th> 주문자 ID </th>
                        <th> 총 결제금액 </th>
                        <th> 주문 상태 </th>
                        <th> </th>
                    </tr>
            <%
					if (loginMember.getMemberRole() == MemberRole.valueOf("A"))	   {
	   				for (int i = 0; i < orderListAll.size(); i++) {
   					Order order = orderListAll.get(i);
   					
                    switch (order.getStoreId()) {
                        case "003":  user_store = "CAFE JAWA 동탄점";
                                 break;
                        case "002":  user_store = "CAFE JAWA 인천점";
                                 break;
                        case "001":  user_store = "CAFE JAWA 잠실점";
                                 break;
                        }
                    switch (order.getStatus()) {
                        case 5:  user_orderStats = "상품 준비완료 / 수령대기";
                                    break;
                        case 4:  user_orderStats = "상품 준비 임박";
                                    break;
                        case 3:  user_orderStats = "주문수락됨 / 상품 준비중";
                                    break;
                        case 2:  user_orderStats = "주문확인 요청";
                                    break;
                        case 1:  user_orderStats = "결제 오류";
                                    break;
                        case 0:  user_orderStats = "수령 완료";
                                    break;
                        }

            %>
                    <tr>
                        <input type="hidden" name="update_userStats_td_<%= order.getOrderNum() %>" value="<%= user_orderStats %>">
                        <td><%= order.getOrderNum() %></td>
                        <td><%= user_store %></td>
                        <td><%= order.getMemberId() %></td>
                        <td><%= order.getTotalPrice() %> 원</td>
                        <td id='acceptOrder_result_<%= order.getOrderNum() %>' class="td_userStatus"><%= user_orderStats %></td>
                        <td><button onclick='javascript:updateUserStatus("<%= order.getOrderNum() %>", "<%= order.getStatus() %>");' id="accept_btn_<%= order.getOrderNum() %>" class="abutton admin_info">주문 수락하기</button></td>
                    </tr>
            <% }} else {
	   				for (int i = 0; i < userOrderList.size(); i++) {
   					Order order = userOrderList.get(i);
   					
                    switch (order.getStoreId()) {
                        case "003":  user_store = "CAFE JAWA 동탄점";
                                 break;
                        case "002":  user_store = "CAFE JAWA 인천점";
                                 break;
                        case "001":  user_store = "CAFE JAWA 잠실점";
                                 break;
                        }
                    switch (order.getStatus()) {
                        case 5:  user_orderStats = "상품 준비완료 / 수령대기";
                                    break;
                        case 4:  user_orderStats = "상품 준비 임박";
                                    break;
                        case 3:  user_orderStats = "주문수락됨 / 상품 준비중";
                                    break;
                        case 2:  user_orderStats = "주문확인 요청";
                                    break;
                        case 1:  user_orderStats = "결제 오류";
                                    break;
                        case 0:  user_orderStats = "수령 완료";
                                    break;
                        }

            %>
                    <tr>
                        <input type="hidden" name="update_userStats_td_<%= order.getOrderNum() %>" value="<%= user_orderStats %>">
                        <td><%= order.getOrderNum() %></td>
                        <td><%= user_store %></td>
                        <td><%= order.getMemberId() %></td>
                        <td><%= order.getTotalPrice() %> 원</td>
                        <td id='acceptOrder_result_<%= order.getOrderNum() %>' class="td_userStatus"><%= user_orderStats %></td>
                    </tr>
            <% }} %>
            
                </table>

                <div class="admin_info">


                </div>

            </div>

         </div>


    </div>

</section>
    
<script>

updateTdColor();

function updateTdColor () {
    $("td:contains('수령완료')").css({color:"rgb(194, 180, 182)"});
    $("td:contains('결제 오류')").css({color:"red"});
    $("td:contains('주문확인 요청')").css({color:"orange"});
    $("td:contains('주문수락됨 / 상품 준비중')").css({color:"skyblue"});
    $("td:contains('상품 준비 임박')").css({color:"pink"});
    $("td:contains('상품 준비완료 / 수령대기')").css({color:"green"});
};



</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>