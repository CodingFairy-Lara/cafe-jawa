package cafe.jawa.order.model.dto;

import java.sql.Date;
import java.util.Objects;

/**
 * @author hera
 *
 */
public class Order {

	// view단에서 받아올 값
    private int op_id;
    private int quantity;
    private int tot_price;
    // DB로부터 받을 값
	private int orderId;
	private String memberId;
	private String storeId;
	private int status;
	private Date orderDate;
	private int totalPrice;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int op_id, int quantity, int tot_price, int orderId, String memberId, String storeId, int status,
			Date orderDate, int totalPrice) {
		super();
		this.op_id = op_id;
		this.quantity = quantity;
		this.tot_price = tot_price;
		this.orderId = orderId;
		this.memberId = memberId;
		this.storeId = storeId;
		this.status = status;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
	}

	public int getOp_id() {
		return op_id;
	}

	public void setOp_id(int op_id) {
		this.op_id = op_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTot_price() {
		return tot_price;
	}

	public void setTot_price(int tot_price) {
		this.tot_price = tot_price;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [op_id=" + op_id + ", quantity=" + quantity + ", tot_price=" + tot_price + ", orderId=" + orderId
				+ ", memberId=" + memberId + ", storeId=" + storeId + ", status=" + status + ", orderDate=" + orderDate
				+ ", totalPrice=" + totalPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(memberId, op_id, orderDate, orderId, quantity, status, storeId, tot_price, totalPrice);
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
		return Objects.equals(memberId, other.memberId) && op_id == other.op_id
				&& Objects.equals(orderDate, other.orderDate) && orderId == other.orderId && quantity == other.quantity
				&& status == other.status && Objects.equals(storeId, other.storeId) && tot_price == other.tot_price
				&& totalPrice == other.totalPrice;
	}

	
	
	
}
