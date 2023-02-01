package cafe.jawa.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import cafe.jawa.board.model.dto.Attachment;
import cafe.jawa.board.model.dto.Board;
import cafe.jawa.board.model.service.BoardService;
import cafe.jawa.common.HelloMvcFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		// 2. 업무로직
		Board board = boardService.selectOneBoard(no);
		System.out.println("board = " + board);
		
		// 3. view단 처리
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp").forward(request, response);
	}

	/**
	 * 첨부파일이 포함된 게시물 수정
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 0. MultipartRequest객체 생성 - 요청메세지에서 파일을 읽어(Input), 서버컴퓨터에 저장(Output)까지 처리
			String saveDirectory = getServletContext().getRealPath("/upload/board"); // 웹루트디렉토리(src/main/webapp)부터 탐색
			int maxPostSize = 10 * 1024 * 1024; // byte단위 : 1kb-1024, 1mb-1024*1kb
			String encoding = "utf-8";
			FileRenamePolicy policy = new HelloMvcFileRenamePolicy(); // 년월일_시분초밀리초_난수.txt 
			// MultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
			MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			// 1. 사용자입력값 처리 - request가 아닌 MultipartRequest에서 값 꺼내기
			int no = Integer.parseInt(multiReq.getParameter("no"));
			String title = multiReq.getParameter("title");
			String writer = multiReq.getParameter("writer");
			String content = multiReq.getParameter("content");
			String[] delFiles = multiReq.getParameterValues("delFile"); // 실제파일삭제, db attachment의 행삭제
			
			Board board = new Board();
			board.setNo(no);
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			
			Enumeration<String> filenames = multiReq.getFileNames(); // upFile1, upFile2, ...
			while(filenames.hasMoreElements()) {
				String filename = filenames.nextElement(); 
				if(multiReq.getFile(filename) != null) { // 전송된 파일이 있는가?
					Attachment attach = new Attachment();
					attach.setBoardNo(no); // fk 값대입
					attach.setOriginalFilename(multiReq.getOriginalFileName(filename));
					attach.setRenamedFilename(multiReq.getFilesystemName(filename));
					board.addAttachment(attach);
				}
			}
			
			System.out.println(board);
			
			// 2. 업무로직 insert into board (no, title, writer, content) values (seq_board_no.nextval, ?, ?, ?)
			int result = boardService.updateBoard(board);
			
			if(delFiles != null) {
				for(String temp : delFiles) {
					int attachNo = Integer.parseInt(temp);
					Attachment attach = boardService.selectOneAttachment(attachNo);
					
					File delFile = new File(saveDirectory, attach.getRenamedFilename());
					// a. 파일삭제
					if(delFile.exists())
						delFile.delete();
					// b. attachment행삭제
					result = boardService.deleteAttachment(attachNo); // delete from attachment where no = ?
					
				}
			}

			// 3. 리다이렉트
			response.sendRedirect(request.getContextPath() + "/board/boardView?no=" + board.getNo());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "게시글 등록중 오류가 발생했습니다.");
			response.sendRedirect(request.getContextPath() + "/board/boardList");
		}
	}

}
