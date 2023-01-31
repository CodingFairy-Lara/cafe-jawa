package cafe.jawa.order.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cafe.jawa.cart.model.dao.CartDao;
import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.dto.Payment;
import cafe.jawa.product.model.dto.Product;
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
		String sql = prop.getProperty("orderEnroll"); // insert into order_tb values (seq_order_id.nextval, ?, ?, default, default, ?, ?)
		String sql2 = prop.getProperty("getOrderInfo"); // select * from order_tb where member_id = ? and store_id = ? and order_status = 1 and total_price = ?
		int result = 0;
		Order order_ = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, order.getMemberId());
			pstmt.setString(2, order.getStoreId());
			pstmt.setInt(3, order.getTotalPrice());
			pstmt.setInt(4, order.getOrderNum());
			
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

	private Order handleOrderResultSet(ResultSet rset) throws SQLException  {
		Order order = new Order();
		order.setOrderId(rset.getInt("ID"));
		order.setMemberId(rset.getString("MEMBER_ID"));
		order.setStoreId(rset.getString("STORE_ID"));
		order.setStatus(rset.getInt("ORDER_STATUS"));
		order.setOrderDate(rset.getDate("ORDER_DATE"));
		order.setTotalPrice(rset.getInt("TOTAL_PRICE"));
		order.setOrderNum(rset.getInt("ORDER_NUM"));
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

	public List<Order> getOrderList(Connection conn, String memberId) {
		String sql = prop.getProperty("getOrderList"); // select * from order_tb where member_id = ?
		List<Order> orderList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Order order = handleOrderResultSet(rset);
					orderList.add(order);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("주문 목록 조회 오류!", e);
		}
		return orderList;
	}

	public int selectLastOrderNo(Connection conn) {
		String sql = prop.getProperty("selectLastOrderNo"); // select MAX(order_num) from order_tb
		int orderNo = 0;
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
		){
			if(rset.next())
				orderNo = rset.getInt(1);
			
		} catch (SQLException e) {
			throw new ProductException("주문번호 조회 오류!", e);
		}
		return orderNo;
	}

	public int selectLastseqNo(Connection conn) {
		String sql = prop.getProperty("selectLastseqNo"); // SELECT seq_order_id.CURRVAL FROM DUAL
		int orderId = 0;
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
		){
			if(rset.next())
				orderId = rset.getInt(1);
			
		} catch (SQLException e) {
			throw new ProductException("주문ID 조회 오류!", e);
		}
		return orderId;
	}

	public int enrollPayment(Connection conn, Payment payment) {
		String sql = prop.getProperty("enrollPayment"); // insert into payment values (seq_payment_id.nextval, ?, ?)
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, payment.getOrderNum());
			pstmt.setString(2, payment.getPaymentMethod());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("결제내역 저장 오류!", e);
		}
		return result;
		
	}

	public Payment getPaymentList(Connection conn, int orderNum) {
		String sql = prop.getProperty("getPaymentList"); // select * from payment where order_num = ?
		Payment payment = new Payment();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, orderNum);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					payment = handlePaymentResultSet(rset);
				}
			}
		} catch (SQLException e) {
			throw new ProductException("결제내역 조회 오류!", e);
		}
		return payment;
	}

	private Payment handlePaymentResultSet(ResultSet rset) throws SQLException  {
		Payment payment = new Payment();
		payment.setPaymentId(rset.getInt("PAYMENT_ID"));
		payment.setOrderNum(rset.getInt("ORDER_NUM"));
		payment.setPaymentMethod(rset.getString("PAYMENT_METHOD"));
		return payment;
	}
	
}
