package cafe.jawa.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.store.model.dto.Store;
import cafe.jawa.store.model.dto.StoreStatus;
import cafe.jawa.store.model.service.StoreService;

/**
 * Servlet implementation class StoreOpenServlet
 */
@WebServlet("/store/storeOpen")
public class StoreOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreService storeService = new StoreService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
			String store_id = request.getParameter("store_id");
			String _status = request.getParameter("storeStatus");
			
			StoreStatus status = StoreStatus.valueOf(_status);
			
			
			Store store = new Store(store_id, null, null, null, status);
			
			int result = storeService.storeOpen(store);
			if(result > 0 && status.equals("O")) {
				session.setAttribute("msg", "매장을 오픈했습니다.");
			} else {
				session.setAttribute("msg", "매장을 닫았습니다.");
			}
		} catch (Exception e) {
			session.setAttribute("msg", "매장 오픈/클로즈에 실패했습니다.");
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
