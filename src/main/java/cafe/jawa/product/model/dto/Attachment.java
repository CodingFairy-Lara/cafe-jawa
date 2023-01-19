package cafe.jawa.product.model.dto;

import java.sql.Date;
import java.util.Objects;

public class Attachment {
	private int id;
	private int productId;
	private String originalFilename;
	private String renamedFilename;
	private Date enrollDate;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(int id, int productId, String originalFilename, String renamedFilename, Date enrollDate) {
		super();
		this.id = id;
		this.productId = productId;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.enrollDate = enrollDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getRenamedFilename() {
		return renamedFilename;
	}

	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", product_id=" + productId + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", enrollDate=" + enrollDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(enrollDate, id, originalFilename, productId, renamedFilename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attachment other = (Attachment) obj;
		return Objects.equals(enrollDate, other.enrollDate) && id == other.id
				&& Objects.equals(originalFilename, other.originalFilename) && productId == other.productId
				&& Objects.equals(renamedFilename, other.renamedFilename);
	}
	
}
