package cafe.jawa.cart.model.service;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.commit;
import static cafe.jawa.common.JdbcTemplate.getConnection;
import static cafe.jawa.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import cafe.jawa.cart.model.dao.CartDao;
import cafe.jawa.cart.model.dto.Cart;
import cafe.jawa.product.model.dto.OrderedProduct;

public class CartService {
	private CartDao cartDao = new CartDao();

	public int cartEnroll(Cart cart) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.cartEnroll(conn, cart);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Cart> selectCartList(String memberId) {
		Connection conn = getConnection();
		List<Cart> cartList = cartDao.selectCartList(conn, memberId);
		
		close(conn);
		return cartList;
	}

}
