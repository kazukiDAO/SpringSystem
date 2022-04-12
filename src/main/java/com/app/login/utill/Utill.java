package com.app.login.utill;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public class Utill {
	
	/**
	 * セッションの勉強用のために使うメソッド
	 */
	public static void checkSession(HttpSession session) {
		// session が作られてるか見てみる。
		if (session != null) {
			System.out.println("sessionはあります：" + session);
			String jsessionid = session.getId();
			System.out.println("JSESSIONIDはあります：" + jsessionid);

			// session に authentication が作られてるか見てみる。
			SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			if (securityContext != null) {
				Authentication authentication = securityContext.getAuthentication();
				System.out.println("authenticationはあります" + authentication);
			}else {
				System.out.println("authenticationはありません");
			}
		}
	}
	
    public static String getHostName() {
            InetAddress ia = null;
			try {
				ia = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
            return ia.getHostName();
    }
}