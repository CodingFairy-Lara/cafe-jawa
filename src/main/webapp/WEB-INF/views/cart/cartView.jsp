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
%>

<section id=cart-view-container>
    <!-- 장바구니 헤더 -->
    <div class="cart_header">
        <div class="inner">
            <h1 class="cart_header">장바구니</h1>
            <select name="store_id" id="select_store" required >
                <option value="" disabled selected>  주문할 매장을 선택해주세요  <img src="<%= request.getContextPath() %>/images/errow_down.png" alt=""></option>
                <option value="001">CAFE JAWA 잠실점</option>
                <option value="002">CAFE JAWA 인천점</option>
                <option value="003">CAFE JAWA 동탄점</option>
            </select>
        </div>
    </div>
    <!-- 장바구니 헤더 끝 -->
    <div class="cart_content inner">
        <!-- 장바구니 리스트 헤더 -->
        <div class="cart_list_header">
            <h2>주문 메뉴</h2>
            <div class="cart_list_header_container">
                <ul class="cart_checkbox">
                    <li><input type="checkbox" name="cartList_selectAll" id="cartList_selectAll" checked ><label for="cartList_selectAll">전체 선택</label></li>
                </ul>
                <div class="cart_select_container">
                    <a href="javascript:void(0)" class="abutton" onclick="javascript:basket.delCheckedItem();">선택 상품 삭제</a>
                    <a href="javascript:void(0)" class="abutton" onclick="javascript:cart_deleteAll('<%= loginMember.getMemberId() %>');">장바구니 비우기</a>
                </div>
            </div>
        </div>
        <!-- 장바구니 리스트 헤더 끝 -->
        <!-- 장바구니 리스트 시작 -->
        <div id="cart_list_container">
            <form name="orderform" id="orderform" method="post" class="orderform" action="/Page" onsubmit="return false;">
    
                <input type="hidden" name="cmd" value="order">
                <div class="basketdiv" id="basket">
            <!-- 장바구니 비어있을때 -->
            <% if (cartList.size() == 0) { %>
                <div class="row data" id="empty_cart">
                    <span class="empty_cart"><%= loginMember.getMemberName() %>님의 장바구니가 비어있습니다. 🛒</span>
                </div>
            <% }else {
            for(Cart cart : cartList){
                for(OrderedProduct orderedProduct : orderedProductList){
                    if(cart.getOrderedProductId() == orderedProduct.getOrderedProductId()) {
                        if (orderedProduct.getProductId() < 1000) { 
            %>
                    <!-- drink 메뉴 -->
                    <div class="row data">
                        <div class="subdiv">
                            <div class="check"><input type="checkbox" name="checked_cart_product" checked  value="<%= cart.getCartId() %>"  onClick="">&nbsp;</div>
            <% for(Attachment attachment : attachmentList) {
                if(attachment.getProductId() == orderedProduct.getProductId()) { %>
                    <% for(Product product : productList) {
                        if(product.getProductId() == orderedProduct.getProductId()) { %>
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
                            <div class="basketprice" id="amount_init_<%= cart.getCartId() %>" name="price_init" init="false" value="<%= product.getPrice() %>" cartId="<%= cart.getCartId() %>"><input type="hidden" name="p_price_<%= cart.getCartId() %>" id="p_price_<%= cart.getCartId() %>" class="p_price" value="<%= product.getPrice() %>"><%= product.getPrice() %></div>
                            <div class="num">
                                <div class="updown">
                                    <span onclick="javascript:change_qty_cart('m', '<%= cart.getCartId() %>'); modify_cart_qty('<%= cart.getCartId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= cart.getCartId() %>').val());"">
                                        <img src="<%= request.getContextPath() %>/images/quantity/minus-sign.png" alt="-" class="quantity_cart">
                                    </span>
                                    <input type="text" name="quantity" id="quantity_<%= cart.getCartId() %>" class="cart_quantity" value="<%= cart.getQuantity() %>" readonly="readonly">
                                    <span onclick="javascript:change_qty_cart('p', '<%= cart.getCartId() %>'); modify_cart_qty('<%= cart.getCartId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= cart.getCartId() %>').val());"">
                                        <img src="<%= request.getContextPath() %>/images/quantity/plus-sign.png" alt="+" class="quantity_cart">
                                    </span>
                                </div>
                            </div>
                            <div id="total_amount_<%= cart.getCartId() %>" name="tot_price_init" init="false" class="sum" cartId="<%= cart.getCartId() %>" value="<%= product.getPrice() * cart.getQuantity() %>"><%= product.getPrice() * cart.getQuantity() %></div>
                        </div>
                        <div class="subdiv">
                            <div class="basketcmd"><a href="javascript:void(0)" class="abutton" onclick="javascript:cart_deleteOne('<%= cart.getCartId() %>', '<%= orderedProduct.getOrderedProductId() %>');">삭제</a></div>
                        </div>
                    </div>
            <%}} }} } else { %>
                        <!-- food 메뉴 -->
                        <div class="row data">
                            <div class="subdiv">
                                <div class="check"><input type="checkbox" name="checked_cart_product" checked  value="<%= cart.getCartId() %>"  onClick="">&nbsp;</div>
                <% for(Attachment attachment : attachmentList) {
                    if(attachment.getProductId() == orderedProduct.getProductId()) { %>
                        <% for(Product product : productList) {
                            if(product.getProductId() == orderedProduct.getProductId()) { %>
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
                                <div class="basketprice" id="amount_init_<%= cart.getCartId() %>" name="price_init" init="false" value="<%= product.getPrice() %>" cartId="<%= cart.getCartId() %>"><input type="hidden" name="p_price_<%= cart.getCartId() %>" id="p_price_<%= cart.getCartId() %>" class="p_price" value="<%= product.getPrice() %>"><%= product.getPrice() %></div>
                                <div class="num">
                                    <div class="updown">
                                        <span onclick="javascript:change_qty_cart('m', '<%= cart.getCartId() %>'); modify_cart_qty('<%= cart.getCartId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= cart.getCartId() %>').val());"">
                                            <img src="<%= request.getContextPath() %>/images/quantity/minus-sign.png" alt="-" class="quantity_cart">
                                        </span>
                                        <input type="text" name="quantity" id="quantity_<%= cart.getCartId() %>" class="cart_quantity" value="<%= cart.getQuantity() %>" readonly="readonly">
                                        <span onclick="javascript:change_qty_cart('p', '<%= cart.getCartId() %>'); modify_cart_qty('<%= cart.getCartId() %>', '<%= orderedProduct.getOrderedProductId() %>', $('#quantity_'+'<%= cart.getCartId() %>').val());"">
                                            <img src="<%= request.getContextPath() %>/images/quantity/plus-sign.png" alt="+" class="quantity_cart">
                                        </span>
                                    </div>
                                </div>
                                <div id="total_amount_<%= cart.getCartId() %>" name="tot_price_init" init="false" class="sum" cartId="<%= cart.getCartId() %>" value="<%= product.getPrice() * cart.getQuantity() %>"><%= product.getPrice() * cart.getQuantity() %></div>
                            </div>
                            <div class="subdiv">
                                <div class="basketcmd"><a href="javascript:void(0)" class="abutton" onclick="javascript:cart_deleteOne('<%= cart.getCartId() %>', '<%= orderedProduct.getOrderedProductId() %>');">삭제</a></div>
                            </div>
                        </div>
                <%}} }} }} }} }%>
            
                </div>
                <div class="cart_select_container">
                    <a href="javascript:void(0)" class="abutton" onclick="javascript:basket.delCheckedItem();">선택 상품 삭제</a>
                    <a href="javascript:void(0)" class="abutton" onclick="javascript:cart_deleteAll('<%= loginMember.getMemberId() %>');">장바구니 비우기</a>
                </div>
                
                <div class="cart_footer">
                    <div id="goorder" class="">
                        <div class="clear"></div>
                        <div class="cmd">
                            <a href="javascript:void(0)" class="abutton" id="go_to_check" onclick="">주문하기</a>
                        </div>
                    </div>
                    <div class="total_quantity_n_price">
                        <div class="bigtext right-align sumcount" id="sum_p_num">상품갯수: 4개</div>
                        <div class="bigtext right-align box blue summoney" id="sum_p_price">합계금액: 74,200원</div>
                    </div>
                </div>
            </form>
            
        </div>
        <!-- 장바구니 리스트 끝 -->
   
    
    
    
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

  });


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>