package com.mysystem.dao;

import java.util.List;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.UserTrail;

public interface UserTrailDao {
	UserTrail findByUsername(String username);//通过用户名查找账户
	UserTrail findByUtid(Integer uitd);//通过uitd获取账户申请信息
	void register(UserTrail userTrail);//注册账户
	List<UserTrail> findUnverified(PageModel<UserTrail> pageModel);//查找待审核的账户申请
	Integer countUnverified(PageModel<UserTrail> pageModel);//计数
	void rejectUser(Integer uitd);//驳回账户申请
	void passUser(Integer uitd);//通过账户申请
	
	List<UserTrail> findAll(PageModel<UserTrail> pageModel);//查找待审核的账户申请
	Integer countAll(PageModel<UserTrail> pageModel);//计数
	public void editUser(UserTrail userTrail);
}
