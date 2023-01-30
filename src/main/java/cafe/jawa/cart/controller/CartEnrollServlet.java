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
 * Servlet implementation class CartEnrollServlet
 */
@WebServlet("/cart/cartEnroll")
public class CartEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private CartService cartservice = new CartService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		OrderedProduct orderedProduct = null;
		int productId = 0;

		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String subCategory = request.getParameter("subCategory");

			if (subCategory.equals("CBR  ") || subCategory.equals("ESP  ") || subCategory.equals("FRP  ") || subCategory.equals("BLD  ") || subCategory.equals("TEA  ")) {
				String size = request.getParameter("cup_size");
				String cup = request.getParameter("cup_kind");
				orderedProduct = cartservice.checkDupCart(productId, memberId, size, cup);
				if (orderedProduct != null) {
				 	session.setAttribute("msg", "상품이 장바구니에 이미 존재합니다.");
					response.sendRedirect(request.getContextPath() + "/product/view?productId=" +productId);
				 	return;
				}
				orderedProduct = new OrderedProduct(productId, quantity, size, cup, memberId);			
			} else {
				orderedProduct = cartservice.checkDupCart(productId, memberId);
				if (orderedProduct != null) {
				 	session.setAttribute("msg", "상품이 장바구니에 이미 존재합니다.");
					response.sendRedirect(request.getContextPath() + "/product/view?productId=" +productId);
				 	return;
				}
				orderedProduct = new OrderedProduct(productId, quantity, memberId);			
			}

			
			// if(checkDup > 0) {
				int result = productService.orderedProductEnroll(orderedProduct);
				
				if(result > 0) {
					orderedProduct = productService.selectOrderedProduct(productId, quantity, memberId);
					System.out.println("cart orderedProduct2 = " + orderedProduct);
					Cart cart = new Cart();
					cart.setMemberId(orderedProduct.getMemberId());
					cart.setOrderedProductId(orderedProduct.getOrderedProductId());
					cart.setQuantity(orderedProduct.getQuantity());
					System.out.println("cart cart = " + cart);
					int result2 = cartservice.cartEnroll(cart);
					if (result2 > 0) 
						session.setAttribute("msg", "장바구니에 상품을 담았습니다.");
				}
			// } else {
			// 	session.setAttribute("msg", "상품이 장바구니에 이미 존재합니다.");
			// 	return;
			// }
			
		} catch(Exception e) {
			session.setAttribute("msg", "장바구니 담기에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/product/view?productId=" +productId);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("UTF-8");
		OrderedProduct orderedProduct = null;
		int productId = 0;

		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String subCategory = request.getParameter("subCategory");

			if (subCategory.equals("CBR  ") || subCategory.equals("ESP  ") || subCategory.equals("FRP  ") || subCategory.equals("BLD  ") || subCategory.equals("TEA  ")) {
				String size = request.getParameter("cup_size");
				String cup = request.getParameter("cup_kind");
				orderedProduct = cartservice.checkDupCart(productId, memberId, size, cup);
				if (orderedProduct != null) {
				 	session.setAttribute("msg", "상품이 장바구니에 이미 존재합니다.");
					response.sendRedirect(request.getContextPath() + "/product/menu");
				 	return;
				}
				orderedProduct = new OrderedProduct(productId, quantity, size, cup, memberId);			
			} else {
				orderedProduct = cartservice.checkDupCart(productId, memberId);
				if (orderedProduct != null) {
				 	session.setAttribute("msg", "상품이 장바구니에 이미 존재합니다.");
					response.sendRedirect(request.getContextPath() + "/product/menu");
				 	return;
				}
				orderedProduct = new OrderedProduct(productId, quantity, memberId);			
			}

			
			// if(checkDup > 0) {
				int result = productService.orderedProductEnroll(orderedProduct);
				
				if(result > 0) {
					orderedProduct = productService.selectOrderedProduct(productId, quantity, memberId);
					System.out.println("cart orderedProduct2 = " + orderedProduct);
					Cart cart = new Cart();
					cart.setMemberId(orderedProduct.getMemberId());
					cart.setOrderedProductId(orderedProduct.getOrderedProductId());
					cart.setQuantity(orderedProduct.getQuantity());
					System.out.println("cart cart = " + cart);
					int result2 = cartservice.cartEnroll(cart);
					if (result2 > 0) 
						session.setAttribute("msg", "장바구니에 상품을 담았습니다.");
				}
			// } else {
			// 	session.setAttribute("msg", "상품이 장바구니에 이미 존재합니다.");
			// 	return;
			// }
			
		} catch(Exception e) {
			session.setAttribute("msg", "장바구니 담기에 실패하였습니다.");
			e.printStackTrace();
		}
		
		// 1. 사용자입력값 처리
		String sub_cate = request.getParameter("sub_cate");
		System.out.println("sub_cate = " + sub_cate);
		
		// 2. 업무로직
		// db에서 상품 목록 조회
		List<Product> productList = productService.selectProductList();
		// System.out.println(productList);
		
		// 상품 이미지 AttachmentList 가져오기
		List<Attachment> attachmentList = productService.selectAttachmentList();
		// System.out.println(attachmentList);
		
		// 3. view단 위임.
		request.setAttribute("productList", productList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("sub_cate", sub_cate);
		request.getRequestDispatcher("/WEB-INF/views/product/productMenu.jsp").forward(request, response);
		
	}
}
