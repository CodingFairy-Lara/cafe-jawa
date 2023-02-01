package cafe.jawa.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cafe.jawa.board.model.dto.Attachment;
import cafe.jawa.board.model.dto.Board;
import cafe.jawa.board.model.exception.BoardException;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		String path = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("BoardDao 쿼리 로드 완료! - " + prop);
	}

	public List<Board> selectBoardList(Connection conn, Map<String, Object> param) {
		String sql = prop.getProperty("selectBoardList");
		List<Board> boardList = new ArrayList<>();
		
		int page = (int) param.get("page"); 
		int limit = (int) param.get("limit"); // 5
		
		int start = (page - 1) * limit + 1; // 1, 6, 11, 16, ... 
		int end = page * limit; // 5, 10, 15, 20, ...
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			try(ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					Board board = handleBoardResultSet(rset);
					board.setAttachCnt(rset.getInt("attach_cnt"));
					boardList.add(board);					
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException("게시글 목록 조회 오류!", e);
		}
		
		return boardList;
	}

	private Board handleBoardResultSet(ResultSet rset) throws SQLException {
		Board board = new Board();
		board.setNo(rset.getInt("no"));
		board.setTitle(rset.getString("title"));
		board.setWriter(rset.getString("writer"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_count"));
		board.setRegDate(rset.getDate("reg_date"));
		return board;
	}

	public int selectTotalCount(Connection conn) {
		String sql = prop.getProperty("selectTotalCount");
		int totalCount = 0;
		
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
		){
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new BoardException("전체 게시글수 조회 오류!", e);
		}
		return totalCount;
	}

	public int insertBoard(Connection conn, Board board) {
		String sql = prop.getProperty("insertBoard");
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시글 등록 오류!", e);
		}
		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		String sql = prop.getProperty("selectLastBoardNo");
		int boardNo = 0;
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
		){
			if(rset.next())
				boardNo = rset.getInt(1);
			
		} catch (SQLException e) {
			throw new BoardException("게시글번호 조회 오류!", e);
		}
		
		return boardNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		String sql = prop.getProperty("insertAttachment");
		// insert into attachment(no, board_no, original_filename, renamed_filename) values (seq_attachment_no.nextval, ?, ?, ?)
		int result = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new BoardException("첨부파일 등록 오류!", e);
		}
		
		return result;
	}

	public Board selectOneBoard(Connection conn, int no) {
		String sql = prop.getProperty("selectOneBoard");
		Board board = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					board = handleBoardResultSet(rset);
				}
			}
			
		} catch (Exception e) {
			throw new BoardException("게시글 한건 조회 오류!", e);
		}
		
		return board;
	}

	public List<Attachment> selectAttachmentByBoardNo(Connection conn, int boardNo) {
		String sql = prop.getProperty("selectAttachmentByBoardNo"); // select * from attachment where board_no = ?
		List<Attachment> attachments = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, boardNo);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Attachment attach = handleAttachmentResultSet(rset);
					attachments.add(attach);
				}
			}
			
		} catch (Exception e) {
			throw new BoardException("게시글 한건 조회 오류!", e);
		}
		
		return attachments;
	}

	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		attach.setNo(rset.getInt("no"));
		attach.setBoardNo(rset.getInt("board_no"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		attach.setRegDate(rset.getDate("reg_date"));
		return attach;
	}

	public int updateReadCount(Connection conn, int no) {
		String sql = prop.getProperty("updateReadCount"); // update board set read_count = read_count + 1 where no = ?
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("조회수 증가 오류!", e);
		}
		return result;
	}

	public Attachment selectOneAttachment(Connection conn, int no) {
		String sql = prop.getProperty("selectOneAttachment");
		Attachment attach = null;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					attach = handleAttachmentResultSet(rset);
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException("첨부파일 한건 조회 오류", e);
		}
		
		return attach;
	}

	public int deleteBoard(Connection conn, int no) {
		String sql = prop.getProperty("deleteBoard"); // delete from board where no = ?
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("게시글 삭제 오류!", e);
		}
		
		return result;
	}
	

	public int updateBoard(Connection conn, Board b) {
		String sql = prop.getProperty("updateBoard");
		int result = 0;

		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 수정 오류!", e);
		}
		return result;
	}

	public int deleteAttachment(Connection conn, int attachNo) {
		String sql = prop.getProperty("deleteAttachment");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			// 미완성 쿼리 값대입
			pstmt.setInt(1, attachNo);
			// 실행
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			throw new BoardException("첨부파일 삭제 오류!", e);
		}
		
		return result;
	}
	
	public int deleteBoardComment(Connection conn, int no) {
		String sql = prop.getProperty("deleteBoardComment");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
//			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("댓글 삭제 오류", e);
		} 
		return result;
	}

}




