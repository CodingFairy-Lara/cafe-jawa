package cafe.jawa.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.service.ProductService;

/**
 * Servlet implementation class ProductMenuServlet
 */
@WebServlet("/product/menu")
public class ProductMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		
		// 2. 업무로직
		// db에서 상품 목록 조회
		List<Product> productList = productService.selectProductList();
		System.out.println(productList);
		
		// 상품 이미지 Attachment 가져오기
		List<Attachment> attachmentList = productService.selectAttachmentList();
		System.out.println(attachmentList);
		
		String url = request.getRequestURI(); // /jawa/product/menu
		
		// 3. view단 위임.
		request.setAttribute("productList", productList);
		request.setAttribute("attachmentList", attachmentList);
		request.getRequestDispatcher("/WEB-INF/views/product/productMenu.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
