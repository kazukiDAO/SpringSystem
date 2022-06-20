package com.app.login.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.login.domain.entity.User;
import com.app.login.domain.repository.UserMapper;
import com.app.login.domain.service.UserServiceMybatis;

@Transactional
@Service("UserServiceMybatisImpl")
public class UserServiceMybatisImpl implements UserServiceMybatis {

    @Autowired
    UserMapper userMapper;

    @Override
    public int insert(User user) {
        //insert実行
        return userMapper.insert(user);
    }

    @Override
    public User selectOne(String userId) {
        //select実行
        return userMapper.selectOne(userId);
    }

    @Override
    public List<User> selectMany() {
        //select実行
        return userMapper.selectMany();
    }

    @Override
    public int updateOne(User user) {
        //update実行
        return userMapper.updateOne(user);
    }

    @Override
    public int deleteOne(String userId) {
        //delete実行
        return userMapper.deleteOne(userId);
    }
}
