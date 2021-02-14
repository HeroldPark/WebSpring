package com.star.blue.user;

import java.util.List;
import java.util.Map;

import com.star.blue.user.UserVO;

public interface UserService {
    public List<UserVO> selectUser(Map<String, Object> params) throws Exception;
}