package svc;

import java.sql.Connection;

import dao.LoginDAO;
import vo.Member;
import static db.JdbcUtil.*;

public class LoginService {

	public Member getLoginMember(String id,String passwd) {
		
		//싱글톤 패턴
		//최초에 인스턴스가 생성되어 있지 않으면 한번만 힙메모리에 생성하고
		//이후부터는 공유해서 사용한다.(메모리 낭비를 방지한다).
		LoginDAO loginDAO = LoginDAO.getInstance();
		
		//데이터베이스 연결 선언
		Connection con = getConnection();

		//데이터베이스 연결
		loginDAO.setConnection(con);
		
		Member loginMember = loginDAO.selectLoginMember(id,passwd);
		
		close(con);
		
		return loginMember;
		
	}
}