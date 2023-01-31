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

	public Order orderEnroll(Connection conn, Order order) {
		String sql = prop.getProperty("orderEnroll"); // insert into order_tb values (seq_order_id.nextval, ?, ?, default, default, ?)
		String sql2 = prop.getProperty("getOrderInfo"); // select * from order_tb where member_id = ? and store_id = ? and order_status = 1 and total_price = ?
		int result = 0;
		Order order_ = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, order.getMemberId());
			pstmt.setString(2, order.getStoreId());
			pstmt.setInt(3, order.getTotalPrice());
			
			result = pstmt.executeUpdate();
			if (result > 0) {
				// 주문정보 업데이트
				try(PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
					pstmt2.setString(1, order.getMemberId());
					pstmt2.setString(2, order.getStoreId());
					pstmt2.setInt(3, order.getTotalPrice());
					
					try(ResultSet rset = pstmt2.executeQuery()){
						while(rset.next()) {
							order_ = handleOrderResultSet(rset);
						}
					}
				} catch (SQLException e) {
					throw new ProductException("주문정보 조회 오류!", e);
				}
			}
		} catch (SQLException e) {
			throw new ProductException("주문 생성 오류!", e);
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

	public int updateOpTb(Connection conn, Order order_, int op_id) {
		String sql = prop.getProperty("updateOpTb"); // UPDATE ordered_product SET order_id = ? WHERE id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, order_.getOrderId());
			pstmt.setInt(2, op_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문 목록 업데이트 오류!", e);
		}
		return result;
	}

	public int deleteCart(Connection conn, int op_id) {
		String sql = prop.getProperty("deleteCart"); // delete from cart where od_product_id = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, op_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("장바구니 제거 오류!", e);
		}
		return result;
	}
	
	
}
