package cafe.jawa.member.controller;

import java.io.IOException;
import java.util.Enumeration;

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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			int result = memberService.deleteMember(memberId);
			
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			session.setAttribute("msg", "회원을 탈퇴했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
