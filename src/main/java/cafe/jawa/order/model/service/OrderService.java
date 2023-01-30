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

	public int orderEnroll(Order order, String[] opIdList) {
		int result = 0;
		int result2 = 0;
		Connection conn = getConnection();
		try {
			result = orderDao.orderEnroll(conn, order);
			commit(conn);
			if (result > 0 ) {
				Order order_ = orderDao.getOrderInfo(conn, order);
				if(order_ != null) {
					for (int i = 0; i < opIdList.length; i++) {
						String op_Id = opIdList[i];
						result2 = orderDao.updateOpTb(conn, order_, op_Id);
					}
				}
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result2;
	}

	public int deleteCart(String op_Id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = orderDao.deleteCart(conn, op_Id);
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
