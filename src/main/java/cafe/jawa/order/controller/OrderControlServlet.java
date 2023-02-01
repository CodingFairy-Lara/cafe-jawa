package cafe.jawa.order.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import cafe.jawa.order.model.dto.Payment;
import cafe.jawa.order.model.service.OrderService;
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class OrderControllServlet
 */
@WebServlet("/order/control")
public class OrderControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartservice = new CartService();
	private ProductService productService = new ProductService();
	private OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		String payment_totPrice = request.getParameter("payment_totPrice");
		String payment_type = request.getParameter("payment_type");
		String storeId = request.getParameter("storeId");
		int orderNum = orderService.selectLastOrderNo();
		
		Payment payment = new Payment();
		payment.setPaymentMethod(payment_type);
		payment.setOrderNum(orderNum);
		
		int result = orderService.enrollPayment(payment);
		
		// db에서 상품 목록 조회
		List<Product> productList = productService.selectProductList();
		// 상품 이미지 AttachmentList 가져오기
		List<Attachment> attachmentList = productService.selectAttachmentList();
		// 주문상품 orderedProductList 가져오기
		List<OrderedProduct> orderedProductList = productService.selectOrderedProductList();
		// 사용자 cartList 가져오기
		List<Cart> cartList = cartservice.selectCartList(memberId);
		
		// 주문정보 list 가져오기
		List<Order> userOrderList = orderService.getOrderList(memberId);
		System.out.println("orderList = " + userOrderList);
		
		// 결제정보 list 가져오기
		List<Payment> paymentList = new ArrayList<>();
		for (Order order : userOrderList) {
			int orderNum_ = order.getOrderNum();
			Payment payment_ = orderService.getPaymentList(orderNum_);
			paymentList.add(payment_);
		}
		System.out.println("paymentList = " + paymentList);

		if (userOrderList != null) {
			session.setAttribute("userOrderList", userOrderList);
		}
		
		// 3. view단 위임.
		request.setAttribute("userOrderList", userOrderList);
		request.setAttribute("paymentList", paymentList);
		request.setAttribute("storeId", storeId);
		request.setAttribute("productList", productList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("orderedProductList", orderedProductList);
		request.setAttribute("cartList", cartList);
		
		response.sendRedirect(request.getContextPath() + "/");
		
//		request.getRequestDispatcher("/WEB-INF/views/order/orderInfo.jsp").forward(request, response);
	}

}
