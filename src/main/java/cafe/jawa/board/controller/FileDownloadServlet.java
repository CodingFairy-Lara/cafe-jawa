package cafe.jawa.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.board.model.dto.Attachment;
import cafe.jawa.board.model.service.BoardService;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/board/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		
		// 2. 업무로직 - attachment 한건 조회
		Attachment attach = boardService.selectOneAttachment(no);
		System.out.println("attach = " + attach);
		
		// 3. 응답메세지에 파일출력
		// a. 응답헤더 작성 (다운로드할 파일명 originalFilename)
		String filename = URLEncoder.encode(attach.getOriginalFilename(), "utf-8");
		System.out.println("filename = " + filename);
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		// b. 실제파일(renamedFilename)을 읽어서(input) http응답메세지에 쓰기(output)
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		File downFile = new File(saveDirectory, attach.getRenamedFilename());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		// c. 읽고 쓰기
		int len = 0;
		byte[] buffer = new byte[8192]; // 한번에 처리할 byte수
		while((len = bis.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		
	}

}
