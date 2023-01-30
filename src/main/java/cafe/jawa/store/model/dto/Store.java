package cafe.jawa.store.model.dto;

import java.util.Objects;

public class Store {
	
	private String store_id;
	private String store_name;
	private String address;
	private String phone;
	private StoreStatus status;
	
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Store(String store_id, String store_name, String address, String phone, StoreStatus status) {
		super();
		this.store_id = store_id;
		this.store_name = store_name;
		this.address = address;
		this.phone = phone;
		this.status = status;
	}
	
	public String getStore_id() {
		return store_id;
	}
	
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	
	public String getStore_name() {
		return store_name;
	}
	
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public StoreStatus getStatus() {
		return status;
	}
	
	public void setStatus(StoreStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Store [store_id=" + store_id + ", store_name=" + store_name + ", address=" + address + ", phone="
				+ phone + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, phone, status, store_id, store_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(address, other.address) && Objects.equals(phone, other.phone) && status == other.status
				&& Objects.equals(store_id, other.store_id) && Objects.equals(store_name, other.store_name);
	}
	
}
