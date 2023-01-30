package cafe.jawa.order.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderPage extends ArrayList{

	private List<Order> orders;

	public OrderPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderPage(List<Order> orders) {
		super();
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrderPage [orders=" + orders + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orders);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderPage other = (OrderPage) obj;
		return Objects.equals(orders, other.orders);
	}
	
	
	
}
