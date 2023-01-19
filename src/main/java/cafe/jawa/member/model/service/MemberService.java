package cafe.jawa.member.model.service;

import static cafe.jawa.common.JdbcTemplate.close;
import static cafe.jawa.common.JdbcTemplate.commit;
import static cafe.jawa.common.JdbcTemplate.getConnection;
import static cafe.jawa.common.JdbcTemplate.rollback;

import java.sql.Connection;

import cafe.jawa.member.model.dao.MemberDao;
import cafe.jawa.member.model.dto.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Member selectOneMember(String memberId) {
		Connection conn = getConnection();
		
		Member member = memberDao.selectOneMember(conn, memberId);
		
		close(conn);
		return member;
	}

	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePassword(Member loginMember) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updatePassword(conn, loginMember);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteMember(conn,memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

}
