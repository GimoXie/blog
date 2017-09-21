package com.gimo.blog.web.model;

/**
 * 博主实体类
 * @author GimoXie
 *
 */
public class Blogger {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户名
	 */
	private String userId;
	/**
	 * 简介
	 */
	private String profile;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 格言
	 */
	private String sign;
	/**
	 * 图片路径
	 */
	private String imageName;
	
	public Blogger(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserName(String userId) {
		this.userId = userId;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}