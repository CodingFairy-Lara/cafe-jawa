package cafe.jawa.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.board.model.dto.Board;
import cafe.jawa.board.model.service.BoardService;
import cafe.jawa.common.HelloMvcUtils;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		// board 쿠키처리 board="[84][22]"
		String boardCookieVal = "";
		boolean hasRead = false;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				
				if("board".equals(name)) {
					boardCookieVal = value;
					if(value.contains("[" + no + "]")) {
						hasRead = true;
					}
				}
			}
		}
		
		// 응답쿠키
		if(!hasRead) {
			Cookie cookie = new Cookie("board", boardCookieVal + "[" + no + "]");
			cookie.setMaxAge(365 * 24 * 60 * 60 ); // 365일
			cookie.setPath(request.getContextPath() + "/board/boardView");
			response.addCookie(cookie);
		}
		

		// 2. 업무로직 - 게시판/첨부파일테이블 조회
		// selectOneBoard = select * from board where no = ?
		// selectAttachmentByBoardNo = select * from attachment where board_no = ?
		Board board = boardService.selectOneBoard(no, hasRead);
		System.out.println("board = " + board);
		
		// 개행문자 변환처리
		board.setContent(
				HelloMvcUtils.convertLineFeedToBr(
					HelloMvcUtils.escapeHtml(board.getContent()))
			);
		
		// 3. view단 위임
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp").forward(request, response);
	}


}
