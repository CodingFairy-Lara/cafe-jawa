package cafe.jawa.board.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * BoardEntity에 확장된 속성/기능을 추가하는 클래스
 *
 */
public class Board extends BoardEntity {
	
	private int attachCnt;
	private List<Attachment> attachments = new ArrayList<>();

	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(int no, String title, String writer, String content, int readCount, Date regDate, int attachCnt) {
		super(no, title, writer, content, readCount, regDate);
		this.attachCnt = attachCnt;
	}
	
	public Board(int no, String title, String writer, String content, int readCount, Date regDate, List<Attachment> attachments) {
		super(no, title, writer, content, readCount, regDate);
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
		return "Board [attachCnt=" + attachCnt + ", attachments=" + attachments + ", toString()=" + super.toString()
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
