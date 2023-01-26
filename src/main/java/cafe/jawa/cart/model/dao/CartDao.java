package cafe.jawa.cart.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CartDao {

	private Properties prop = new Properties();
	
	public CartDao() {
		String path = CartDao.class.getResource("/sql/cart/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
