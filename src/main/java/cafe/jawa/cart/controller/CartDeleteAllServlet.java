package cafe.jawa.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.cart.model.service.CartService;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class CartDeleteAllServlet
 */
@WebServlet("/cart/deleteAll")
public class CartDeleteAllServlet extends HttpServlet {
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
			String memberId = request.getParameter("member_Id");
			
			System.out.println("member_Id = " + memberId);
			int result = cartservice.deleteAllCart(memberId);
			
			if(result > 0) {
				int result2 = cartservice.deleteAllOp(memberId);
				if (result2 > 0) 
					session.setAttribute("msg", "장바구니의 모든 상품을 비웠습니다.");
			}
			
		} catch(Exception e) {
			session.setAttribute("msg", "장바구니 비우기에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/cart/cartView");
	}

}