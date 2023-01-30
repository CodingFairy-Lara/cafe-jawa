package cafe.jawa.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.cart.model.service.CartService;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class CartDeleteSelectedServlet
 */
@WebServlet("/cart/deleteSelected")
public class CartDeleteSelectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private CartService cartservice = new CartService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		int cartId = Integer.parseInt(request.getParameter("cart_id"));
		int opId = Integer.parseInt(request.getParameter("op_id"));
		System.out.println("cart_id = " + cartId);
		System.out.println("op_id = " + opId);
		
		response.getWriter().write(deleteSelected(cartId, opId)+"");
	}
	
	public int deleteSelected(int cartId, int opId) {
		int result2 = 0;
		try {
			int result = cartservice.deleteCartSelected(cartId);
			if(result > 0) {
				result2 = cartservice.deleteOpSelected(opId);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return result2;
	}

}
