package cafe.jawa.order.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AdminUpdateOrderServlet
 */
@WebServlet("/admin/updateStatus")
public class AdminUpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private CartService cartService = new CartService();
	private OrderService orderService = new OrderService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		int result = orderService.updateStatus_4(order_num);
		

		
	}

	
}
