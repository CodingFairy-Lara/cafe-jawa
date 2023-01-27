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
import cafe.jawa.product.model.dto.OrderedProduct;
import cafe.jawa.product.model.exception.ProductException;

public class CartDao {

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


	
	
	
}
