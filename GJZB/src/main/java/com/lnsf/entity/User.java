package com.lnsf.entity;


public class User {
	private Integer userId ; 
	private String userName ; 
	private String userPw ; 
	private String phone ; 
	private String email ; 
	private String introduction ;
	private String profilePic  ; 
	private String userlevel ;
	private Integer winCount ;
	private Integer money ;
	
	public User() {
		super();
	}
	public User(Integer userId, String userName, String userPw, String phone, String email, String introduction,
			String profilePic, String userlevel, Integer winCount, Integer money) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPw = userPw;
		this.phone = phone;
		this.email = email;
		this.introduction = introduction;
		this.profilePic = profilePic;
		this.userlevel = userlevel;
		this.winCount = winCount;
		this.money = money;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}
	public Integer getWinCount() {
		return winCount;
	}
	public void setWinCount(Integer winCount) {
		this.winCount = winCount;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPw=" + userPw + ", phone=" + phone
				+ ", email=" + email + ", introduction=" + introduction + ", profilePic=" + profilePic + ", userlevel="
				+ userlevel + ", winCount=" + winCount + ", money=" + money + "]";
	}

	
}

