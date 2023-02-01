package cafe.jawa.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.member.model.dto.Member;
import cafe.jawa.member.model.dto.MemberRole;
import cafe.jawa.member.model.service.MemberService;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.dto.Payment;
import cafe.jawa.order.model.service.OrderService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private OrderService orderService = new OrderService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		
		Member member = memberService.selectOneMember(memberId);
		HttpSession session = request.getSession();
		
		if(member != null && password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
			if(saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 7);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
		} else {
			session.setAttribute("msg", "아이디가 존재하지 않거나 비밀번호가 틀립니다.");
		}
		
		if (member.getMemberRole() == MemberRole.valueOf("A")) {
			// 관리자일 경우, 모든 주문정보 list 가져오기
			List<Order> orderListAll = orderService.getNewOrderList();
			System.out.println("orderListAll = " + orderListAll);
			session.setAttribute("orderListAll", orderListAll);
			// 결제정보 list 가져오기
			List<Payment> paymentList = new ArrayList<>();
			for (Order order : orderListAll) {
				int orderNum_ = order.getOrderNum();
				Payment payment_ = orderService.getPaymentList(orderNum_);
				paymentList.add(payment_);
			}
			session.setAttribute("paymentList", paymentList);

			
		}
		if (member.getMemberRole() == MemberRole.valueOf("U")) {
			List<Order> userOrderList = orderService.getOrderList(memberId);
			System.out.println("userOrderList = " + userOrderList);
			if (userOrderList != null) {
				session.setAttribute("userOrderList", userOrderList);
			}
		}
		
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
