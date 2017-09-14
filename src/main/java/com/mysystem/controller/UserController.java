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
//    		//����Զ�̶���ע��RMI·����ӿڱ��������������һ��
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
	
	
	//�û�ע��
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public String add(UserTrail userTrail){
		try{
		String name=userTrail.getUsername();
		UserTrail us=userTrailService.findByUsername(name);//�����û����Ƿ����
		if (us!=null){	//�û����Ѵ���		
			return "1";
		}
		else {
			userTrailService.register(userTrail);//�����û�������Ϣ
			return "2";	
		}		
		}
		catch (Exception e)
		{
			 e.printStackTrace();
			userTrailService.register(userTrail);//�����û�������Ϣ
			return "2";//���سɹ�
		}
	}
}
