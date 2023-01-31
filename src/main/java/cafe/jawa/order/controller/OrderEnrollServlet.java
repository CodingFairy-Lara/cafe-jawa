package cafe.jawa.order.controller;

import java.io.IOException;
import java.util.Arrays;
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
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.service.OrderService;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class OrderEnrollServlet
 */
@WebServlet("/order/enroll")
public class OrderEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartservice = new CartService();
	private ProductService productService = new ProductService();
	private OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String memberId = request.getParameter("memberId");
		System.out.println("결제진행중 " + memberId);
		// 수령지점 정보
		String storeId = request.getParameter("storeId");
		System.out.println(storeId);
		// 결제수단 정보
		String payment_type = request.getParameter("payment_type");
		System.out.println(payment_type);
		// 총 결제가격
		int totPrice = Integer.parseInt(request.getParameter("payment_totPrice"));
		System.out.println(totPrice);
		// op_id List
		String opIdList_ = request.getParameter("opIdList");		
		String opIdList__ = opIdList_.substring(1, opIdList_.length()-1);
		String[] opIdList = opIdList__.replace(" ", "").split(",");
		
		for(String val : opIdList) {
			System.out.println(val+ " ");
		}
		
		response.getWriter().write(orderEnroll(memberId, storeId, payment_type, totPrice, opIdList)+"");
	}
			
		
	public int orderEnroll(String memberId, String storeId, String payment_type, int totPrice, String[] opIdList) {
		int result2 = 0;
		Order order = new Order();
		try {
			
			order.setMemberId(memberId);
			order.setStoreId(storeId);
			order.setTotalPrice(totPrice);
			
			for(int i = 0; i < opIdList.length; i++ ) {
				String op_Id = opIdList[i];
				int op_id = Integer.parseInt(op_Id);
				System.out.println(op_id);
				
				int result = orderService.orderEnroll(order, op_id);
				
				if (result > 0) {
					result2 = orderService.deleteCart(op_id);
				}
			}}
			catch(Exception e) {
				e.printStackTrace();
				return 0;
			}
			return result2;
			
		}
}
