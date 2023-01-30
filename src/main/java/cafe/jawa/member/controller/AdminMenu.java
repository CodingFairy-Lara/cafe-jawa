package cafe.jawa.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class AdminMenu
 */
@WebServlet("/member/admin")
public class AdminMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> productList = productService.selectProductList();
		
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/WEB-INF/views/member/admin.jsp").forward(request, response);
	}
	
}
