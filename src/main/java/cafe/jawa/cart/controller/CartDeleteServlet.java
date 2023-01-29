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
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class CartDeleteServlet
 */
@WebServlet("/cart/deleteOne")
public class CartDeleteServlet extends HttpServlet {
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
			int cartId = Integer.parseInt(request.getParameter("cart_id"));
			int opId = Integer.parseInt(request.getParameter("op_id"));
			
			System.out.println("cart_id = " + cartId);
			System.out.println("op_id = " + opId);
			int result = cartservice.deleteOneCart(cartId);
			
			if(result > 0) {
				int result2 = cartservice.deleteOneOp(opId);
				if (result2 > 0) 
					session.setAttribute("msg", "장바구니에서 상품을 삭제하였습니다.");
			}
			
		} catch(Exception e) {
			session.setAttribute("msg", "장바구니 삭제에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/cart/cartView");
	}

}
