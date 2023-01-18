package cafe.jawa.product.model.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 
 * DTO 
 * VO
 * Entity
 * 
 * - member객체 하나는 db member테이블의 한 행과 매칭.
 * - member필드는 db member테이블의 컬럼과 매칭(호환가능한 자료형)
 * 
 * - 기본생성자/전체파라미터생성자
 * - getter/setter
 * - toString
 *
 */
public class Product {
	
	private String productId;
	private String productName;
	private String description;
	private int price;
	private Timestamp enrollDate;
	private Activaion activation; // Y, N
	
	public Product() {
		super();
	}

	public Product(String productId, String productName, String description, int price, Timestamp enrollDate,
			Activaion activation) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.enrollDate = enrollDate;
		this.activation = activation;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Activaion getActivation() {
		return activation;
	}

	public void setActivation(Activaion activation) {
		this.activation = activation;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", enrollDate=" + enrollDate + ", activation=" + activation + "]";
	}
	
}

