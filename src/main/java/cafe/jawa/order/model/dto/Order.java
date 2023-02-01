package cafe.jawa.order.model.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author hera
 *
 */
public class Order {

    // DB로부터 받을 값
	private int orderNum;
	private int orderId;
	private String memberId;
	private String storeId;
	private int status;
	private Timestamp orderDate;
	private int totalPrice;
	private String payment_method;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderNum, int orderId, String memberId, String storeId, int status, Timestamp orderDate, int totalPrice,
			String payment_method) {
		super();
		this.orderNum = orderNum;
		this.orderId = orderId;
		this.memberId = memberId;
		this.storeId = storeId;
		this.status = status;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.payment_method = payment_method;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", orderId=" + orderId + ", memberId=" + memberId + ", storeId="
				+ storeId + ", status=" + status + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice
				+ ", payment_method=" + payment_method + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(memberId, orderDate, orderId, orderNum, payment_method, status, storeId, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(memberId, other.memberId) && Objects.equals(orderDate, other.orderDate)
				&& orderId == other.orderId && orderNum == other.orderNum
				&& Objects.equals(payment_method, other.payment_method) && status == other.status
				&& Objects.equals(storeId, other.storeId) && totalPrice == other.totalPrice;
	}

	
	
	
}
