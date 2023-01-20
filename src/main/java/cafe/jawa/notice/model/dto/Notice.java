package cafe.jawa.notice.model.dto;

public class Notice {

	private int id;
	private String title;
	private String content;
	private String notice_date;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(int no, String title, String content, String notice_date) {
		super();
		this.id = no;
		this.title = title;
		this.content = content;
		this.notice_date = notice_date;
	}

	public int getNo() {
		return id;
	}

	public void setNo(int no) {
		this.id = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	@Override
	public String toString() {
		return "Notice [no=" + id + ", title=" + title + ", content=" + content + ", notice_date=" + notice_date + "]";
	}
	
	
	
	
	
}
