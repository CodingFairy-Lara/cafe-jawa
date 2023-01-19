package cafe.jawa.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cafe.jawa.member.model.dto.Member;
import cafe.jawa.member.model.dto.MemberRole;
import cafe.jawa.member.model.dto.Withdrawal;
import cafe.jawa.member.model.exception.MemberException;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String path = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Connection conn, Member member) {
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setDate(6, member.getBirthday());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류!", e);
		}
		return result;
	}

	public Member selectOneMember(Connection conn, String memberId) {
		String sql = prop.getProperty("selectOneMember");
		Member member = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					member = handleMemberResultSet(rset);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString("id"));
		member.setPassword(rset.getString("password"));
		member.setMemberName(rset.getString("name"));
		member.setMemberRole(MemberRole.valueOf(rset.getString("memberRole")));
		member.setBirthday(rset.getDate("birthday"));
		member.setEmail(rset.getString("email"));
		member.setPhone(rset.getString("Phone"));
		member.setEnrollDate(rset.getTimestamp("enroll_date"));
		member.setOrderCount(rset.getInt("order_count"));
		member.setWithdrawal(Withdrawal.valueOf(rset.getString("withdrawal")));
		return member;
	}

	public int updateMember(Connection conn, Member member) {
		String sql = prop.getProperty("updateMember");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getMemberName());
			pstmt.setDate(2, member.getBirthday());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원정보 수정 오류", e);
		}
		return result;
	}

	public int updatePassword(Connection conn, Member loginMember) {
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, loginMember.getPassword());
			pstmt.setString(2, loginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("비밀번호 수정 오류", e);
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		String sql = prop.getProperty("deleteMember");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원탈퇴 오류", e);
		}
		return result;
	}

}
