package com.mysystem.service;

import java.util.List;


import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.UserTrail;
public interface UserTrailService {
	public UserTrail findByUtid(Integer utid);//ͨ��uitd��ȡ�˻�������Ϣ
	public UserTrail findByUsername(String username);//ͨ���û��������˻�
	public void register(UserTrail userTrail);//ע���˻�
	public List<UserTrail> findUnverified(PageModel<UserTrail> pageModel);//���Ҵ���˵��˻�����
	public Integer countUnverified(PageModel<UserTrail> pageModel);//����
	void rejectUser(Integer uitd);//�����˻�����
	void passUser(Integer uitd);//ͨ���˻�����
	public List<UserTrail> findAll(PageModel<UserTrail> pageModel);//���Ҵ���˵��˻�����
	public Integer countAll(PageModel<UserTrail> pageModel);//����
	public void editUser(UserTrail userTrail);
}
