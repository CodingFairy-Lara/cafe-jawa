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
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class CartEditQuantityServlet
 */
@WebServlet("/cart/modifyQty")
public class CartModifyQtyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private CartService cartservice = new CartService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		response.setContentType("text/html;charset=UTF-8");
		
		int cartId = Integer.parseInt(request.getParameter("cart_id"));
		int opId = Integer.parseInt(request.getParameter("op_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("cart_id = " + cartId);
		System.out.println("op_id = " + opId);
		System.out.println("quantity = " + quantity);
		
		response.getWriter().write(modifyCartQty(cartId, opId, quantity)+"");
	}
	
	public int modifyCartQty(int cartId, int opId, int quantity) {
		int result2 = 0;
		try {
			int result = cartservice.modifyCartQty(cartId, quantity);
			if(result > 0) {
				result2 = cartservice.modifyOpQty(opId, quantity);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return result2;
	}

}
