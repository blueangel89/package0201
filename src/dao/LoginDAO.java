package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static db.JdbcUtil.*;
import vo.Member;

//로그인과 관련된 데이터베이스 연동부분 처리
public class LoginDAO {

	private static LoginDAO loginDAO;
	private Connection con;
	
	//기본생성자
	private LoginDAO() {}

	public static LoginDAO getInstance() {
		//싱글톤 패턴을 구현하기 위해 
		//최초 인스턴스가 없는경우 한번만 인스턴스 생성
		if(loginDAO == null) {
			loginDAO = new LoginDAO();
		}
		
		return loginDAO;
	}
	
	//데이터베이스 연결설정
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public Member selectLoginMember(String id,String passwd) {
		
		Member loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from users where id = ? and passwd = ?";
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,id);
			pstmt.setString(2,passwd);
			
			rs = pstmt.executeQuery();
			
			//입력폼에서 입력받는 아이디와 비밀번호를 가진 멤버가 테이블에
			//있을 경우
			if(rs.next()) {
				
				loginMember = new Member();
				
				loginMember.setAddr(rs.getString("addr"));
				loginMember.setAge(rs.getInt("age"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setGender(rs.getString("gender"));
				loginMember.setId(rs.getString("id"));
				loginMember.setName(rs.getString("name"));
				loginMember.setNation(rs.getString("nation"));
				loginMember.setPasswd(rs.getString("passwd"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return loginMember;
	}
	
}