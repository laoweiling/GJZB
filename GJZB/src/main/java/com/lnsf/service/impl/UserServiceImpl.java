package com.lnsf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.RelationDao;
import com.lnsf.dao.UserDao;
import com.lnsf.dao.WinDao;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.User;
import com.lnsf.service.UserService;

/**
 * @author 黄浩贡
 * @version 创建时间：2017年7月27日10:46:31
 * @introduction 增加修改用户操作的实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private WinDao winDao;
	@Autowired
	private RelationDao relationDao;
	@Autowired
	private ProjectDao projectDao;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	public int addOneUser(User user) {
		return userDao.addOneUser(user);
	}

	@Override
	public int updateUserName(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserNameByID(user);
	}

	@Override
	public int updateUserPw(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserPwByID(user);
	}

	@Override
	public int updateUserintroduc(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserIntroduByID(user);
	}

	@Override
	public int verifyEmail(String email) {
		return userDao.verifyEmail(email);
	}

	@Override
	public int verifyPhone(String phone) {
		return userDao.verifyPhone(phone);
	}

	@Override
	public User getUserByUserNameAndUserPwService(User user) {
		return userDao.getUserByUserNameAndUserPw(user);
	}

	@Override
	public int verifyUserName(String userName) {
		return userDao.verifyUserName(userName);
	}

	@Override
	public int saveNewUserPwByEmail(User user) {
		return userDao.saveNewUserPwByEmail(user);
	}

	@Override
	public int updateUserByAdmin(User user) {
		return userDao.updateUserByAdmin(user);
	}

	@Override
	public int updatePic(User user) {
		return userDao.updatePic(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<User> searchUser(User user) {
		return userDao.searchUser(user);
	}

	/**
	 * @author 黄卉 删除用户，有影响其他表
	 * @version 创建时间：2017年7月29日09:39:07
	 * @introduction 删除用户，有影响其他表
	 */
	@Override
	public int deleteUserById(String userIds) {
		String[] ids = userIds.split(",");
		int success = 0;
		for (String str : ids) {
			Integer userId = Integer.parseInt(str);
			// -------先查找是否有此信息----------
			// 去project表查询，找出projectID和userID,获取链表
			// relationDao表根据用户id删除：userId
			relationDao.deleteRelationByUserId(userId);

			// winDao表根据项目id删除："
			winDao.deleteWinByUserId(userId);

			// 根据userId去查找该用户发布过的标 --> 招标
			List<Project> projectList = projectDao.getProjectByUserId(userId);
			// 中标和投标表根据项目ID删除项目表
			for (int i = 0; i < projectList.size(); i++) {
				int projectId = projectList.get(i).getProjectId();
				// winDao表根据项目id删除
				winDao.deleteWinByProjectId(projectId);
				// relationDao表根据项目id删除
				relationDao.deleteRelationByProjectId(projectId);
				// projectDao表根据项目id删除
				projectDao.deleteByProjectId(projectId);
			}
			// 删完关联表之后，再去删用户表
			success = userDao.deleteUserById(userId);
		} // foreach
		return success;
	}

	@Override
	public List<User> getUserAllByPage(Page<User> page) {
		return userDao.getUserAllByPage(page);
	}

	@Override
	public int getCount() {
		return userDao.getCount();
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}

	@Override
	public List<User> getHotUser() {
		return userDao.getHotUser();
	}

}
