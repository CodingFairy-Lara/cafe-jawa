package cafe.jawa.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.board.model.dto.Attachment;
import cafe.jawa.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		// 2. 업무로직 - board삭제 - attachment삭제
		// 저장된 첨부파일 삭제
		List<Attachment> attachments = boardService.selectAttachmentByBoardNo(no);
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		for(Attachment attach : attachments) {
			File delFile = new File(saveDirectory, attach.getRenamedFilename());
			boolean bool = delFile.delete();
			System.out.println(bool ? "파일 삭제 성공!" : "파일 삭제 실패!");
		}
		
		int result = boardService.deleteBoard(no); 
		
		// 3. redirect : /board/boardList
		request.getSession().setAttribute("msg", "게시글을 성공적으로 삭제했습니다.");
		response.sendRedirect(request.getContextPath() + "/board/boardList");
		
	}

}






