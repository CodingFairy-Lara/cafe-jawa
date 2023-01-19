package cafe.jawa.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.member.model.dto.Member;
import cafe.jawa.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

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
		response.sendRedirect(request.getContextPath() + "/");
	}

}
