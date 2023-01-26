package cafe.jawa.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class ProductOrderEnrollServlet
 */
@WebServlet("/product/orderEnroll")
public class ProductOrderEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		OrderedProduct orderedProduct = null;

		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String subCategory = request.getParameter("subCategory");

			if (subCategory.equals("CBR  ") || subCategory.equals("ESP  ") || subCategory.equals("FRA  ") || subCategory.equals("BLD  ") || subCategory.equals("TEA  ")) {
				String size = request.getParameter("cup_size");
				String cup = request.getParameter("cup_kind");
				orderedProduct = new OrderedProduct(productId, quantity, size, cup);			
			} else {
				orderedProduct = new OrderedProduct(productId, quantity);			
			}
			
			System.out.println("orderedProduct = " + orderedProduct);
			
			int result = productService.orderedProductEnroll(orderedProduct);
			
			if(result > 0) {
				session.setAttribute("msg", "상품주문 추가 성공!");
			}
		} catch(Exception e) {
			session.setAttribute("msg", "상품주문에 실패하였습니다.");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

}
