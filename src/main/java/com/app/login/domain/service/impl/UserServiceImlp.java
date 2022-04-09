package com.app.login.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.login.domain.entity.User;
import com.app.login.domain.repository.UserDao;
import com.app.login.domain.service.UserService;

@Transactional
@Service
public class UserServiceImlp implements UserService {

    @Autowired
    UserDao dao;
    
    /**
     * insert用メソッド.
     */
    public int insert(User user) {

        return dao.insertOne(user);
    }
    
    /**
     * カウント用メソッド.
     */
    public int count() {
        return dao.count();
    }

    /**
     * 全件取得用メソッド.
     */
    public List<User> selectMany() {
        // 全件取得
        return dao.selectMany();
    }
    
    /**
     * １件取得用メソッド.
     */
    public User selectOne(String userId) {
        // selectOne実行
        return dao.selectOne(userId);
    }
    
    /**
     * １件更新用メソッド.
     */
    public int updateOne(User user) {

        return dao.updateOne(user);
    }
    
    /**
     * １件削除用メソッド.
     */
    public int deleteOne(String userId) {

        return dao.deleteOne(userId);
    }
}
