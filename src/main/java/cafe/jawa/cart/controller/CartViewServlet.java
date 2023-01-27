package cafe.jawa.cart.controller;

import java.io.IOException;
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
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class CartViewServlet
 */
@WebServlet("/cart/cartView")
public class CartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartservice = new CartService();
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 1. 사용자 id 가져오기
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		System.out.println("memberId = " + memberId);
		
		// 2. 업무로직
		// db에서 상품 목록 조회
		List<Product> productList = productService.selectProductList();
		System.out.println("productList="+productList);
		
		// 상품 이미지 AttachmentList 가져오기
		List<Attachment> attachmentList = productService.selectAttachmentList();
		System.out.println("attachmentList="+attachmentList);
		
		// 주문상품 orderedProductList 가져오기
		List<OrderedProduct> orderedProductList = productService.selectOrderedProductList();
		System.out.println("orderedProductList="+orderedProductList);
		
		// 사용자 cartList 가져오기
		List<Cart> cartList = cartservice.selectCartList(memberId);
		System.out.println("cartList="+cartList);
		
		
		// 3. view단 위임.
		request.setAttribute("productList", productList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("orderedProductList", orderedProductList);
		request.setAttribute("cartList", cartList);
		request.getRequestDispatcher("/WEB-INF/views/cart/cartView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
