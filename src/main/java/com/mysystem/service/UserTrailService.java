package com.mysystem.service;

import java.util.List;


import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.UserTrail;
public interface UserTrailService {
	public UserTrail findByUtid(Integer utid);//通过uitd获取账户申请信息
	public UserTrail findByUsername(String username);//通过用户名查找账户
	public void register(UserTrail userTrail);//注册账户
	public List<UserTrail> findUnverified(PageModel<UserTrail> pageModel);//查找待审核的账户申请
	public Integer countUnverified(PageModel<UserTrail> pageModel);//计数
	void rejectUser(Integer uitd);//驳回账户申请
	void passUser(Integer uitd);//通过账户申请
	public List<UserTrail> findAll(PageModel<UserTrail> pageModel);//查找待审核的账户申请
	public Integer countAll(PageModel<UserTrail> pageModel);//计数
	public void editUser(UserTrail userTrail);
}
