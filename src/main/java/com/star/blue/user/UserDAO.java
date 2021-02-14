package com.star.blue.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import com.star.blue.user.UserVO;
 
@Repository("userDAO")
public class UserDAO {
 
    @Autowired
    private SqlSession sqlSession;
    private String Namespace = "com.star.blue.user.userMapper";
         
	public List<UserVO> selectUser(Map<String, Object> params) throws Exception {
		//return sqlSession.selectList(Namespace+".selectUser");

		params.put("QueryString", "SELECT host, user, select_priv FROM user");
		return sqlSession.selectList(Namespace+".selectUser", params);
	}
}