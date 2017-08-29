package com.lnsf.service;

import java.util.List;

import com.lnsf.entity.Page;
import com.lnsf.entity.User;

/**
 * @author 劳伟玲
 * @version 创建时间：2017年7月26日21:29:22
 * @introduction 创建用户业务层接口UserService
 */

public interface UserService {
	// 查询全部用户信息
	List<User> getAllUsers();

	// 按用户id查询单个用户信息
	User getUserById(Integer userId);

	// 修改用户名字，密码，个人简介
	public int updateUserName(User user);

	public int updateUserPw(User user);

	public int updateUserintroduc(User user);

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月27日11:01:12
	 * @introduction 添加用户业务逻辑
	 */
	int addOneUser(User user);
	
	/**
	* @author 黄卉  
	* @version 创建时间：2017年7月27日11:01:12
	* @introduction  普通用户修改
	*/
	public int updateUser(User user);
	
	/**
	 * 用于首页的报表，展示用戶的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public int getUserCount();
	
	/**
	 * 实现用户的分页查询
	 * 
	 * @author 梁肖萍
	 * @param page
	 * @return
	 */
	public List<User> getUserAllByPage(Page<User> page);
	
	/**
	 * 分页查询的总数
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public int getCount();
	
	/**
	 * 实现用户的模糊查询
	 * @author 梁肖萍
	 * @param user
	 * @return
	 */
	public List<User> searchUser(User user);

	/**
	 * 实现管理员对用户的修改
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @return
	 */
	public int updateUserByAdmin(User user);

	/**
	 * 校验Email是否被注册过
	 * 
	 * @author 梁肖萍
	 * @param email
	 * @return
	 */
	public int verifyEmail(String email);

	/**
	 * 校验Phone是否被注册过
	 * 
	 * @author 梁肖萍
	 * @param phone
	 * @return
	 */
	public int verifyPhone(String phone);

	/**
	 * 校验用户名是否以及存在
	 * 
	 * @author 梁肖萍
	 * @param userName
	 * @return
	 */
	public int verifyUserName(String userName);

	/**
	 * 根据userIds来删除用户
	 * 
	 * @author 梁肖萍
	 * @param userIds
	 * @return
	 */
	public int deleteUserById(String userIds);
	
	/**
	 * 更新用户图片
	 * 
	 * @author 梁肖萍
	 * @param pic
	 * @return
	 */
	public int updatePic(User user);
	
	/**
	 * 用于首页展示 大神级别的人物
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public List<User> getHotUser();

	/**
	 * 根据用户名返回密码
	 * 
	 * @author 肖梦雅
	 * @param user
	 * @return
	 */
	public User getUserByUserNameAndUserPwService(User user);


	/**
	 * @author 肖梦雅
	 * @version 2017年7月28日17:03:12
	 * @param user
	 * @induction 根据邮箱(忘记密码后)修改密码
	 */
	public int saveNewUserPwByEmail(User user);
}
