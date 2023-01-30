package cafe.jawa.order.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import cafe.jawa.cart.model.dao.CartDao;

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

	
	
	
	
}
