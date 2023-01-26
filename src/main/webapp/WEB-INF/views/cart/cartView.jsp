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

<section id=cart-view-container>
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
    <div class="cart_content inner">
        <h2>주문 메뉴</h2>
        <div>
            <input type="checkbox" name="selectAll" id="selectAll">전체 선택
        </div>
    
    
    
    
    </div>

</section>
<script type="text/javascript">

</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>