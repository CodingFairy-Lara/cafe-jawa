package cafe.jawa.order.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
//		order.setOrderDate(rset.getTimestamp("ORDER_DATE"));
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
		// order_num 내림차순 조회
		String sql = prop.getProperty("getOrderList"); // select * from order_tb where member_id = ? ORDER BY order_num DESC
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

	public List<Order> getOrderListAll(Connection conn) {
		// order_num 내림차순 조회
		String sql = prop.getProperty("getOrderListAll"); // select * from order_tb ORDER BY order_num DESC
		List<Order> orderList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Order order = handleOrderResultSet(rset);
					orderList.add(order);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("전체 주문목록 조회 오류!", e);
		}
		return orderList;
	}

	public int updateOrderStatus_2(Connection conn, int orderNum) {
		String sql = prop.getProperty("updateOrderStatus_2"); // UPDATE order_tb SET order_status = 2 WHERE order_num = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, orderNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문테이블_status 업데이트 오류! (2)", e);
		}
		return result;
	}

	/*
	 * for 관리자계정
	 * select ot.order_num,
		REGEXP_REPLACE(LISTAGG(ot.member_id, ',') WITHIN GROUP (ORDER BY ot.member_id DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as member_id,
		REGEXP_REPLACE(LISTAGG(ot.ID, ',') WITHIN GROUP (ORDER BY ot.ID DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as ID,
		REGEXP_REPLACE(LISTAGG(ot.STORE_ID, ',') WITHIN GROUP (ORDER BY ot.STORE_ID DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as STORE_ID,
		REGEXP_REPLACE(LISTAGG(ot.ORDER_STATUS, ',') WITHIN GROUP (ORDER BY ot.member_id DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as ORDER_STATUS,
		REGEXP_REPLACE(LISTAGG(ot.ORDER_DATE, ',') WITHIN GROUP (ORDER BY ot.ORDER_DATE DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as ORDER_DATE,
		REGEXP_REPLACE(LISTAGG(ot.TOTAL_PRICE, ',') WITHIN GROUP (ORDER BY ot.TOTAL_PRICE DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as TOTAL_PRICE,
		REGEXP_REPLACE(LISTAGG(p.PAYMENT_METHOD, ',') WITHIN GROUP (ORDER BY p.PAYMENT_METHOD DESC ), '([^,]+)(,\1)*(,|$)', '\1\3') as PAYMENT_METHOD
		from order_tb ot, payment p
		where p.order_num = ot.order_num 
		group by ot.order_num;
	 */
	public List<Order> getNewOrderList(Connection conn) {
		// order_num 내림차순 조회
		String sql = prop.getProperty("getNewOrderList");  // SELECT DISTINCT member_id, order_num, STORE_ID, TOTAL_PRICE, ORDER_STATUS FROM Order_tb ORDER BY order_num DESC
		List<Order> orderList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Order order = handleNewOrderResultSet(rset);
					orderList.add(order);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("[관리자 계정] 전체 주문목록 조회 오류!", e);
		}
		return orderList;
	}
	
	// payment 정보 추가
	private Order handleNewOrderResultSet(ResultSet rset) throws SQLException {
		Order order = new Order();
//		order.setOrderId(rset.getInt("ID"));
		order.setMemberId(rset.getString("MEMBER_ID"));
		order.setStoreId(rset.getString("STORE_ID"));
		order.setStatus(rset.getInt("ORDER_STATUS"));
//		order.setOrderDate(rset.getTimestamp("ORDER_DATE"));
		order.setTotalPrice(rset.getInt("TOTAL_PRICE"));
		order.setOrderNum(rset.getInt("ORDER_NUM"));
//		order.setPayment_method(rset.getString("PAYMENT_METHOD"));
		return order;
	}

	public int acceptOrder(Connection conn, int order_num) {
		String sql = prop.getProperty("updateOrderStatus_3"); // UPDATE order_tb SET order_status = 3 WHERE order_num = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, order_num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문테이블_status 업데이트 오류! (3)", e);
		}
		return result;
	}

	public List<Order> getUserOrderList(Connection conn, String memberId) {
		String sql = prop.getProperty("getUserOrderList"); // SELECT DISTINCT member_id, order_num, STORE_ID, TOTAL_PRICE, ORDER_STATUS FROM Order_tb where member_id= ? ORDER BY order_num DESC
		List<Order> orderList = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Order order = handleNewOrderResultSet(rset);
					orderList.add(order);					
				}
			}
		} catch (SQLException e) {
			throw new ProductException("사용자 주문목록 조회 오류!", e);
		}
		return orderList;
	}

	public int updateStatus_4(Connection conn, int order_num, int orderStatus) {
		String sql = prop.getProperty("updateOrderStatus_4"); // UPDATE order_tb SET order_status = ? WHERE order_num = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, orderStatus);
			pstmt.setInt(2, order_num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("주문테이블_status 업데이트 오류! (4~0)", e);
		}
		return result;
	}
	
}
