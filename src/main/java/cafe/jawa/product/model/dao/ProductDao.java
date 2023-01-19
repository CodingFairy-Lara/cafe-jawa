package cafe.jawa.product.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import cafe.jawa.member.model.dao.MemberDao;

public class ProductDao {
	private Properties prop = new Properties();
	
	public ProductDao() {
		String path = MemberDao.class.getResource("/sql/product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
