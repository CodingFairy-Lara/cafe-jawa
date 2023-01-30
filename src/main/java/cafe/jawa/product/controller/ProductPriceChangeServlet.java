package cafe.jawa.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class ProductPriceChangeServlet
 */
@WebServlet("/product/productPriceChange")
public class ProductPriceChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
			String productName = request.getParameter("productName");
			String _price = request.getParameter("price");
			int price = Integer.parseInt(_price);
			
			Product product = new Product(0, null, productName, null, price , null, null, null);
			
			int result = productService.priceChange(product);
			if(result > 0) {
				session.setAttribute("msg", "가격을 변경하였습니다.");
			}
		} catch (Exception e) {
			session.setAttribute("msg", "가격 변경에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

}
