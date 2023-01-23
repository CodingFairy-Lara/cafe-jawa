package cafe.jawa.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class ProductViewServlet
 */
@WebServlet("/product/view")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int id = Integer.parseInt(request.getParameter("productId"));
		System.out.println("id = " + id);
		
		// 2. 업무로직
		// 상품 정보 가져오기 by id
		Product product = productService.selectOneProduct(id);
		
		// 상품 이미지 AttachmentList 가져오기
		List<Attachment> attachmentList = productService.selectAttachmentList();
		System.out.println(attachmentList);

		System.out.println("product = " + product);
		
		// 3. view단 위임.
		request.setAttribute("product", product);
		request.setAttribute("attachmentList", attachmentList);
		request.getRequestDispatcher("/WEB-INF/views/product/productView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
