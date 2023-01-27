package cafe.jawa.cart.model.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import cafe.jawa.product.model.dto.Activation;
import cafe.jawa.product.model.dto.Attachment;
import cafe.jawa.product.model.dto.OrderedProduct;

public class Cart extends OrderedProduct{
	
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
	
	public Cart(int orderedProductId, int orderId, int productId, int quantity, String cup, String cupSize,
			String memberId) {
		super(orderedProductId, orderId, productId, quantity, cup, cupSize, memberId);
	}

	public Cart(int productId, int quantity, String cup, String cupSize, String memberId) {
		super(productId, quantity, cup, cupSize, memberId);
	}

	public Cart(int productId, int quantity, String memberId) {
		super(productId, quantity, memberId);
	}
	
	public Cart(String memberId, int orderedProductId, int quantity) {
		super(memberId, orderedProductId, quantity);
	}

	public Cart(int productId, String subCategory, String productName, String description, int price,
			Timestamp enrollDate, Activation activation, String factor) {
		super(productId, subCategory, productName, description, price, enrollDate, activation, factor);
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
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cartId, memberId, orderedProductId, quantity);
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
		Cart other = (Cart) obj;
		return cartId == other.cartId && Objects.equals(memberId, other.memberId)
				&& orderedProductId == other.orderedProductId && quantity == other.quantity;
	}

	
}
