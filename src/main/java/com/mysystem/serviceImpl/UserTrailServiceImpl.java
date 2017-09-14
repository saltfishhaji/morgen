package com.mysystem.serviceImpl;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysystem.PageModel.PageModel;
import com.mysystem.dao.UserTrailDao;
import com.mysystem.entity.UserTrail;
import com.mysystem.service.UserTrailService;

//�˻�������Ϣ�����ʵ��
@Service(value="userTrailService")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserTrailServiceImpl implements UserTrailService{
	@Autowired
	private UserTrailDao userTrailDao;
	public UserTrail findByUsername(String username){
		return userTrailDao.findByUsername(username);
	}
	//ͨ��uitd��ȡ�˻�������Ϣ
	public UserTrail findByUtid(Integer utid){
		return userTrailDao.findByUtid(utid);
	}

	//ע���˻�
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void register(UserTrail userTrail){
		userTrailDao.register(userTrail);
	}
	//���Ҵ���˵��˻�����
	public List<UserTrail> findUnverified(PageModel<UserTrail> pageModel){
		return userTrailDao.findUnverified(pageModel);
	}
	
	//����
	public Integer countUnverified(PageModel<UserTrail> pageModel){
		return userTrailDao.countUnverified(pageModel);
	}

	
	//�����˻�����
	public void rejectUser(Integer utid){
		userTrailDao.rejectUser(utid);
	}
	
	//ͨ���˻�����
	public void passUser(Integer utid){
		userTrailDao.passUser(utid);
	}
	
	public List<UserTrail> findAll(PageModel<UserTrail> pageModel){
		return userTrailDao.findAll(pageModel);
	}
	
	//����
	public Integer countAll(PageModel<UserTrail> pageModel){
		return userTrailDao.countAll(pageModel);
	}
	
	public void editUser(UserTrail userTrail)
	{
		userTrailDao.editUser(userTrail);
	}
}
