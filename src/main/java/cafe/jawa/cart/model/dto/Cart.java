package cafe.jawa.cart.model.dto;

import java.util.Objects;

public class Cart {
	
	private int cartId;
	private String memberId;
	private int orderedProductId;
	private int quantity;
	
	public Cart() {
		super();
	}

	public Cart(int cartId, String memberId, int orderedProductId, int quantity) {
		super();
		this.cartId = cartId;
		this.memberId = memberId;
		this.orderedProductId = orderedProductId;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getOrderedProductId() {
		return orderedProductId;
	}

	public void setOrderedProductId(int orderedProductId) {
		this.orderedProductId = orderedProductId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", memberId=" + memberId + ", orderedProductId=" + orderedProductId
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, memberId, orderedProductId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return cartId == other.cartId && Objects.equals(memberId, other.memberId)
				&& orderedProductId == other.orderedProductId && quantity == other.quantity;
	}
	
	
	
}
