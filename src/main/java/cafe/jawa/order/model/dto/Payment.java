package cafe.jawa.order.model.dto;

import java.util.Objects;

public class Payment {
	
	private int paymentId;
	private int orderNum;
	private String paymentMethod;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int paymentId, int orderId, String paymentMethod) {
		super();
		this.paymentId = paymentId;
		this.orderNum = orderId;
		this.paymentMethod = paymentMethod;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", orderNum=" + orderNum + ", paymentMethod=" + paymentMethod + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderNum, paymentId, paymentMethod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return orderNum == other.orderNum && paymentId == other.paymentId
				&& Objects.equals(paymentMethod, other.paymentMethod);
	}
	
	
	

}
