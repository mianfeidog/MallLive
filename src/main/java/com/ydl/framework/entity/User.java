package com.ydl.framework.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ydl
 * @since 2017-09-05
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
	private Long id;
    /**
     * 手机号，即为登录账号
     */
	private String telephone;
    /**
     * 微信openid，微信第三方登录对接
     */
	@TableField("wechat_openid")
	private String wechatOpenid;
    /**
     * 密码,SHA1加密
     */
	private String password;
    /**
     * 用户类型，0管理员，1会员，2教练
     */
	private Integer type;
    /**
     * 是否激活状态，0激活，1停用
     */
	@TableField("is_enabled")
	private Integer isEnabled;
    /**
     * 登录token
     */
	private String token;
    /**
     * token失效时间
     */
	@TableField("token_expired_at")
	private Date tokenExpiredAt;
    /**
     * 加密，即为加密随机数
     */
	private String salt;
    /**
     * 创建时间
     */
	@TableField("created_at")
	private Date createdAt;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWechatOpenid() {
		return wechatOpenid;
	}

	public void setWechatOpenid(String wechatOpenid) {
		this.wechatOpenid = wechatOpenid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpiredAt() {
		return tokenExpiredAt;
	}

	public void setTokenExpiredAt(Date tokenExpiredAt) {
		this.tokenExpiredAt = tokenExpiredAt;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", telephone=" + telephone +
			", wechatOpenid=" + wechatOpenid +
			", password=" + password +
			", type=" + type +
			", isEnabled=" + isEnabled +
			", token=" + token +
			", tokenExpiredAt=" + tokenExpiredAt +
			", salt=" + salt +
			", createdAt=" + createdAt +
			"}";
	}
}
