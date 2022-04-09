package com.app.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.login.domain.entity.User;
import com.app.login.domain.model.SignUpForm;
import com.app.login.domain.service.UserService;

@Controller
public class SignUpController {

	@Autowired
	private UserService userService;
	
    /**
     * ユーザー登録画面のGETメソッド用処理.
     */
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignUpForm form /*, Model model*/) {

    	//@ModelAttributeを付けると
    	//キー名は、デフォルトでは、型の最初の文字を小文字にしたものになる。つまりsignUpFormになる。
    	//キー名を指定したい場合は、@ModelAttribute<キー名>と指定する。
    	
        //以下をやってるイメージ。
        //model.addAttribute("signUpForm",form);

        return "login/signup";
    }

    /**
     * ユーザー登録画面のPOSTメソッド用処理.
     */
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated SignUpForm form,BindingResult bindingResult) {
    	
    	//@Validatedがないとバリデーションかからずスルーする
    	//()でバリデーションを実行する順番を定義したクラスを指定するとその通りの順番で実行してくれる。
    	
    	if (bindingResult.hasErrors()){
    		return "login/signup";
    	}
    	
    	System.out.println(form);
        // insert用変数
        User user = new User();

        user.setUserId(form.getUserId()); //ユーザーID
        user.setPassWord(form.getPassWord()); //パスワード
        user.setUserName(form.getUserName()); //ユーザー名
        user.setBirthDay(form.getBirthDay()); //誕生日
        user.setAge(form.getAge()); //年齢
        user.setMarriage(form.getMarriage()); //結婚ステータス
        user.setRole("ROLE_GENERAL"); //ロール（一般）

        // ユーザー登録処理
        int result = userService.insert(user);

        // ユーザー登録結果の判定
        if (result > 0) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }

        // login.htmlにリダイレクト
        return "redirect:/login";
    }
   
//    GlobalControllAdvice.javaで代替
    
//    /**
//     * DataAccessException発生時の処理メソッド.
//     */
//    @ExceptionHandler(DataAccessException.class)
//
//    public String dataAccessExceptionHandler(DataAccessException e, Model model) {
//
//        // 例外クラスのメッセージをModelに登録
//        model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");
//
//        // 例外クラスのメッセージをModelに登録
//        model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました");
//
//        // HTTPのエラーコード（500）をModelに登録
//        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return "error";
//    }
//
//    /**
//     * Exception発生時の処理メソッド.
//     */
//    @ExceptionHandler(Exception.class)
//
//    public String exceptionHandler(Exception e, Model model) {
//
//        // 例外クラスのメッセージをModelに登録
//        model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");
//
//        // 例外クラスのメッセージをModelに登録
//        model.addAttribute("message", "SignupControllerでExceptionが発生しました");
//
//        // HTTPのエラーコード（500）をModelに登録
//        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return "error";
//    }
}