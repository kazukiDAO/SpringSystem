package com.app.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.domain.entity.User;
import com.app.login.domain.model.DetailForm;
import com.app.login.domain.model.SignUpForm;
import com.app.login.domain.service.UserService;
import com.app.login.utill.Utill;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	HttpServletRequest request;

	// ユーザー一覧画面のGET用メソッド.
	@GetMapping("/home")
	public String getHome(Model model) {

		HttpSession session = request.getSession(false);
		Utill.checkSession(session);

		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/home :: home_contents");

		return "login/homeLayout";
	}

	/**
	 * ユーザー一覧画面のGETメソッド用処理.
	 */
	@GetMapping("/userList")
	public String getUserList(Model model) {

		// コンテンツ部分にユーザー一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/userList :: userList_contents");

		// ユーザー一覧の生成
		List<User> userList = userService.selectMany();

		// Modelにユーザーリストを登録
		model.addAttribute("userList", userList);

		// データ件数を取得
		int count = userService.count();
		model.addAttribute("userListCount", count);
		HttpSession session = request.getSession(false);
		Object result = session.getAttribute("result"); // 取得
		// session.invalidate(); これしたらログイン画面戻る
		model.addAttribute("result", result);

		return "login/homeLayout";
	}

	/**
	 * ユーザー詳細画面のGETメソッド用処理.
	 */
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute DetailForm form, Model model, @PathVariable("id") String userId) {

		// ユーザーID確認（デバッグ）
		System.out.println("userId = " + userId);

		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/userDetail :: userDetail_contents");

		// ユーザーIDのチェック
		if (userId != null && userId.length() > 0) {

			// ユーザー情報を取得
			User user = userService.selectOne(userId);

			// Userクラスをフォームクラスに変換
			form.setUserId(user.getUserId()); // ユーザーID
			form.setUserName(user.getUserName()); // ユーザー名
			form.setBirthDay(user.getBirthDay()); // 誕生日
			form.setAge(user.getAge()); // 年齢
			form.setMarriage(user.getMarriage()); // 結婚ステータス

			// Modelに登録
			model.addAttribute("detailForm", form);

		}

		return "login/homeLayout";
	}

	/**
	 * ユーザー更新用処理.
	 */
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute @Validated DetailForm form,BindingResult bindingResult, Model model) {

		System.out.println("更新ボタンの処理");

		// Userインスタンスの生成
		User user = new User();

		// フォームクラスをUserクラスに変換
		user.setUserId(form.getUserId());
		user.setUserName(form.getUserName());
		user.setBirthDay(form.getBirthDay());
		user.setAge(form.getAge());
		user.setMarriage(form.getMarriage());
		HttpSession session = request.getSession(false);
		try {

			// 更新実行
			int result = userService.updateOne(user);
			if (result > 0) {
				session.setAttribute("result", "更新成功");
			} else {
				session.setAttribute("result", "更新失敗");
			}

		} catch (DataAccessException e) {

			session.setAttribute("result", "更新失敗(トランザクションテスト)");

		}

		// ユーザー一覧画面を表示
		return "redirect:/userList";
	}

	/**
	 * ユーザー削除用処理.
	 */
	@PostMapping(value = "/userDetail", params = "delete")
	public String postUserDetailDelete(@ModelAttribute SignUpForm form, Model model) {

		System.out.println("削除ボタンの処理");

		// 削除実行
		int result = userService.deleteOne(form.getUserId());
		HttpSession session = request.getSession(false);
		if (result > 0) {

			session.setAttribute("result", "削除成功");

		} else {

			session.setAttribute("result", "削除失敗");

		}

		// ユーザー一覧画面を表示
		return "redirect:/userList";
	}

	/**
	 * アドミン権限専用画面のGET用メソッド.
	 * 
	 * @param model Modelクラス
	 * @return 画面のテンプレート名
	 */
	@GetMapping("/admin")
	public String getAdmin(Model model) {

		// コンテンツ部分にユーザー詳細を表示するための文字列を登録
		model.addAttribute("contents", "login/admin :: admin_contents");

		// レイアウト用テンプレート
		return "login/homeLayout";
	}

}