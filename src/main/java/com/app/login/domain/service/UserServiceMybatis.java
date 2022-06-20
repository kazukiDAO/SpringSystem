package com.app.login.domain.service;

import java.util.List;

import com.app.login.domain.entity.User;

public interface UserServiceMybatis {

	public int insert(User user);
	//public int count();
	public List<User> selectMany();
	public User selectOne(String userId);
	public int updateOne(User user);
	public int deleteOne(String userId);
}
