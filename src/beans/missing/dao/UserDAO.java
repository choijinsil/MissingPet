package beans.missing.dao;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import beans.missing.vo.UserVO;
import iba.MySqlMapClient;

public class UserDAO {
	SqlMapClient smc;

	public UserDAO() {
		smc = MySqlMapClient.getSqlMapInstance();
	}

	public boolean insert_user(UserVO vo) { // 회원 가입
		try {
			smc.insert("user.insert_user", vo);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean select_user(Map<String, String> map) { // 로그인시 회원조회
		try {
			int t = (Integer) smc.queryForObject("user.select_user", map);
			if (t == 1) {
				System.out.println("dao성공 들어왔다.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("map>>" + map.get("id") + "," + map.get("pass"));
		return false;
	}

}
