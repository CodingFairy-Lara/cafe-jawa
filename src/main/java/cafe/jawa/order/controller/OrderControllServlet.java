package cafe.jawa.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.cart.model.service.CartService;
import cafe.jawa.member.model.dto.Member;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.service.OrderService;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class OrderControllServlet
 */
@WebServlet("/order/controll")
public class OrderControllServlet extends HttpServlet {
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
		String memberId = request.getParameter("memberId");
		
		Order order = orderService.getOrderStatus(memberId);
		
		
		
		doGet(request, response);
	}

}
