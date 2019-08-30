package beans.missing.dao;

public class UserDAO {
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import beans.missing.vo.PetVO;
import iba.MySqlMapClient;

public class UserDAO {
	
	SqlMapClient smc;
	
	public UserDAO() {
		smc = MySqlMapClient.getSqlMapInstance();
	}
	
	public List<PetVO> pet_list () {
		
		List<PetVO> list = null;
		try {
			list = smc.queryForList("user.pet_list");
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  list;
	}
	
}
