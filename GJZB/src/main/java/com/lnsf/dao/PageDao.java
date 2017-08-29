package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lnsf.entity.Page;
import com.lnsf.entity.User;

/**
 * 分页查询
 * @author 梁肖萍
 *
 */
public interface PageDao {
	/**
	 * 实现用户的分页查询
	 * @author 梁肖萍
	 * @param page
	 * @return
	 */
	@Select("select * from (select w.*, rownum rn from gjzb_user w where rownum <= #{end} ) e where rn >= #{start}")
	public List<User> getUserAllByPage(Page<User> page);
	
	/**
	 * 分页查询的总数
	 * @return
	 */
	@Select("select count(userId) from gjzb_user")
	public int getCount();
	
	/**
	 * 实现用户模糊查询的分页查询
	 * @author 梁肖萍
	 * @param page
	 * @return
	 */
	@Select("select * from (select w.*, rownum rn from gjzb_user w where rownum <= #{end} and userName like concat(concat('%',#{userName}),'%')) e where rn >= #{start}")
	public List<User> searchUserByPage(Page<User> page);
	
	/**
	 * 分页模糊查询的总数
	 * @return
	 */
	@Select("select count(userId) from gjzb_user where userName like concat(concat('%',#{userName}),'%')")
	public int getCountBySearch();
	
	
}
