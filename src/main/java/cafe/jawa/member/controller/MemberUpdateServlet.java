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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
			
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			String _birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone"); 
			
			Date birthday = !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
			
			Member member = new Member(memberId, null, memberName, null, birthday, email, phone, null, 0);
			
			int result = memberService.updateMember(member);
			if(result > 0) {
				session.setAttribute("msg", "회원 정보를 수정했습니다.");
				session.setAttribute("loginMember", memberService.selectOneMember(memberId));
			}
		} catch (Exception e) {
			session.setAttribute("msg", "회원 정보 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/member/memberView");
	}

}
