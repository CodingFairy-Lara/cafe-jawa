package cafe.jawa.store.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import cafe.jawa.store.model.dto.Store;
import cafe.jawa.store.model.exception.StoreException;

public class StoreDao {
	
	private Properties prop = new Properties();
	
	public StoreDao() {
		String path = StoreDao.class.getResource("/sql/store/store-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int storeOpen(Connection conn, Store store) {
		int result = 0;
		String sql = prop.getProperty("storeOpen");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, store.getStatus().name());
			pstmt.setString(2, store.getStore_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new StoreException("매장 오픈/클로즈 오류", e);
		}
		return result;
	}

}
