package com.app.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.utill.Utill;

@Controller
public class LoginController {

	@Autowired
	HttpServletRequest request;

	/**
	 * ログイン画面のGETメソッド用処理.
	 */
	@GetMapping("/login")
	public String getLogin(Model model) {

		HttpSession session = request.getSession(false);
		Utill.checkSession(session);

		// login.htmlに画面遷移
		return "login/login";
	}

	/**
	 * ログイン画面のPOSTメソッド用処理.
	 */
	@PostMapping("/login")
	public String postLogin(Model model) {

		// ホーム画面に画面遷移
		return "redirect:/home";
	}
	
	// ログアウト用メソッド.
	@PostMapping("/logout")
	public String postLogout() {
		
		
		HttpSession session = request.getSession(false);
		
		//SecurityConfig.javaで似たようなことしてる
		session.invalidate();
		
		//セッション破棄してもSpring は再びセッション取得し直してるぽい、CSRF対策のため??
		Utill.checkSession(session);

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}
}