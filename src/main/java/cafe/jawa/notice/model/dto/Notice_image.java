package cafe.jawa.notice.model.dto;

public class Notice_image {
		private int id;
		private int notice_number;
		private String original_filename;
		private String renamed_filename;
		private String enroll_date;
		
		public Notice_image() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Notice_image(int id, int notice_number, String original_filename, String renamed_filename,
				String enroll_date) {
			super();
			this.id = id;
			this.notice_number = notice_number;
			this.original_filename = original_filename;
			this.renamed_filename = renamed_filename;
			this.enroll_date = enroll_date;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getNotice_number() {
			return notice_number;
		}

		public void setNotice_number(int notice_number) {
			this.notice_number = notice_number;
		}

		public String getOriginal_filename() {
			return original_filename;
		}

		public void setOriginal_filename(String original_filename) {
			this.original_filename = original_filename;
		}

		public String getRenamed_filename() {
			return renamed_filename;
		}

		public void setRenamed_filename(String renamed_filename) {
			this.renamed_filename = renamed_filename;
		}

		public String getEnroll_date() {
			return enroll_date;
		}

		public void setEnroll_date(String enroll_date) {
			this.enroll_date = enroll_date;
		}

		@Override
		public String toString() {
			return "Notice_image [id=" + id + ", notice_number=" + notice_number + ", original_filename="
					+ original_filename + ", renamed_filename=" + renamed_filename + ", enroll_date=" + enroll_date
					+ "]";
		}
		
		
		
}
