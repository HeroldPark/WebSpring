package com.star.blue.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;
 
import com.star.blue.user.UserVO;
import com.star.blue.user.UserDAO;
 
@Service("userService")
public class UserServiceImpl implements UserService {
  
    @Resource(name="userDAO")
    private UserDAO userDAO;
     
    public List<UserVO> selectUser(Map<String, Object> params) throws Exception {
        return userDAO.selectUser(params);
    }
}