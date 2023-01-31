package cafe.jawa.product.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.dto.Product;
import cafe.jawa.product.model.exception.ProductException;

public class ProductDao {
	private Properties prop = new Properties();
	
	public ProductDao() {
		String path = ProductDao.class.getResource("/sql/product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> selectProductList(Connection conn) {
		String sql = prop.getProperty("selectProductList");
		List<Product> productList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Product product = handleProductResultSet(rset);
					productList.add(product);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("상품 목록 조회 오류!", e);
		}
		return productList;
	}

	private Product handleProductResultSet(ResultSet rset) throws SQLException {
		Product product = new Product();
		product.setProductId(rset.getInt("id"));
		product.setSubCategory(rset.getString("sub_category_id"));
		product.setProductName(rset.getString("name"));
		product.setDescription(rset.getString("describe"));
		product.setPrice(rset.getInt("unit_price"));
		product.setEnrollDate(rset.getTimestamp("enroll_date"));
		product.setFactor(rset.getString("factor"));
		return product;
	}

	public List<Attachment> selectAttachmentList(Connection conn) {
		String sql = prop.getProperty("selectProductImageList"); // select * from product_images
		List<Attachment> attachmentList = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Attachment attach = handleAttachmentResultSet(rset);
					attachmentList.add(attach);
				}
			}
		} catch (Exception e) {
			throw new ProductException("상품 이미지 조회 오류!", e);
		}
		return attachmentList;
	}
	
//	public List<Attachment> selectAttachmentList(Connection conn, int productId) {
//		String sql = prop.getProperty("selectProductImages"); // select * from product_images where product_id = ?
//		List<Attachment> attachmentList = new ArrayList<>();
//		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
//			pstmt.setInt(1, productId);
//			
//			try(ResultSet rset = pstmt.executeQuery()){
//				while(rset.next()) {
//					Attachment attach = handleAttachmentResultSet(rset);
//					attachmentList.add(attach);
//				}
//			}
//		} catch (Exception e) {
//			throw new ProductException("상품 이미지 조회 오류!", e);
//		}
//		return attachmentList;
//	}

	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		attach.setId(rset.getInt("id"));
		attach.setProductId(rset.getInt("product_id"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		attach.setEnrollDate(rset.getDate("enroll_date"));
		return attach;
	}

	public Product selectOneProduct(Connection conn, int id) {
		String sql = prop.getProperty("selectOneProduct"); // select * from product where product_id = ?
		Product product = new Product();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					product = handleProductResultSet(rset);
				}
			}
		} catch (Exception e) {
			throw new ProductException("상품 정보 조회 오류!", e);
		}
		return product;
	}

    public int orderedProductEnroll(Connection conn, OrderedProduct orderedProduct) {
		String sql = prop.getProperty("orderedProductEnroll"); // insert into ordered_product values (seq_ordered_product_id.nextval, null, ?, ?, ?, ?, ?)
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, orderedProduct.getProductId());
			pstmt.setInt(2, orderedProduct.getQuantity());
			pstmt.setString(3, orderedProduct.getCupSize());
			pstmt.setString(4, orderedProduct.getCup());
			pstmt.setString(5, orderedProduct.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("상품주문 추가 오류!", e);
		}
		return result;
	}
	
	public OrderedProduct handleOrderedProductResultSet(ResultSet rset) throws SQLException {
		OrderedProduct orderedProduct = new OrderedProduct();
		orderedProduct.setOrderedProductId(rset.getInt("id"));
		orderedProduct.setProductId(rset.getInt("product_id"));
		orderedProduct.setQuantity(rset.getInt("quantity"));
		orderedProduct.setCup(rset.getString("cup"));
		orderedProduct.setCupSize(rset.getString("cup_size"));
		orderedProduct.setMemberId(rset.getString("member_id"));
		orderedProduct.setOrderId(rset.getInt("order_id"));
		return orderedProduct;
	}

	public OrderedProduct selectOrderedProduct(Connection conn, int productId, int quantity, String memberId) {
		String sql = prop.getProperty("selectOrderedProduct"); // select * from ordered_product where order_id is null and product_id = ? and quantity = ? and member_id = ?
		OrderedProduct orderedProduct = new OrderedProduct();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, productId);
			pstmt.setInt(2, quantity);
			pstmt.setString(3, memberId);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					orderedProduct = handleOrderedProductResultSet(rset);
				}
			}
		} catch (Exception e) {
			throw new ProductException("상품 정보 조회 오류!", e);
		}
		return orderedProduct;
	}

	public List<OrderedProduct> selectOrderedProductList(Connection conn) {
		String sql = prop.getProperty("selectOrderedProductList");
		List<OrderedProduct> orderedProductList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					OrderedProduct orderedProduct = handleOrderedProductResultSet(rset);
					orderedProductList.add(orderedProduct);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("주문상품 리스트 조회 오류!", e);
		}
		return orderedProductList;
	}

	public int priceChange(Connection conn, Product product) {
		int result = 0;
		String sql = prop.getProperty("priceChange");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, Integer.toString(product.getPrice()));
			pstmt.setString(2, product.getProductName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("가격 변경 오류!", e);
		}
		return result;
	}
	
	
	
	
	
}
