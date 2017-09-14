package com.mysystem.service;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import org.springframework.stereotype.Service;

import com.mysystem.entity.User;
import com.mysystem.entity.UserPoint;
public interface UserService extends Remote {
	public List<User> findAll() throws RemoteException;
	public User findByUtid(Integer utid);//通过utid查找用户
	public void add(User user);//添加账户信息
	public void updatePoint(UserPoint up);
}
