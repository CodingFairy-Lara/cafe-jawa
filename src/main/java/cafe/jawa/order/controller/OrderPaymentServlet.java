package cafe.jawa.order.controller;

import java.io.IOException;
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
import cafe.jawa.order.model.service.OrderService;
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class OrderPaymentServlet
 */
@WebServlet("/order/payment")
public class OrderPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartservice = new CartService();
	private ProductService productService = new ProductService();
	private OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		// 수령지점 정보
		String storeId = request.getParameter("storeId");
		System.out.println(storeId);

		// 총 결제금액
		int totalPrice = Integer.parseInt(request.getParameter("final_totPrice"));
		System.out.println(totalPrice);
		
		// 선택상품 op_id 목록 배열로 가져옴
		String [] opIdList_ = request.getParameterValues("opIdList");		
		for(String val : opIdList_) {
			System.out.println(val+ " ");
		}
		List<String> opIdList = Arrays.asList(opIdList_);
		
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
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("opIdList", opIdList);
		request.setAttribute("productList", productList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("orderedProductList", orderedProductList);
		request.setAttribute("cartList", cartList);
		request.getRequestDispatcher("/WEB-INF/views/order/orderPayment.jsp").forward(request, response);
		
	}

}
