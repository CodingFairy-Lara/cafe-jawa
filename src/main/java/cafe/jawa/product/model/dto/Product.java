package cafe.jawa.product.model.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * ProductEntity 클래스에 첨부파일기능 추가
 *
 */
public class Product extends ProductEntity{
	
	private int attachCnt;
	private List<Attachment> attachments = new ArrayList<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String subCategory, String productName, String description, int price,
			Timestamp enrollDate, Activaion activation) {
		super(productId, subCategory, productName, description, price, enrollDate, activation);
		// TODO Auto-generated constructor stub
	}
	
	public Product(int attachCnt, List<cafe.jawa.product.model.dto.Attachment> attachments) {
		super();
		this.attachCnt = attachCnt;
		this.attachments = attachments;
	}

	public int getAttachCnt() {
		return attachCnt;
	}

	public void setAttachCnt(int attachCnt) {
		this.attachCnt = attachCnt;
	}
	
	

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Attachment [attachCnt=" + attachCnt + ", attachments=" + attachments + ", toString()=" + super.toString()
				+ "]";
	}

	/**
	 * boar객체 생성시 초기화된 attachment에 Attachment객체를 추가하는 메소드
	 * 
	 * @author honngd
	 * @param attach
	 */
	public void addAttachment(Attachment attach) {
		this.attachments.add(attach);
	}
}

	
	


