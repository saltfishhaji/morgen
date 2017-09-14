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

//账户申请信息服务层实现
@Service(value="userTrailService")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserTrailServiceImpl implements UserTrailService{
	@Autowired
	private UserTrailDao userTrailDao;
	public UserTrail findByUsername(String username){
		return userTrailDao.findByUsername(username);
	}
	//通过uitd获取账户申请信息
	public UserTrail findByUtid(Integer utid){
		return userTrailDao.findByUtid(utid);
	}

	//注册账户
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void register(UserTrail userTrail){
		userTrailDao.register(userTrail);
	}
	//查找待审核的账户申请
	public List<UserTrail> findUnverified(PageModel<UserTrail> pageModel){
		return userTrailDao.findUnverified(pageModel);
	}
	
	//计数
	public Integer countUnverified(PageModel<UserTrail> pageModel){
		return userTrailDao.countUnverified(pageModel);
	}

	
	//驳回账户申请
	public void rejectUser(Integer utid){
		userTrailDao.rejectUser(utid);
	}
	
	//通过账户申请
	public void passUser(Integer utid){
		userTrailDao.passUser(utid);
	}
	
	public List<UserTrail> findAll(PageModel<UserTrail> pageModel){
		return userTrailDao.findAll(pageModel);
	}
	
	//计数
	public Integer countAll(PageModel<UserTrail> pageModel){
		return userTrailDao.countAll(pageModel);
	}
	
	public void editUser(UserTrail userTrail)
	{
		userTrailDao.editUser(userTrail);
	}
}
