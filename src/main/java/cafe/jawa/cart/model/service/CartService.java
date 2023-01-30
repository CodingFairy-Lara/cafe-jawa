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
import cafe.jawa.product.model.dto.Product;

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

	public int deleteOneCart(int cartId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.deleteOneCart(conn, cartId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteOneOp(int opId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.deleteOneOp(conn, opId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteAllCart(String memberId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.deleteAllCart(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteAllOp(String memberId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.deleteAllOp(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int modifyCartQty(int cartId, int quantity) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.modifyCartQty(conn, cartId, quantity);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int modifyOpQty(int opId, int quantity) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.modifyOpQty(conn, opId, quantity);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public OrderedProduct checkDupCart(int productId, String memberId, String size, String cup) {
		Connection conn = getConnection();
		OrderedProduct orderedProduct = cartDao.checkDupCart(conn, productId, memberId, size, cup);
		close(conn);
		return orderedProduct;
	}

	public OrderedProduct checkDupCart(int productId, String memberId) {
		Connection conn = getConnection();
		OrderedProduct orderedProduct = cartDao.checkDupCart(conn, productId, memberId);
		close(conn);
		return orderedProduct;
	}

	public int deleteCartSelected(int cartId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.deleteCartSelected(conn, cartId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteOpSelected(int opId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = cartDao.deleteOpSelected(conn, opId);
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
