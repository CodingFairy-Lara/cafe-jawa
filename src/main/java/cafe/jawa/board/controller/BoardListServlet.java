package cafe.jawa.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.board.model.dto.Board;
import cafe.jawa.board.model.service.BoardService;
import cafe.jawa.common.HelloMvcUtils;


/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService boardService = new BoardService();


	/**
	 * paging처리
	 * 1. content영역
	 *   - page
	 *   - limit
	 *   - start
	 *   - end
	 *   
	 * 2. pagebar영역 - HelloMvcUtils.getPagebar()
	 *   - page
	 *   - limit
	 *   - totalCount
	 *   - url 
	 *   
	 *   - totalPage
	 *   - pagebarSize
	 *   - pageStart
	 *   - pageEnd
	 *   - pageNo
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		final int limit = 5;
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {}
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit);
		
		// 2. 업무로직
		// a. db에서 목록조회(페이징)
		List<Board> boardList = boardService.selectBoardList(param);
		System.out.println(boardList);
		// b. 페이지바
		int totalCount = boardService.selectTotalCount(); // select count(*) from board
		System.out.println(totalCount);
		
		String url = request.getRequestURI(); // /mvc/board/boardList
		String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
		System.out.println(pagebar);
		
		// 3. view단 위임.
		request.setAttribute("boardList", boardList);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
		
	}

}
