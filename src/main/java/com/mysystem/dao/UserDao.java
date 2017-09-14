package com.mysystem.dao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysystem.entity.User;
import com.mysystem.entity.UserPoint;

@Repository(value="userDao")
public interface UserDao extends Remote{
	public User findByUtid(Integer utid);//ͨ��utid�����û�
	public List<User> findAll() throws RemoteException;
	public void add(User user);//����˻���Ϣ
	public void updatePoint(UserPoint up);

}
