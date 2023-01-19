package cafe.jawa.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.member.model.dto.Member;
import cafe.jawa.member.model.service.MemberService;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		try {
			String memberId = request.getParameter("memberId");
			String password = request.getParameter("password");
			String memberName = request.getParameter("memberName");
			String _birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
			
			Member member = new Member(memberId, password, memberName, null, birthday, email, phone, null, 0);
			
			int result = memberService.insertMember(member);
			
			if(result > 0) {
				session.setAttribute("msg", "카페자와 가입을 축하드립니다!");
			}
		} catch(Exception e) {
			session.setAttribute("msg", "회원가입에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/");
	}
}
