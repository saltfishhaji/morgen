package com.mysystem.dao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysystem.entity.User;
import com.mysystem.entity.UserPoint;

@Repository(value="userDao")
public interface UserDao extends Remote{
	public User findByUtid(Integer utid);//通过utid查找用户
	public List<User> findAll() throws RemoteException;
	public void add(User user);//添加账户信息
	public void updatePoint(UserPoint up);

}
