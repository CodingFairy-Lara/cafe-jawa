package cafe.jawa.product.model.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class OrderedProduct extends Product{

	private int orderedProductId;
	private int orderId;
	private int productId;
	private int quantity;
	private String cup;
	private String cupSize;
	private String memberId;
	
	public OrderedProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderedProduct(int attachCnt, List<Attachment> attachments) {
		super(attachCnt, attachments);
		// TODO Auto-generated constructor stub
	}
	
	public OrderedProduct(int productId, String subCategory, String productName, String description, int price,
			Timestamp enrollDate, Activation activation, String factor) {
		super(productId, subCategory, productName, description, price, enrollDate, activation, factor);
		// TODO Auto-generated constructor stub
	}
	
	
	public OrderedProduct(int orderedProductId, int orderId, int productId, int quantity, String cup, String cupSize,
			String memberId) {
		super();
		this.orderedProductId = orderedProductId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.cup = cup;
		this.cupSize = cupSize;
		this.memberId = memberId;
	}

	
	// dirnk
	public OrderedProduct(int productId, int quantity, String cup, String cupSize, String memberId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.cup = cup;
		this.cupSize = cupSize;
		this.memberId = memberId;
	}
	
	// food
	public OrderedProduct(int productId, int quantity, String memberId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.memberId = memberId;
	}
	
	public int getOrderedProductId() {
		return orderedProductId;
	}
	public void setOrderedProductId(int orderedProductId) {
		this.orderedProductId = orderedProductId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCup() {
		return cup;
	}
	public void setCup(String cup) {
		this.cup = cup;
	}
	public String getCupSize() {
		return cupSize;
	}
	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "OrderedProduct [orderedProductId=" + orderedProductId + ", orderId=" + orderId + ", productId="
				+ productId + ", quantity=" + quantity + ", cup=" + cup + ", cupSize=" + cupSize + ", memberId="
				+ memberId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cup, cupSize, orderId, orderedProductId, productId, quantity);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedProduct other = (OrderedProduct) obj;
		return Objects.equals(cup, other.cup) && Objects.equals(cupSize, other.cupSize) && orderId == other.orderId
				&& orderedProductId == other.orderedProductId && productId == other.productId
				&& quantity == other.quantity;
	}
	
	
	
	
}
