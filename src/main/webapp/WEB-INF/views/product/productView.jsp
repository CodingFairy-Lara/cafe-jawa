<%@page import="cafe.jawa.product.model.dto.Product"%>
<%@page import="cafe.jawa.product.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
	List<Attachment> attachmentList = (List<Attachment>) request.getAttribute("attachmentList");
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
                        <a href="javascript:void(0)" role="button" title="장바구니 열기">장바구니 열기</a>
                    </div>
                </div>
        <% if (product.getSubCategory().equals("CBR  ") || product.getSubCategory().equals("ESP  ") || product.getSubCategory().equals("FRP  ") || product.getSubCategory().equals("BLD  ") || product.getSubCategory().equals("TEA  ")) { %>
                <form 
                name="cartEnrollFrm" 
                method="POST" 
                action="<%= request.getContextPath() %>/product/cartEnroll">
                    <fieldset>
                        <legend class="product_option">옵션</legend>
                        <div class="product_view_info">
                            <div class="product_option_size">
                                <p class="tit">사이즈</p>
                                <div class="product_select_wrap2">
                                    <!-- <div class="selectTxt2" id="product_info01">Tall(톨) / 355ml (12 fl oz)</div> -->
                                    <div class="option_size_select">
                                        <img src="<%= request.getContextPath() %>/images/size/tall.png" alt="">
                                    </div>
                                    <div class="option_size_select">
                                        <img src="<%= request.getContextPath() %>/images/size/grande.png" alt="">
                                    </div>
                                    <div class="option_size_select">
                                        <img src="<%= request.getContextPath() %>/images/size/venti.png" alt="">
                                    </div>
                                </div>
                            </div>
                            <div class="product_option_cup">
                                <p class="tit">컵종류</p>
                                 <ul>
                                    <li class="option_cup_select">
                                        <div class="option_cup_select">
                                            매장컵
                                        </div>
                                    </li>
                                    
                                </ul>
                                <ul>
                                    <li class="option_cup_select">
                                        <div class="option_cup_select">
                                            일회용컵
                                        </div>
                                    </li>
                                </ul>
                            </div>
        <% } %>
                            <div class="product_factor">
                                <p>알레르기 유발요인 : <%= product.getFactor() %></p>
                            </div>

                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
        <% 	}}	%>
    </div>

</section>
<script type="text/javascript">

    $(".zoomImg").elevateZoom({
        zoomType: "inner",
        gallery : "product_thum_wrap",
        galleryActiveClass: "active",
        zoomWindowWidth: 610,
        zoomWindowHeight: 468,
        zoomWindowOffetx: 38,
        zoomWindowOffety: -1,
    });
    // function zoom(element, type, custors) {
    //     element.elevateZoom({
    //         zoomType: type,
    //         cursor: custors,
    //         zoomWindowFadeIn: 500,
    //         zoomWindowFadeOut: 750
    //     });
    // }
    // zoom($('#zoomImg'), "lens", "default");
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>
