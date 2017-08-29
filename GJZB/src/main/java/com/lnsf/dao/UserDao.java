package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lnsf.entity.Page;
import com.lnsf.entity.User;

/**
* @author 劳伟玲 
* @version 创建时间：2017年7月26日21:29:22
* @introduction  创建用户接口UserDao，注解实现用户信息的查询和删除
*/
/**
 * @author 黄浩贡
 * @version 创建时间：2017年7月27日10:26:20
 * @introduction 实现user的修改操作
 */
public interface UserDao {
	// 查询全部用户信息
	@Select("select * from gjzb_user")
	List<User> getAllUsers();

	// 按用户id查询单个用户信息
	@Select("select * from gjzb_user where userId=#{userId}")
	User getUserById(Integer userId);

	// 按用户id删除个人用户信息
	@Delete("delete from gjzb_user where userId=#{userId}")
	public int deleteUserById(Integer userId);

	// 根据Id修改用户名
	@Update("update Gjzb_User set userName=#{userName} where userId=#{userId}")
	public int updateUserNameByID(User user);

	// 根据Id修改密码
	@Update("update Gjzb_User set userPw=#{userPw} where userId=#{userId}")
	public int updateUserPwByID(User user);

	// 根据Id修改个人简介
	@Update("update Gjzb_User set introduction=#{introduction} where userId=#{userId}")
	public int updateUserIntroduByID(User user);

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日09:24:09
	 * @introduction 实现超级管理员user的修改操作 比普通管理员多修改用户级别、用户中标次数和金钱。
	 */
	@Update("update Gjzb_User set userName=#{userName}" + ",userPw=#{userPw}" + ",phone=#{phone}"
			+ ",profilePic=#{profilePic} " + ",userlevel=#{userlevel}" + ",winCount=#{winCount}" + ",money=#{money} "
			+ "where userId=#{userId}")
	public int updateUserByAdmin(User user);

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日09:24:09
	 * @introduction 实现user的修改操作
	 */
	// 普通用户根据修改个人简介所有
	@Update("update Gjzb_User set userName=#{userName}" + ",userPw=#{userPw}" + ",phone=#{phone}" + ",email=#{email}"
			+ ",introduction=#{introduction}" + ",profilePic=#{profilePic} " + "where userId=#{userId}")
	public int updateUser(User user);

	/**
	 * 用于首页的报表，展示用戶的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select count(userId) from gjzb_user")
	public int getUserCount();

	/**
	 * 实现用户的分页查询
	 * 
	 * @author 梁肖萍
	 * @param page
	 * @return
	 */
	@Select("select * from (select w.*, rownum rn from gjzb_user w where rownum <= #{end} ) e where rn >= #{start}")
	public List<User> getUserAllByPage(Page<User> page);

	/**
	 * 分页查询的总数
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select count(userId) from gjzb_user")
	public int getCount();

	/**
	 * 实现用户模糊查询的分页查询
	 * 
	 * @author 梁肖萍
	 * @param page
	 * @return
	 */
	@Select("select * from (select w.*, rownum rn from gjzb_user w where rownum <= #{end} and userName like concat(concat('%',#{userName}),'%')) e where rn >= #{start}")
	public List<User> searchUserByPage(Page<User> page);

	/**
	 * 分页模糊查询的总数
	 * 
	 * @return
	 */
	@Select("select count(userId) from gjzb_user where userName like concat(concat('%',#{userName}),'%')")
	public int getCountBySearch();

	/**
	 * 实现用户的模糊查询
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @return
	 */
	@Select("select * from gjzb_user where userName like concat(concat('%',#{userName}),'%')")
	public List<User> searchUser(User user);

	/**
	 * 添加用户信息
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @return
	 */
	@Insert("insert into gjzb_user(userId,userName,userPw,phone,email,profilePic) values(userseq.nextval,#{userName},#{userPw},#{phone},#{email},'1.jpg')")
	public int addOneUser(User user);

	/**
	 * 校验Email是否被注册过
	 * 
	 * @author 梁肖萍
	 * @param email
	 * @return
	 */
	@Select("select count(*) from gjzb_user where email=#{email}")
	public int verifyEmail(String email);

	/**
	 * 校验Phone是否被注册过
	 * 
	 * @author 梁肖萍
	 * @param phone
	 * @return
	 */
	@Select("select count(*) from gjzb_user where phone=#{phone}")
	public int verifyPhone(String phone);

	/**
	 * 校验用户名是否存在
	 * 
	 * @author 梁肖萍
	 * @param userName
	 * @return
	 */
	@Select("select count(*) from gjzb_user where userName=#{userName}")
	public int verifyUserName(String userName);

	/**
	 * 更新用户图片
	 * 
	 * @author 梁肖萍
	 * @param pic
	 * @return
	 */
	@Update("update Gjzb_User set profilePic=#{profilePic} where userId = #{userId}")
	public int updatePic(User user);

	/**
	 * 用于首页展示 大神级别的人物
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select * from (select * from Gjzb_User order by winCount desc,money desc) where rownum <=5")
	public List<User> getHotUser();

	/**
	 * 登录
	 * 
	 * @author 肖梦雅
	 * @param user
	 * @return
	 */
	@Select("select  * from Gjzb_User where userName=#{userName} and userPw=#{userPw}")
	public User getUserByUserNameAndUserPw(User user);

	/**
	 * @author 肖梦雅
	 * @param user
	 * @induction 根据邮箱(忘记密码后)修改密码
	 */
	@Update("update Gjzb_User set userPw=#{userPw} where email=#{email}")
	public int saveNewUserPwByEmail(User user);
}
