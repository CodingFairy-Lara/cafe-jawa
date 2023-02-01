package cafe.jawa.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.cart.model.service.CartService;
import cafe.jawa.member.model.dto.Member;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.service.OrderService;
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class OrderEnrollServlet
 */
@WebServlet("/order/view")
public class OrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartservice = new CartService();
	private ProductService productService = new ProductService();
	private OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 1. 사용자 id 가져오기
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		System.out.println(memberId);
		
		// 사용자 주문정보 가져오기
		List<Order> userOrderList = orderService.getOrderList(memberId);
		System.out.println("orderList = " + userOrderList);
		
		for (Order order : userOrderList) {
			if (order.getStatus() > 0) {
				session.setAttribute("msg", "이미 진행중인 주문이 존재합니다! 주문하신 상품 수령 후 이용해주세요.");
				response.sendRedirect(request.getContextPath() + "/cart/view");
			 	return;
			}
		}
		
		// 수령지점 정보
		String storeId = request.getParameter("store_id");
		System.out.println(storeId);

		// 선택상품 op_id 목록 배열로 가져옴
		String [] selectedOpList = request.getParameterValues("checked_cart_product");		
		for(String val : selectedOpList) {
			System.out.println(val+ " ");
		}
		List<String> selected_opList = Arrays.asList(selectedOpList);
		
		// 2. 업무로직
		// db에서 상품 목록 조회
		List<Product> productList = productService.selectProductList();
		
		// 상품 이미지 AttachmentList 가져오기
		List<Attachment> attachmentList = productService.selectAttachmentList();
		
		// 주문상품 orderedProductList 가져오기
		List<OrderedProduct> orderedProductList = productService.selectOrderedProductList();
		
		// 사용자 cartList 가져오기
		List<Cart> cartList = cartservice.selectCartList(memberId);
		
		
		// 3. view단 위임.
		request.setAttribute("storeId", storeId);
		request.setAttribute("selected_opList", selected_opList);
		request.setAttribute("productList", productList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("orderedProductList", orderedProductList);
		request.setAttribute("cartList", cartList);
		request.getRequestDispatcher("/WEB-INF/views/order/orderView.jsp").forward(request, response);
	}
	
	


}
