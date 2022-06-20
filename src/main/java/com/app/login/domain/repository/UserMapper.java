package com.app.login.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.app.login.domain.entity.User;

@Mapper
public interface UserMapper {

    // 登録用メソッド
    public int insert(User user);

    // カウント用メソッド
    public int count();

    // １件検索用メソッド
    public User selectOne(String userId);

    // 全件検索用メソッド
    public List<User> selectMany();

    // １件更新用メソッド
    public int updateOne(User user);

    // １件削除用メソッド
    public int deleteOne(String userId);
}
