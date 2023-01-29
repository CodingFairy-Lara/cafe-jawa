package cafe.jawa.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.cart.model.service.CartService;
import cafe.jawa.member.model.dto.Member;
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
				orderedProduct = new OrderedProduct(productId, quantity, size, cup, memberId);			
			} else {
				orderedProduct = new OrderedProduct(productId, quantity, memberId);			
			}
			System.out.println("cartEnroll orderedProduct = " + orderedProduct);
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
			
		} catch(Exception e) {
			session.setAttribute("msg", "장바구니 담기에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/product/view?productId=" +productId);
	}

}
