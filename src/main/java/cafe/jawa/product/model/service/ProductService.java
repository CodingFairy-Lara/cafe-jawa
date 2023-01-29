package cafe.jawa.product.model.service;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.commit;
import static cafe.jawa.common.JdbcTemplate.getConnection;
import static cafe.jawa.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import cafe.jawa.product.model.dao.ProductDao;
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;
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

	public Product selectOneProduct(int id) {
		Connection conn = getConnection();
		Product product = productDao.selectOneProduct(conn, id);
		close(conn);
		return product;
}

    public int orderedProductEnroll(OrderedProduct orderedProduct) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = productDao.orderedProductEnroll(conn, orderedProduct);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public OrderedProduct selectOrderedProduct(int productId, int quantity, String memberId) {
		Connection conn = getConnection();
		OrderedProduct orderedProduct = productDao.selectOrderedProduct(conn, productId, quantity, memberId);
		close(conn);
		return orderedProduct;
	}

	public List<OrderedProduct> selectOrderedProductList() {
		Connection conn = getConnection();
		List<OrderedProduct> orderedProductList = productDao.selectOrderedProductList(conn);
		
		close(conn);
		return orderedProductList;
	}

}