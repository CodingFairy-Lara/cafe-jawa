package cafe.jawa.store.model.service;

import java.sql.Connection;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.commit;
import static cafe.jawa.common.JdbcTemplate.getConnection;
import static cafe.jawa.common.JdbcTemplate.rollback;

import cafe.jawa.store.model.dao.StoreDao;
import cafe.jawa.store.model.dto.Store;

public class StoreService {
	private StoreDao storeDao = new StoreDao();

	public int storeOpen(Store store) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = storeDao.storeOpen(conn, store);
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
