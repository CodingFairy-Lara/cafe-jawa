package cafe.jawa.cart.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.product.model.dao.ProductDao;
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.exception.ProductException;

public class CartDao {

	private ProductDao productdao = new ProductDao();
	private Properties prop = new Properties();
	
	public CartDao() {
		String path = CartDao.class.getResource("/sql/cart/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int cartEnroll(Connection conn, Cart cart) {
		String sql = prop.getProperty("cartEnroll"); // insert into cart values (seq_cart_id.nextval, ?, ?, ?)
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cart.getMemberId());
			pstmt.setInt(2, cart.getOrderedProductId());
			pstmt.setInt(3, cart.getQuantity());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 추가 오류!", e);
		}
		return result;
	}

	public List<Cart> selectCartList(Connection conn, String memberId) {
		String sql = prop.getProperty("selectCartList"); // select * from cart where member_id = ?
		List<Cart> cartList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Cart cart = handleCartResultSet(rset);
					cartList.add(cart);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("장바구니 리스트 조회 오류!", e);
		}
		return cartList;
	}

	private Cart handleCartResultSet(ResultSet rset)  throws SQLException  {
		Cart cart = new Cart();
		cart.setCartId(rset.getInt("cart_id"));
		cart.setMemberId(rset.getString("member_id"));
		cart.setOrderedProductId(rset.getInt("od_product_id"));
		cart.setQuantity(rset.getInt("quantity"));
		return cart;
	}

	public int deleteOneCart(Connection conn, int cartId) {
		String sql = prop.getProperty("deleteOneCart"); // delete from cart where cart_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, cartId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 삭제 오류!", e);
		}
		return result;
	}

	public int deleteOneOp(Connection conn, int opId) {
		String sql = prop.getProperty("deleteOneOp"); // delete from ordered_product where id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, opId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문상품 삭제 오류!", e);
		}
		return result;
	}

	public int deleteAllCart(Connection conn, String memberId) {
		String sql = prop.getProperty("deleteAllCart"); // delete from cart where member_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 전체 삭제 오류!", e);
		}
		return result;
	}

	public int deleteAllOp(Connection conn, String memberId) {
		String sql = prop.getProperty("deleteAllOP"); // delete from ordered_product where member_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문상품 전체 삭제 오류!", e);
		}
		return result;
	}

	public int modifyCartQty(Connection conn, int cartId, int quantity) {
		String sql = prop.getProperty("modifyCartQty"); // UPDATE cart SET quantity = ? WHERE cart_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, cartId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 수량 변경 오류!", e);
		}
		return result;
	}


	public int modifyOpQty(Connection conn, int opId, int quantity) {
		String sql = prop.getProperty("modifyOpQty"); // UPDATE ordered_product SET quantity = ? WHERE id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, opId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문상품 수량 변경 오류!", e);
		}
		return result;
	}

	public OrderedProduct checkDupCart(Connection conn, int productId, String memberId, String size, String cup) {
		String sql = prop.getProperty("checkDupCart1"); // select * from ordered_product where member_id = ? and product_id = ? and cup = ? and cup_size = ?
		OrderedProduct orderedProduct = new OrderedProduct();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			pstmt.setInt(2, productId);
			pstmt.setString(3, cup);
			pstmt.setString(4, size);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					orderedProduct = productdao.handleOrderedProductResultSet(rset);
					return orderedProduct;
				}
			}
		} catch (Exception e) {
			throw new ProductException("장바구니 중복체크 오류!", e);
		}
		return null;
	}

	public OrderedProduct checkDupCart(Connection conn, int productId, String memberId) {
		String sql = prop.getProperty("checkDupCart2"); // select * from ordered_product where member_id = ? and product_id = ?
		OrderedProduct orderedProduct = new OrderedProduct();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			pstmt.setInt(2, productId);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					orderedProduct = productdao.handleOrderedProductResultSet(rset);
					return orderedProduct;
				}
			}
		} catch (Exception e) {
			throw new ProductException("장바구니 중복체크 오류!", e);
		}
		return null;
	}

	public int deleteCartSelected(Connection conn, int cartId) {
		String sql = prop.getProperty("deleteCartSelected"); // delete from cart where cart_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, cartId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 선택 삭제 오류!", e);
		}
		return result;
	}

	public int deleteOpSelected(Connection conn, int opId) {
		String sql = prop.getProperty("deleteOpSelected"); // delete from ordered_product where id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, opId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 선택 삭제 오류!", e);
		}
		return result;
	}


	
	
	
}
