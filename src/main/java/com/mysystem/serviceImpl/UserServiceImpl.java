package com.mysystem.serviceImpl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import com.mysystem.dao.UserDao;
import com.mysystem.entity.User;
import com.mysystem.entity.UserPoint;
import com.mysystem.service.UserService;
@Service(value="userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	public void updatePoint(UserPoint up)
	{
		userDao.updatePoint(up);
	}

	

	//通过utid查找用户
	public User findByUtid(Integer utid) {
		return userDao.findByUtid(utid);
	}
	//添加账户信息
	public void add(User user){
		userDao.add(user);
	}
	
	
	public List<User> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		List<User> userList=new LinkedList<User>();
		User person1=new User();

		userList=userDao.findAll();
//
		
//		person1.setUserId(0);
//		person1.setUserName("A");
//		person1.setPassword("123456");
//		userList.add(person1);
//		System.out.println(userList);


		return userList;
	}

}