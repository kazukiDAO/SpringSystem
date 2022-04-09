package com.app.login.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {

    private String userId; //ユーザーID
    private String passWord; //パスワード
    private String userName; //ユーザー名
    private Date birthDay; //誕生日
    private Integer age; //年齢
    private Integer marriage; //結婚ステータス
    private String role; //ロール
}