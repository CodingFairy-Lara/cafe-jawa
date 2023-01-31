package cafe.jawa.order.model.service;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.commit;
import static cafe.jawa.common.JdbcTemplate.getConnection;
import static cafe.jawa.common.JdbcTemplate.rollback;

import java.sql.Connection;

import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.order.model.dao.OrderDao;
import cafe.jawa.order.model.dto.Order;

public class OrderService {

	private OrderDao orderDao = new OrderDao();

	public int orderEnroll(Order order, int op_id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			Order order_ = orderDao.orderEnroll(conn, order);
			commit(conn);
			
			// op table에 생성된 order_id 부여하기
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


	
	
}
