package com.lnsf.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.User;
import com.lnsf.service.ProjectService;
import com.lnsf.service.UserService;
import com.lnsf.util.RC4Encrypt;
import com.lnsf.util.SendJMail;
import com.lnsf.util.VerifyCodeUtil;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;

	@ModelAttribute("user")
	public User getUserById(Integer userId) {
		User user = new User();
		if (userId != null) {
			user = userService.getUserById(userId);
		}
		return user;
	}

	@RequestMapping("/showUserById")
	public String showUserById(HttpServletRequest request, Model model) {
		// int userid = Integer.parseInt(request.getParameter("userId"));
		User user = this.userService.getUserById(2);
		model.addAttribute("user", user);
		return "showUserById";
	}

	@RequestMapping("/showAllUsers")
	public String showAllUsers(HttpServletRequest request, Model model) {
		List<User> users = this.userService.getAllUsers();
		model.addAttribute("users", users);
		return "showAllUsers";
	}

	/**
	 * @author 劳伟玲
	 * @version 创建时间：2017年7月29日14:01:28
	 * @introduction 获取未中标项目中的竞标用户的信息 测试更新
	 */
	@RequestMapping("/getNotTenderUser")
	public String getNotTenderUser(@RequestParam("userId") Integer userId, @RequestParam("projectId") Integer projectId,
			Model model) {
		User user = userService.getUserById(userId);
		Project project = projectService.getProjectById(projectId);
		model.addAttribute("tenderUser", user);
		model.addAttribute("tenderProject", project);

		return "jsp/tender/showUserById";
	}

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日14:01:28
	 * @introduction 添加用户信息，上传照片，信息，一起提交user信息 只提交照片的。 返回照片路径
	 */
	@ResponseBody
	@RequestMapping(value = "/addOneUserPicture")
	public String addOneUserPicture(@ModelAttribute User user,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
		String newFileName = null;
		try {
			String fileName = file.getOriginalFilename();// 获得文件名字
			fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			if (!("").equals(fileName)) {
				String path = "D:/image/image"; // 在当前项目下的image盘下边
				// ------------------上传图片到image---------------------
				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String date = fmt.format(new Date());
				newFileName = user.getUserId() + date + ".jpg";
				File targetFile = new File(path, newFileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存
				try {
					// 1.保存新的图片
					file.transferTo(targetFile);
					// 2.把旧的图片删除
					File oldFile = new File(path, user.getProfilePic());
					oldFile.delete();

					// 3.修改session里面的用户信息
					user.setProfilePic(newFileName);
					request.getSession().setAttribute("user", user);

					// 4.修改数据库的图片路径
					int rows = userService.updatePic(user);

					// 5.把数据返回给前台
					if (rows > 0) {
						return newFileName;// 成功上传图片
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("上传文件为空");
			}
		} catch (Exception e) {
			System.out.println("上传文件出现错误！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改个人信息
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("updateUser")
	@ResponseBody()
	public int updateUser(User user, HttpServletRequest request) {
		// 1.修改用户信息
		int i = userService.updateUser(user);
		// 2.修改session里面的用户信息
		request.getSession().setAttribute("user", user);

		if (i > 0) {
			return 1; // 表示修改成功
		}
		return 0;
	}

	/**
	 * 实现模糊查询
	 * 
	 * @author 梁肖萍
	 * @param userName
	 * @return
	 */
	@RequestMapping("searchUser")
	@ResponseBody()
	public List<User> searchUser(User user) {
		List<User> users = new ArrayList<>();
		users = userService.searchUser(user);
		return users;
	}

	/**
	 * 注册用户
	 * 
	 * @author 梁肖萍
	 * @param User
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("regit")
	public void regit(User addUser,String reg_code,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String code = (String) request.getSession().getAttribute("emailCode");
		if(code == null){
			response.sendRedirect("/GJZB/jsp/user/regist.jsp");
		}
		if(reg_code.trim().length()>0 && code.equals(reg_code)){
			String psw = RC4Encrypt.getkeyToPWD(addUser.getUserPw());
			addUser.setUserPw(psw);
			int row = userService.addOneUser(addUser);
			if (row > 0) {
				response.sendRedirect("/GJZB/login.jsp");
			} 
		}
	}

	/**
	 * 校验Email是否被注册过
	 * 
	 * @author 梁肖萍
	 * @param email
	 * @return
	 */
	@RequestMapping("verifyEmail")
	@ResponseBody()
	public int verifyEmail(String email) {
		email = email.trim();
		if (email != null && email.trim().length() > 0) {
			int row = userService.verifyEmail(email);
			if (row > 0) {
				return 1; // 表示email被注册过
			}
		}
		return 0;
	}

	/**
	 * 校验手机号是否被注册过
	 * 
	 * @author 梁肖萍
	 * @param phone
	 * @return
	 */
	@RequestMapping("verifyPhone")
	@ResponseBody()
	public int verifyPhone(String phone) {
		phone = phone.trim();
		if (phone != null && phone.trim().length() > 0) {
			int row = userService.verifyPhone(phone);
			if (row > 0) {
				return 1; // 表示手机号被注册过
			}
		}
		return 0;
	}

	/**
	 * 校验用户名是否已经存在
	 * 
	 * @author 梁肖萍
	 * @param userName
	 * @return
	 */
	@RequestMapping("verifyUserName")
	@ResponseBody()
	public int verifyUserName(String userName) {
		userName = userName.trim();
		if (userName != null && userName.trim().length() > 0) {
			int row = userService.verifyUserName(userName);
			if (row > 0) {
				return 1; // 表示用户名已经被注册过
			}
		}
		return 0;
	}

	/**
	 * 退出exit
	 * 
	 * @author 梁肖萍
	 * @param request
	 * @return
	 */
	@RequestMapping("exit")
	public void exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("/GJZB/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有用户
	 * 
	 * @author 梁肖萍
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getAllUsers", produces = "application/json;chartset=utf-8")
	@ResponseBody()
	public String getAllUsers(int page, int rows) {
		Page<User> pageUser = new Page<>();
		pageUser.setStart((page - 1) * rows + 1);
		pageUser.setEnd(page * rows);

		List<User> users = userService.getUserAllByPage(pageUser);
		pageUser.setRows(users);
		int total = userService.getCount();
		pageUser.setTotal(total);
		// JSONObject jsonObject = JSONObject.fromObject(pageUser);
		Gson gson = new Gson();
		// String json ="[" + jsonObject.toString() + "]";
		return gson.toJson(pageUser);
	}

	/**
	 * 根据用户userId来删除用户
	 * 
	 * @author 梁肖萍
	 * @param userId
	 * @return
	 */
	@RequestMapping("deleteUserById")
	@ResponseBody()
	public int deleteUserById(String userIds) {
		if (userIds.trim().length() > 0) {
			int i = userService.deleteUserById(userIds);
			return i;
		}
		return 0;
	}

	/**
	 * 根据用户useId去修改用户，获得use对象，以及把对象返回到修改用户的页面
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("toEditUser")
	public String toEditUser(User user, HttpServletRequest request) {
		request.setAttribute("editUser", user);
		return "jsp/user/editUser";
	}

	/**
	 * 管理员修改用户
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @return
	 */
	@RequestMapping("editUser")
	@ResponseBody()
	public int editUser(User user) {
		int rows = userService.updateUserByAdmin(user);
		if (rows > 0) {
			return 1;// 表示更新成功
		}
		return 0;
	}

	/**
	 * 修改个人信息，异步获取到该对象信息，以及转到修改个人信息的页面
	 * 
	 * @author 梁肖萍
	 * @param user
	 * @return user
	 */
	@RequestMapping("editPersonal")
	@ResponseBody()
	public User editPersonal(User user) {
		return user;
	}

	/**
	 * 获得验证码
	 * 
	 * @author 肖梦雅
	 * @param request
	 * @param response
	 */
	@RequestMapping("getVerifyCode")
	@ResponseBody()
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			VerifyCodeUtil.getVerifyCodeDbutil(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 比较验证码是否一致
	 * 
	 * @author 肖梦雅
	 * @param request
	 */
	@RequestMapping("validateVerifyCode")
	@ResponseBody()
	public int validateVerifyCode(HttpServletRequest request, String verify) {
		String vCode = (String) request.getSession().getAttribute("verifyCode");

		if (vCode.equalsIgnoreCase(verify)) {
			return 1; // 表示一致
		}
		return 0;

	}

	/**
	 * 登录用户的验证
	 * 
	 * @author 肖梦雅
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("valifyUser")
	@ResponseBody()
	public Integer loginValifydUser(User user, HttpServletRequest request) {
		user.setUserPw(RC4Encrypt.getkeyToPWD(user.getUserPw()));
		User user1 = userService.getUserByUserNameAndUserPwService(user);
		if (user1 != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user1);
			return 1;
		} else {
			return 0;

		}
	}

	/**
	 * 用于忘记密码，判断邮箱是否已经注册
	 * 
	 * @author 肖梦雅
	 * @version 2017-7-28 13:06:34
	 * @induction用于忘记密码。。发送邮箱 ，提取验证码
	 * @return
	 */
	@RequestMapping("getEmail")
	@ResponseBody
	public int getEmail(String email) {
		int i = userService.verifyEmail(email.trim());
		if (i > 0) {
			// 该邮箱存在
			return i;
		} else {
			return 0;
		}

	}

	/**
	 * 用于发送验证码到邮箱，忘记密码
	 * 
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping("getIdentifideCodeByEmail")
	@ResponseBody
	public String getIdentifideCodeByEmail(String email, HttpServletRequest request) {
		request.getSession().setAttribute("email", email);
		// 发送验证码到邮箱
		SendJMail.chooseEMail(email);
		String code = SendJMail.testCode;
		request.getSession().setAttribute("emailCode", code);
		// 获取验证码
		return SendJMail.testCode;
	}

	/**
	 * @author 肖梦雅
	 * @version 2017-7-28 16:41:24
	 * @induction忘记密码后修改的新密码的保存
	 * @return
	 */
	@RequestMapping("saveNewUserPw")
	@ResponseBody
	public int saveNewUserPw(User user) {
		System.out.println("user" + user);
		user.setUserPw(RC4Encrypt.getkeyToPWD(user.getUserPw()));
		int i = userService.saveNewUserPwByEmail(user);
		System.out.println("i=" + i);
		return i;
	}

}
