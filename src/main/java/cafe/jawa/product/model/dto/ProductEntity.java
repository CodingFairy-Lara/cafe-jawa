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
public class ProductEntity {
	
	private int productId;
	private String subCategory;
	private String productName;
	private String description;
	private int price;
	private Timestamp enrollDate;
	private Activaion activation; // Y, N
	
	public ProductEntity() {
		super();
	}

	public ProductEntity(int productId, String subCategory, String productName, String description, int price,
			Timestamp enrollDate, Activaion activation) {
		super();
		this.productId = productId;
		this.subCategory = subCategory;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.enrollDate = enrollDate;
		this.activation = activation;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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
		return "Product [productId=" + productId + ", subCategory=" + subCategory + ", productName=" + productName
				+ ", description=" + description + ", price=" + price + ", enrollDate=" + enrollDate + ", activation="
				+ activation + "]";
	}
	
}

