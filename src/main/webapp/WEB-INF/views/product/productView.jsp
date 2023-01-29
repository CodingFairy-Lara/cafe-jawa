<%@page import="cafe.jawa.product.model.dto.Product"%>
<%@page import="cafe.jawa.product.model.dto.Attachment"%>
<%@page import="cafe.jawa.product.model.dto.OrderedProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
	List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
    if(request.getAttribute("orderedProduct") != null) {
        OrderedProduct orderedProduct = (OrderedProduct) request.getAttribute("orderedProduct");
    }
%>

<section id=product-view-container>
    <div class="inner">
		<h1>상세 정보</h1>
        <div class="product_view_wrap1">
            <div class="product_view_pic">
        <% 
            for(Attachment attachment : attachmentList){
                if(attachment.getProductId() == product.getProductId()) {
        %>
                <div class="product_big_pic">
        <% if (product.getSubCategory().equals("CBR  ") || product.getSubCategory().equals("ESP  ") || product.getSubCategory().equals("FRP  ") || product.getSubCategory().equals("BLD  ") || product.getSubCategory().equals("TEA  ")) { %>
                    <p>
                        <a href="javascript:void(0)" tabindex="-1" aria-hidden="true" role="presentation">
                            <img class="zoomImg" src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" data-zoom-image="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="상세이미지">
                        </a>
                    </p>
                    <p class="more_btn">
                        <a href="javascript:void(0)" tabindex="-1" aria-hidden="true" role="presentation">
                            <img src="//image.istarbucks.co.kr/common/img/menu/more.png" alt="상세이미지">
                        </a>
                    </p>
                </div>
                <div class="product_thum_wrap" id="product_thum_wrap">
                    <ul class="product_thum">
                        <li>
                            <a href="javascript:void(0)" data-image="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" data-zoom-image="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" class="elevatezoom-gallery active">
                                <img src="<%= request.getContextPath() %>/images/drink/<%=attachment.getOriginalFilename()%>" alt="상세이미지 1번">
                            </a>
                        </li>
                    </ul>
        <% } else { %>
                    <p>
                        <a href="javascript:void(0)" tabindex="-1" aria-hidden="true" role="presentation">
                            <img class="zoomImg" src="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" data-zoom-image="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" alt="상세이미지">
                        </a>
                    </p>
                    <p class="more_btn">
                        <a href="javascript:void(0)" tabindex="-1" aria-hidden="true" role="presentation">
                            <img src="//image.istarbucks.co.kr/common/img/menu/more.png" alt="상세이미지">
                        </a>
                    </p>
                </div>
                <div class="product_thum_wrap" id="product_thum_wrap">
                    <ul class="product_thum">
                        <li>
                            <a href="javascript:void(0)" data-image="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" data-zoom-image="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" class="elevatezoom-gallery active">
                                <img src="<%= request.getContextPath() %>/images/food/<%=attachment.getOriginalFilename()%>" alt="상세이미지 1번">
                            </a>
                        </li>
                    </ul>
        <% } %>
                </div>
            </div>
            <div class="product_view_detail">
                <div class="myAssignZone">
                    <h4><%= product.getProductName() %><br><span><%=attachment.getRenamedFilename()%></span></h4>
                    <p class="t1"><%= product.getDescription() %></p>
                    <div class="myDrink">
                        <a href="<%=request.getContextPath() %>/cart/cartView" role="button" title="장바구니 열기">장바구니 열기</a>
                    </div>
                </div>
        <% if (product.getSubCategory().equals("CBR  ") || product.getSubCategory().equals("ESP  ") || product.getSubCategory().equals("FRP  ") || product.getSubCategory().equals("BLD  ") || product.getSubCategory().equals("TEA  ")) { %>
                <form 
                name="cartEnrollFrm" 
                method="POST" 
                action="<%= request.getContextPath() %>/product/orderEnroll">
                    <fieldset>
                        <legend class="product_option">주문 옵션</legend>
                        <div class="product_view_info">
                            <div class="product_option_size">
                                <p class="tit">사이즈</p>
                                <div class="product_select_wrap2">
                                    <ul>
                                        <li class="option_cup_select">
                                            <div id="tall_size" class="option_size_select selected" onclick="input_size('Tall')">
                                                <img src="<%= request.getContextPath() %>/images/size/tall.png" alt="">
                                                <div class="circle"></div>
                                            </div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li class="option_cup_select">
                                            <div id="grande_size" class="option_size_select" onclick="input_size('Grande')">
                                                <img src="<%= request.getContextPath() %>/images/size/grande.png" alt="">
                                                <div class="circle" style="display:none;"></div>
                                            </div>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li class="option_cup_select">
                                            <div id="venti_size" class="option_size_select" onclick="input_size('Venti')">
                                                <img src="<%= request.getContextPath() %>/images/size/venti.png" alt="">
                                                <div class="circle" style="display:none;"></div>
                                            </div>
                                        </li>
                                    </ul>
                                    <input type='hidden' id='cup_size' name="cup_size" value='tall'/>
                                </div>
                            </div>
                            <div class="product_option_cup">
                                <p class="tit">컵종류</p>
                                 <ul>
                                    <li class="option_cup_select1" onclick="input_cup('매장컵')">
                                        <div id="n_togo_cup" class="option_cup_select selected">
                                            매장컵
                                        </div>
                                    </li>
                                </ul>
                                <ul>
                                    <li class="option_cup_select2" onclick="input_cup('일회용컵')">
                                        <div id="togo_cup" class="option_cup_select unselected">
                                            일회용컵
                                        </div>
                                    </li>
                                </ul>
                                <input type='hidden' id='cup_kind' name="cup_kind" value='매장컵'/>
                            </div>
        <% } else { %>
            <form 
            name="cartEnrollFrm" 
            method="POST" 
            action="<%= request.getContextPath() %>/product/orderEnroll">
                <fieldset>
                    <div class="product_view_info">
        <% } %>
                        <div class="product_factor">
                            <p>알레르기 유발요인 : <%= product.getFactor() %></p>
                        </div>
                    </div>
                    </fieldset>
                    <input type="hidden" name="productId" id="productId" value="<%= product.getProductId() %>">
                    <input type="hidden" name="subCategory" id="subCategory" value="<%= product.getSubCategory() %>">
                    <div class="quantity_n_price_container">
                        <div class="quantity_container">
                            <div id="minus-sign" class="quantity">
                                <a href="javascript:change_qty2('m')">
                                    <img src="<%= request.getContextPath() %>/images/quantity/minus-sign.png" alt="" class="quantity_updown">
                                </a>
                            </div>
                            <div class="quantity_num">
                                <input type="text" name="quantity" id="quantity_pv" value="1" readonly="readonly">
                            </div>
                            <div id="plus-sign" class="quantity">
                                <a href="javascript:change_qty2('p')">
                                    <img src="<%= request.getContextPath() %>/images/quantity/plus-sign.png" alt="" class="quantity_updown">
                                </a>
                            </div>
                        </div>
                        <div class="price_container"  name="price_init" init="false" value="<%= product.getPrice() %>">
                            <input type="hidden" name="pv_price" id="pv_price" class="pv_price" value="<%= product.getPrice() %>">
                            <h2 id="total_amount_pv">
                                 원
                            </h2>
                        </div>
                    </div>
                    <div id="" class="cart_container">
                        <!-- <button onclick="javascript:add_cart('<%= product.getProductId() %>');"">담기</button> -->
                        <button onclick='return cartEnrollSubmit(this.form);'>장바구니 담기</button>
                        <button class="order_btn">주문하기</button>
                    </div>
                </form>
            </div>
        </div>
        <% 	}}	%>
    </div>
    <!-- <div id="layer" style="visibility: hidden">
		visibility:hidden 으로 설정하여 해당 div안의 모든것들을 가려둔다.
		<div id="popup">
			팝업창 닫기 버튼
			<a href="javascript:" onClick="javascript:imagePopup('close', '.layer01');">
                <img src="${contextPath}/resources/image/close.png" id="close" />
			</a> <br />
            <font size="12" id="contents">장바구니에 담았습니다.</font><br>
            <form   action='${contextPath}/cart/myCartList.do'  >				
                    <input  type="submit" value="장바구니 보기">
            </form>		
        </div>
    </div> -->
</section>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/productMenu.js"></script>
<script type="text/javascript">

  window.addEventListener('load', () => {
      if ($("div[name=price_init]").attr("init") == "false") {
          cart_price_init($("div[name=price_init]"));
      }

        function cart_price_init(div) {
        let price_val = Number(div.attr("value"));
        console.log('price_val :>> ', price_val);
        $("#total_amount_pv").html(price_val.format() + " 원");
        $(div).removeAttr("init");
    }
  });

    $(".zoomImg").elevateZoom({
        zoomType: "inner",
        gallery : "product_thum_wrap",
        galleryActiveClass: "active",
        zoomWindowWidth: 610,
        zoomWindowHeight: 468,
        zoomWindowOffetx: 38,
        zoomWindowOffety: -1,
    });

</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>
