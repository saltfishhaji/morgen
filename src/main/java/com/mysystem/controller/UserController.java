package com.mysystem.controller;
import java.util.List;
import java.rmi.Naming;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysystem.entity.User;
import com.mysystem.entity.UserTrail;
import com.mysystem.service.UserService;
import com.mysystem.service.UserTrailService;
@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserTrailService userTrailService;

	@RequestMapping(value="/userList")
	public ModelAndView userList(){

		ModelAndView mv=new ModelAndView();	
		
		mv.setViewName("userList");
//    	try{
//    		//调用远程对象，注意RMI路径与接口必须与服务器配置一致
//    		//UserService userService=(UserService)Naming.lookup("rmi://127.0.0.1:6500/UserService");
//    		List<User> userList=userService.findAll();
//    		mv.addObject("userList",userList);
//    		
//    		return mv;
//    	}catch(Exception ex){
//    		ex.printStackTrace();
//    	}
    	return mv;
	}
	
	
	//用户注册
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public String add(UserTrail userTrail){
		try{
		String name=userTrail.getUsername();
		UserTrail us=userTrailService.findByUsername(name);//查找用户名是否存在
		if (us!=null){	//用户名已存在		
			return "1";
		}
		else {
			userTrailService.register(userTrail);//插入用户申请信息
			return "2";	
		}		
		}
		catch (Exception e)
		{
			 e.printStackTrace();
			userTrailService.register(userTrail);//插入用户申请信息
			return "2";//返回成功
		}
	}
}
