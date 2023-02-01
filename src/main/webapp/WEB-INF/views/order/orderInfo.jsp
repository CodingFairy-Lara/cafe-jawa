<%@page import="cafe.jawa.product.model.dto.Product"%>
<%@page import="cafe.jawa.product.model.dto.Attachment"%>
<%@page import="cafe.jawa.product.model.dto.OrderedProduct"%>
<%@page import="cafe.jawa.cart.model.dto.Cart"%>
<%@page import="cafe.jawa.order.model.dto.Payment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
	List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
	List<OrderedProduct> orderedProductList = (List<OrderedProduct>) request.getAttribute("orderedProductList");
	List<Cart> cartList = (List<Cart>) request.getAttribute("cartList");
	List<Order> orduserOrderListerList = (List<Order>) request.getAttribute("userOrderList");
	List<Payment> paymentList = (List<Payment>) request.getAttribute("paymentList");
	String storeId = (String) request.getAttribute("storeId");
%>

<section id="orderInfo-view-container">
    <div id="orderInfo_container">

        <!-- 주문목록 헤더 -->
        <div class="order_header">
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
            <!-- 픽업지점 정보 헤더 끝 -->
            <!-- 픽업지점 정보 시작 -->
            <div id="orderInfo_container_store_info_div">
            <% if (storeId.equals("001")) { %>
                <div class="orderdiv" id="basket">
                    <div class="row data">
                        <div class="subdiv store_info">&nbsp;</div>
                        <div class="subdiv store_info">CAFE JAWA 잠실점</div>
                        <div class="subdiv store_info">&nbsp;</div>
                    </div>
                </div>
            <% } else if (storeId.equals("002")) { %>
                <div class="orderdiv" id="basket">
                    <div class="row data">
                        <div class="subdiv store_info">&nbsp;</div>
                        <div class="subdiv store_info">CAFE JAWA 인천점</div>
                        <div class="subdiv store_info">&nbsp;</div>
                    </div>
                </div>
            <% } else if (storeId.equals("003")) { %>
                <div class="orderdiv" id="basket">
                    <div class="row data">
                        <div class="subdiv store_info">&nbsp;</div>
                        <div class="subdiv store_info">CAFE JAWA 동탄점</div>
                        <div class="subdiv store_info">&nbsp;</div>
                    </div>
                </div>
            <% } %>
            </div>
            <!-- 수령지점 정보 끝 -->
            <!-- 주문목록 헤더 -->
            <div class="order_list_header">
                <h2>주문 목록</h2>
                <div class="order_list_header_container">
                </div>
            </div>
            <!-- 주문목록 헤더 끝 -->
            <!-- 주문목록 시작 -->
            <div id="orderInfo_container">
                <form 
                name="goPaymentForm" 
                method="POST" 
                action="<%= request.getContextPath() %>/order/payment">
        
                    <input type="hidden" name="cmd" value="order">
                    <div class="orderdiv" id="basket">
                <% for(Order order : orderList) {
                for(OrderedProduct orderedProduct : orderedProductList){
                    if (orderedProduct.getOrderId() == order.getOrderId()){
                        if (orderedProduct.getProductId() < 1000) { 
                %>
                        <!-- drink 메뉴 -->
                        <div class="row data">
                            <div class="subdiv">
                <% for(Attachment attachment : attachmentList) {
                    if(attachment.getProductId() == orderedProduct.getProductId()) { %>
                        <% for(Product product : productList) {
                            if(product.getProductId() == orderedProduct.getProductId()) { %>
                                <div class="check cart_info_div" style="visibility: hidden;">
                                    <input type="checkbox" style="display: hidden;" checked id="checkbox_cartList_<%= orderedProduct.getProductId() %>" name="checked_cart_product" checked value="<%= orderedProduct.getOrderedProductId() %>" onClick="javascript:calcTotalPrice();" cart_id="<%= orderedProduct.getProductId() %>" op_id="<%= orderedProduct.getOrderedProductId() %>">&nbsp;
                                    <input type="hidden" name="get_cart_id" class="individual_cart_id_input" value="<%= orderedProduct.getProductId() %>">
                                    <input type="hidden" name="get_op_id" class="individual_op_id_input" value="<%= orderedProduct.getOrderedProductId() %>">
                                    <input type="hidden" name="get_quantity" class="individual_quantity_input get_quantity_<%= orderedProduct.getProductId() %>" value="<%= orderedProduct.getQuantity() %>">
                                    <input type="hidden" name="get_totalPrice" class="individual_tot_price_input get_totalPrice_<%= orderedProduct.getProductId() %>" value="<%= product.getPrice() * orderedProduct.getQuantity() %>">
                                </div>
                                <div class="img">
                                    <img src="<%= request.getContextPath() %>/images/drink/<%= attachment.getOriginalFilename() %>" width="100" onClick="location.href='<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>'">
                                </div>
                                <div class="pname" onClick="location.href='<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>'">
                                    <span><%= product.getProductName() %></span>
                                </div>
                                <div class="eng_name" onClick="location.href='<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>'">
                                    <span><%= attachment.getRenamedFilename() %></span>
                                </div>
                <%      if(attachment.getRenamedFilename().contains("Ice") || product.getSubCategory().equals("CBR  ") ||product.getSubCategory().equals("FRP  ") || product.getSubCategory().equals("BLD  ")) { %>
                                <div class="cup_info"><span>ICED | <%= orderedProduct.getCupSize() %> | <%= orderedProduct.getCup() %></span></div>
                <% } else { %>
                                <div class="cup_info"><span>HOT | <%= orderedProduct.getCupSize() %> | <%= orderedProduct.getCup() %></span></div>
                <% } %>
                            </div>
                            <div class="subdiv">
                                <div class="basketprice" id="amount_init_<%= orderedProduct.getProductId() %>" name="price_init" init="false" value="<%= product.getPrice() %>" cartId="<%= orderedProduct.getProductId() %>"><input type="hidden" name="p_price_<%= orderedProduct.getProductId() %>" id="p_price_<%= orderedProduct.getProductId() %>" class="p_price" value="<%= product.getPrice() %>"><%= product.getPrice() %></div>
                                <div class="num">
                                    <div class="updown">
                                        <span style="display:none;" onclick="javascript:change_qty_cart('m', '<%= orderedProduct.getProductId() %>'); modify_cart_qty('<%= orderedProduct.getProductId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= orderedProduct.getProductId() %>').val()); calcTotalPrice();">
                                            <img src="<%= request.getContextPath() %>/images/quantity/minus-sign.png" alt="-" class="quantity_cart">
                                        </span>
                                        <input type="text" name="quantity" id="quantity_<%= orderedProduct.getProductId() %>" class="cart_quantity" value="<%= orderedProduct.getQuantity() %>" readonly="readonly">
                                        <span style="display:none;" onclick="javascript:change_qty_cart('p', '<%= orderedProduct.getProductId() %>'); modify_cart_qty('<%= orderedProduct.getProductId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= orderedProduct.getProductId() %>').val()); calcTotalPrice();">
                                            <img src="<%= request.getContextPath() %>/images/quantity/plus-sign.png" alt="+" class="quantity_cart">
                                        </span>
                                    </div>
                                </div>
                                <div id="total_amount_<%= orderedProduct.getProductId() %>" name="tot_price_init" init="false" class="sum" cartId="<%= orderedProduct.getProductId() %>" value="<%= product.getPrice() * orderedProduct.getQuantity() %>"><%= product.getPrice() * orderedProduct.getQuantity() %></div>
                            </div>
                            <div class="subdiv">
                                <div class="basketcmd"><a style="display:none;" href="javascript:void(0)" class="abutton" onclick="javascript:cart_deleteOne('<%= orderedProduct.getProductId() %>', '<%= orderedProduct.getOrderedProductId() %>');">삭제</a></div>
                            </div>
                        </div>
                <%}} }} } else { %>
                            <!-- food 메뉴 -->
                            <div class="row data">
                                <div class="subdiv">
                <% for(Attachment attachment : attachmentList) {
                    if(attachment.getProductId() == orderedProduct.getProductId()) { %>
                        <% for(Product product : productList) {
                            if(product.getProductId() == orderedProduct.getProductId()) { %>
                                <div class="check cart_info_div" style="visibility: hidden;">
                                    <input type="checkbox" checked id="checkbox_cartList_<%= orderedProduct.getProductId() %>" name="checked_cart_product" checked value="<%= orderedProduct.getOrderedProductId() %>" onClick="javascript:calcTotalPrice();" cart_id="<%= orderedProduct.getProductId() %>" op_id="<%= orderedProduct.getOrderedProductId() %>">&nbsp;
                                        <input type="hidden" name="get_cart_id" class="individual_cart_id_input" value="<%= orderedProduct.getProductId() %>">
                                        <input type="hidden" name="get_op_id" class="individual_op_id_input" value="<%= orderedProduct.getOrderedProductId() %>">
                                        <input type="hidden" name="get_quantity" class="individual_quantity_input get_quantity_<%= orderedProduct.getProductId() %>" value="<%= orderedProduct.getQuantity() %>">
                                        <input type="hidden" name="get_totalPrice" class="individual_tot_price_input get_totalPrice_<%= orderedProduct.getProductId() %>" value="<%= product.getPrice() * orderedProduct.getQuantity() %>">
                                </div>
                                    <div class="img">
                                        <img src="<%= request.getContextPath() %>/images/food/<%= attachment.getOriginalFilename() %>" width="100" onClick="location.href='<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>'">
                                    </div>
                                    <div class="pname" onClick="location.href='<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>'" style="margin-top: 25px;">
                                        <span><%= product.getProductName() %></span>
                                    </div>
                                    <div class="eng_name" onClick="location.href='<%= request.getContextPath() %>/product/view?productId=<%= product.getProductId()%>'">
                                        <span><%= attachment.getRenamedFilename() %></span>
                                    </div>
                                </div>
                                <div class="subdiv">
                                    <div class="basketprice" id="amount_init_<%= orderedProduct.getProductId() %>" name="price_init" init="false" value="<%= product.getPrice() %>" cartId="<%= orderedProduct.getProductId() %>"><input type="hidden" name="p_price_<%= orderedProduct.getProductId() %>" id="p_price_<%= orderedProduct.getProductId() %>" class="p_price" value="<%= product.getPrice() %>"><%= product.getPrice() %></div>
                                    <div class="num">
                                        <div class="updown">
                                            <span style="display:none;" onclick="javascript:change_qty_cart('m', '<%= orderedProduct.getProductId() %>'); modify_cart_qty('<%= orderedProduct.getProductId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= orderedProduct.getProductId() %>').val()); calcTotalPrice();">
                                                <img src="<%= request.getContextPath() %>/images/quantity/minus-sign.png" alt="-" class="quantity_cart">
                                            </span>
                                            <input type="text" name="quantity" id="quantity_<%= orderedProduct.getProductId() %>" class="cart_quantity" value="<%= orderedProduct.getQuantity() %>" readonly="readonly">
                                            <span style="display:none;" onclick="javascript:change_qty_cart('p', '<%= orderedProduct.getProductId() %>'); modify_cart_qty('<%= orderedProduct.getProductId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= orderedProduct.getProductId() %>').val()); calcTotalPrice();">
                                                <img src="<%= request.getContextPath() %>/images/quantity/plus-sign.png" alt="+" class="quantity_cart">
                                            </span>
                                        </div>
                                    </div>
                                    <div id="total_amount_<%= orderedProduct.getProductId() %>" name="tot_price_init" init="false" class="sum" cartId="<%= orderedProduct.getProductId() %>" value="<%= product.getPrice() * orderedProduct.getQuantity() %>"><%= product.getPrice() * orderedProduct.getQuantity() %></div>
                                </div>
                                <div class="subdiv">
                                    <div class="basketcmd"><a style="display:none;" href="javascript:void(0)" class="abutton" onclick="javascript:cart_deleteOne('<%= orderedProduct.getProductId() %>', '<%= orderedProduct.getOrderedProductId() %>');">삭제</a></div>
                                </div>
                            </div>
                    <%}} }} }} }}%>
                
                    </div>
                    <div class="order_info_div" style="display:none;">
                        <input type="hidden" name="store_id" value="<%= storeId %>">
                        <input type="hidden" name="final_totPrice" id="final_totPrice_orderInfo" value="">
                        <!-- <input type="hidden" name="store_id" value="<%= storeId %>"> -->
                    </div>
                    
                </form>
                
            </div>
            <!-- 주문목록 끝 -->
    </div>
   
    
    
    
    </div>

</section>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/productOrder.js"></script>
<script type="text/javascript">
    
    window.addEventListener('load', () => {
		let init_length = $("div[name=price_init]").length;
		let init_tot_length = $("div[name=tot_price_init]").length;
		//배열 생성
		let init_arr = new Array(init_length);
		let init_tot_arr = new Array(init_tot_length);
		//배열에 값 주입
		for(let i=0; i<init_length; i++){                          
			init_arr[i] = $("div[name=price_init]").eq(i).attr("cartId");
	    }
		for(let i=0; i<init_tot_length; i++){                          
			init_tot_arr[i] = $("div[name=tot_price_init]").eq(i).attr("cartId");
	    }

        for (let i = 0; i < init_length; i++) {
            cart_price_init(init_arr[i]);
        }

        for (let i = 0; i < init_tot_length; i++) {
            cart_tot_price_init(init_tot_arr[i]);
        }

    function cart_price_init(cartId) {
        let price_val_init = 0;
        Number(cartId);
        console.log('cartId :>> ', cartId);
        if ($("#amount_init_"+cartId).attr("init") == "false") {
            price_val_init = Number($("#amount_init_"+cartId).text());
            console.log('price_val :>> ', price_val_init);
            $("#amount_init_"+cartId).html(price_val_init.format() + " 원");
            $("#amount_init_"+cartId).removeAttr("init");
        }
    }
    
    function cart_tot_price_init(cartId) {
        let price_val_init = 0;
        Number(cartId);
        console.log('cartId :>> ', cartId);
        price_val_init = Number($("#total_amount_"+cartId).text());
        console.log('price_val :>> ', price_val_init);
        $("#total_amount_"+cartId).html(price_val_init.format() + " 원");
    }
    
    calcTotalPrice_order();   
    getFinalPrice();

});


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>