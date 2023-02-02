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
		
		// 2. 업무로직 - 게시판/첨부파일테이블 조회
		Board board = boardService.selectOneBoard(no);
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
