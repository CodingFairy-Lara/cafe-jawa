package cafe.jawa.order.model.service;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.commit;
import static cafe.jawa.common.JdbcTemplate.getConnection;
import static cafe.jawa.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.order.model.dao.OrderDao;
import cafe.jawa.order.model.dto.Order;
import cafe.jawa.order.model.dto.Payment;

public class OrderService {

	private OrderDao orderDao = new OrderDao();

	public int orderEnroll(Order order, int op_id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			Order order_ = orderDao.orderEnroll(conn, order);
			commit(conn);
			
			// 방금 등록된 board.no컬럼값을 조회 - 시퀀스객체의 현재값
			int seqIdNo = orderDao.selectLastseqNo(conn); // select seq_order_id.currval from dual
			System.out.println("seqIdNo = " + seqIdNo);
			
			order_.setOrderId(seqIdNo); // 생성된 pk를 board객체에 다시 주입
			// op_table에 생성된 order_id 부여하기
			if(order_ != null) {
				result = orderDao.updateOpTb(conn, order_, op_id);
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteCart(int op_id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = orderDao.deleteCart(conn, op_id);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Order> getOrderList(String memberId) {
		Connection conn = getConnection();
		List<Order> orderList = null;
		orderList = orderDao.getOrderList(conn, memberId);
		close(conn);
		return orderList;
	}

	public int selectLastOrderNo() {
		Connection conn = getConnection();
		int orderNo = orderDao.selectLastOrderNo(conn);
		System.out.println("orderNo = " + orderNo);
		close(conn);
		return orderNo;
	}

	public int enrollPayment(Payment payment) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = orderDao.enrollPayment(conn, payment);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Payment getPaymentList(int orderNum) {
		Connection conn = getConnection();
		Payment payment = null;
		payment = orderDao.getPaymentList(conn, orderNum);
		close(conn);
		return payment;
	}


	
	
}
