package com.app.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.login.domain.entity.User;
import com.app.login.domain.service.UserServiceMybatis;

@RestController
public class UserRestController {

    @Autowired
    UserServiceMybatis service;

    /**
     * ユーザー全件取得
     */
    @GetMapping("/rest/get")
    public List<User> getUserMany() {

        // ユーザー全件取得
        return service.selectMany();
    }

    /**
     * ユーザー１件取得
     */
    @GetMapping("/rest/get/{id:.+}")
    public User getUserOne(@PathVariable("id") String userId) {

        // ユーザー１件取得
        return service.selectOne(userId);
    }

    /**
     * ユーザー１件登録
     */
    @PostMapping("/rest/insert")
    public String postUserOne(@RequestBody User user) {

        // ユーザーを１件登録
        int result = service.insert(user);

        String str = "";

        if(result == 1) {

            str = "{\"result\":\"ok\"}";

        } else {

            str = "{\"result\":\"error\"}";

        }

        // 結果用の文字列をリターン
        return str;
    }

    /**
     * ユーザー１件登録
     */
    @PutMapping("/rest/update")
    public String putUserOne(@RequestBody User user) {

        // ユーザーを１件登録
        int result = service.updateOne(user);

        String str = "";

        if(result == 1) {

            str = "{\"result\":\"ok\"}";

        } else {

            str = "{\"result\":\"error\"}";

        }

        // 結果用の文字列をリターン
        return str;
    }

    @DeleteMapping("/rest/delete/{id:.+}")
    public String deleteUserOne(@PathVariable("id") String userId) {

        // ユーザーを１件削除
        int result = service.deleteOne(userId);

        String str = "";

        if(result == 1) {

            str = "{\"result\":\"ok\"}";

        } else {

            str = "{\"result\":\"error\"}";

        }

        // 結果用の文字列をリターン
        return str;
    }
}
