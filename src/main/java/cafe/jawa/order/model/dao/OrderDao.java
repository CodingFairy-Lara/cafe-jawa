package cafe.jawa.order.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cafe.jawa.cart.model.dao.CartDao;
import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.product.model.exception.ProductException;

public class OrderDao {

	private Properties prop = new Properties();
	
	public OrderDao() {
		String path = CartDao.class.getResource("/sql/order/order-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int orderEnroll(Connection conn, Order order) {
		String sql = prop.getProperty("orderEnroll"); // insert into order_tb values (seq_order_id.nextval, ?, ?, default, default, ?)
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, order.getMemberId());
			pstmt.setString(2, order.getStoreId());
			pstmt.setInt(3, order.getTotalPrice());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문 생성 오류!", e);
		}
		return result;
	}

	public Order getOrderInfo(Connection conn, Order order) {
		String sql = prop.getProperty("getOrderInfo"); // select * from order_tb where member_id = ? and store_id = ? and order_status = 1 and total_price = ?
		Order order_ = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, order.getMemberId());
			pstmt.setString(2, order.getStoreId());
			pstmt.setInt(3, order.getTotalPrice());
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					order_ = handleOrderResultSet(rset);
				}
			}
		} catch (SQLException e) {
			throw new ProductException("주문정보 조회 오류!", e);
		}
		return order_;
	}

	private Order handleOrderResultSet(ResultSet rset)  throws SQLException  {
		Order order = new Order();
		order.setOrderId(rset.getInt("id"));
		order.setMemberId(rset.getString("member_id"));
		order.setStoreId(rset.getString("store_id"));
		order.setStatus(rset.getInt("order_status"));
		order.setOrderDate(rset.getDate("order_date"));
		order.setTotalPrice(rset.getInt("total_price"));
		return order;
	}

	public int updateOpTb(Connection conn, Order order_, String op_Id) {
		String sql = prop.getProperty("updateOpTb"); // UPDATE ordered_product SET order_id = ? WHERE id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, order_.getOrderId());
			pstmt.setInt(2, Integer.parseInt(op_Id));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문상품 목록 업데이트 오류!", e);
		}
		return result;
	}

	public int deleteCart(Connection conn, String op_Id) {
		String sql = prop.getProperty("deleteCart"); // delete from cart where od_product_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, Integer.parseInt(op_Id));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 제거 오류!", e);
		}
		return result;
	}
	
	
}
