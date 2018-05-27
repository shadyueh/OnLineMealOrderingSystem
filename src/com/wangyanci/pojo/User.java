package com.wangyanci.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", email=" + email + ", role=" + role + ", activestate=" + activestate + ", activecode=" + activecode
				+ ", tel=" + tel + ", state=" + state + ", registtime=" + registtime + "]";
	}

	private int id; // 用户编号
	private String username; // 用户名
	private String password; // 密码
	private String nickname; // 昵称
	private String email; // 邮箱
	private String role; // 角色 默认是user
	private int activestate; // 是否激活 0 未激活
	private String activecode; // 激活码 UUID获取
	private String tel;
	private int state;// 账户状态

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	private Timestamp registtime; // 更新时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActivestate() {
		return activestate;
	}

	public void setActivestate(int activestate) {
		this.activestate = activestate;
	}

	public String getActivecode() {
		return activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public Timestamp getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Timestamp registtime) {
		this.registtime = registtime;
	}

	// 校验方法
	public Map<String, String> validation() {
		Map<String, String> map = new HashMap<String, String>();
		if (username == null || username.trim().length() == 0) {
			map.put("regist.username.error", "用户名不能为空");
		}
		if (password == null || password.trim().length() == 0) {
			map.put("regist.password.error", "密码不能为空");
		}
		return map;

	}

}
