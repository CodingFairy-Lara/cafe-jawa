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
		Order order = new Order();
		int result2 = 0;

		try {
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			// 수령지점 정보
			String storeId = request.getParameter("store_id");
			System.out.println(storeId);
			// 총 결제가격
			int totPrice = Integer.parseInt(request.getParameter("final_totPrice"));
			System.out.println(totPrice);
			// op_id List
			String [] opIdList = request.getParameterValues("get_op_id");		
			for(String val : opIdList) {
				System.out.println(val+ " ");
			}
			
			order.setMemberId(memberId);
			order.setStoreId(storeId);
			order.setTotalPrice(totPrice);
			
			int result = orderService.orderEnroll(order, opIdList);

			if (result > 0) {
				for(int i = 0; i < opIdList.length; i++ ) {
					String op_Id = opIdList[i];
					result2 = orderService.deleteCart(op_Id);
				}
			}
			
		} catch(Exception e) {
			session.setAttribute("msg", "상품 주문에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/order/payment");
	}
		
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

	
}
