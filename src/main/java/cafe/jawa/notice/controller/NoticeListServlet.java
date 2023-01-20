package cafe.jawa.notice.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/notice/noticeList")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		// 1. 사용자 입력값 처리
		final int limit =5;
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page")
		} catch (NumberFormatException e) {}
		
		Map<String, Object> param = new HashMap<>();
		param.put("page", page);
		param.put("limit", limit)
		
		// 2. 업무로직
		// a. db에서 목록조회(페이징)
		List<Notice> noticeList = noticeService.selectNoticeList(param);
		System.out.println(noticeList);
		
		// b. 페이지바
		int totalCount = noticeService.selectTotalCount(); // select count(*) from notice
		System.out.println(totalCount);
		
		String url = request.getRequestURI(); // /notice/noticeList
		String pagebar = 
		
		
		// view단 처리
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp")
			.forward(request, response);
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
