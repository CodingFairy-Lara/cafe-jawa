package cafe.jawa.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.cart.model.service.CartService;
import cafe.jawa.member.model.dto.Member;
import cafe.jawa.member.model.dto.MemberRole;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.dto.Payment;
import cafe.jawa.order.model.service.OrderService;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class AdminNewOrderListServlet
 */
@WebServlet("/admin/newOrderList")
public class AdminNewOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	private CartService cartService = new CartService();
	private OrderService orderService = new OrderService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		List<Order> orderListAll = null;
		
		orderListAll = getNewOrderList(memberId);
		if (orderListAll != null) {
			session.setAttribute("orderListAll", orderListAll);
		}
		
		// 결제정보 list 가져오기
		List<Payment> paymentList = new ArrayList<>();
		for (Order order : orderListAll) {
			int orderNum_ = order.getOrderNum();
			Payment payment_ = orderService.getPaymentList(orderNum_);
			paymentList.add(payment_);
		}
		session.setAttribute("paymentList", paymentList);
		
		System.out.println("orderListAll = " + orderListAll);
		System.out.println("paymentList = " + paymentList);
		
//		response.getWriter().write(getNewOrderList(memberId)+"");
	}
	
	public List<Order> getNewOrderList(String memberId) {
		List<Order> orderListAll = null;
		orderListAll = orderService.getNewOrderList();
		
		return orderListAll;
		
	}
}
