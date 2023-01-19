package cafe.jawa.product.model.service;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import cafe.jawa.product.model.dao.ProductDao;
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.Product;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	
	
	public List<Product> selectProductList() {
		Connection conn = getConnection();
		List<Product> productList = productDao.selectProductList(conn);
		
		close(conn);
		return productList;
	}

	public List<Attachment> selectAttachmentList() {
		Connection conn = getConnection();
		List<Attachment> attachments = productDao.selectAttachmentList(conn);
		close(conn);
		return attachments;
	}

	
	
}