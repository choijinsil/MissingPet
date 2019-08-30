package beans.missing.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import beans.missing.vo.PetVO;
import beans.missing.vo.UserVO;
import iba.MySqlMapClient;

public class UserDAO {
	SqlMapClient sqlMap; 
	
	public UserDAO() {
		sqlMap = MySqlMapClient.getSqlMapInstance();
	}


//<!--MYPAGE.JSP 회원정보조회 -->
	public List<UserVO> select_myinfo(String id) {
			try {
				return sqlMap.queryForList("pet.select_myinfo", id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	return null;
			}


//<!--MYPAGE.JSP 회원MISSING정보조회 -->	
	public List<PetVO> select_mymissing(String id) {
			try { 
				return (List<PetVO>) sqlMap.queryForList("pet.select_mymissing", id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} return null;
			}


//<!--MYPAGE.JSP 회원정보수정 -->	
	public boolean update_myinfo(UserVO user){
		try {
			if(sqlMap.update("pet.update_myinfo", user)==1) 
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false; 
			}
	
	
//<!--MYPAGE.JSP 회원MISSING귀가처리 -->
	public boolean update_mymissing(int missing_no) {
		try {
			if(sqlMap.update("pet.update_mymissing", missing_no)!=0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
		}
	


}
	

