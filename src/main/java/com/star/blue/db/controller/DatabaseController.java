package com.star.blue.db.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

//import javax.annotation.Resource;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.star.blue.user.UserService;
import com.star.blue.user.UserVO;

@Controller
public class DatabaseController{
     
	private static final Logger logger =  LoggerFactory.getLogger(DatabaseController.class);
     
    @Resource(name="userService")
    private UserService service;
     
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws Exception{
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("USER", "root");
    	
        logger.info("db");
 
        List<UserVO> userList = service.selectUser(params);
        
        logger.debug("db : " + userList);
        
        model.addAttribute("userList", userList);
 
        return "db";
    }
 
}