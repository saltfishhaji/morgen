package com.mysystem.dao;

import java.util.List;

import com.mysystem.PageModel.PageModel;
import com.mysystem.entity.UserTrail;

public interface UserTrailDao {
	UserTrail findByUsername(String username);//ͨ���û��������˻�
	UserTrail findByUtid(Integer uitd);//ͨ��uitd��ȡ�˻�������Ϣ
	void register(UserTrail userTrail);//ע���˻�
	List<UserTrail> findUnverified(PageModel<UserTrail> pageModel);//���Ҵ���˵��˻�����
	Integer countUnverified(PageModel<UserTrail> pageModel);//����
	void rejectUser(Integer uitd);//�����˻�����
	void passUser(Integer uitd);//ͨ���˻�����
	
	List<UserTrail> findAll(PageModel<UserTrail> pageModel);//���Ҵ���˵��˻�����
	Integer countAll(PageModel<UserTrail> pageModel);//����
	public void editUser(UserTrail userTrail);
}
