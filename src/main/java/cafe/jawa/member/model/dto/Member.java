package cafe.jawa.member.model.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 
 * DTO 
 * VO
 * Entity
 * 
 * - member객체 하나는 db member테이블의 한 행과 매칭.
 * - member필드는 db member테이블의 컬럼과 매칭(호환가능한 자료형)
 * 
 * - 기본생성자/전체파라미터생성자
 * - getter/setter
 * - toString
 *
 */
public class Member {
	
	private String memberId;
	private String password;
	private String memberName;
	private MemberRole memberRole;
	private Date birthday;
	private String email;
	private String phone;
	private Timestamp enrollDate;
	private int orderCount;

	
	
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String memberId, String password, String memberName, MemberRole memberRole, Date birthday,
			String email, String phone, Timestamp enrollDate, int orderCount) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.memberRole = memberRole;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.orderCount = orderCount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName
				+ ", memberRole=" + memberRole + ", birthday=" + birthday + ", email=" + email + ", phone=" + phone
				+ ", enrollDate=" + enrollDate + ", orderCount=" + orderCount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, email, enrollDate, memberId, memberName, memberRole, orderCount, password, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(email, other.email)
				&& Objects.equals(enrollDate, other.enrollDate) && Objects.equals(memberId, other.memberId)
				&& Objects.equals(memberName, other.memberName) && memberRole == other.memberRole
				&& orderCount == other.orderCount && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone);
	}	
	
}

