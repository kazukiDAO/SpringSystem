package com.app;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Basic認証
 */
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityBasicConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // (3) Basic認証の対象となるパス
        http.antMatcher("/signup");

        // (4) Basic認証を指定
        http.httpBasic();

        // (5) 対象のすべてのパスに対して認証を有効にする
        http.authorizeRequests().anyRequest().authenticated();

        // (6) すべてのリクエストをステートレスとして設定
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // (7) Basic認証の実装を指定
        auth.authenticationProvider(new BasicAuthenticationProvider());
    }

    // (8) 認証処理の実装クラス
    public class BasicAuthenticationProvider implements AuthenticationProvider {

        // (1) Basic認証のID
        private String username="1";

        // (2) Basic認証のパスワード
        private String password="1";
        
        // (9) 認証チェック
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            String name = authentication.getName();
            String password = authentication.getCredentials().toString();

            //  入力された name / password をチェックする
            if( name.equals(this.username) && password.equals(this.password) ){
               return new UsernamePasswordAuthenticationToken(name,password,authentication.getAuthorities());
            }

            throw new AuthenticationCredentialsNotFoundException("basic auth error");
        }

        // (10) 処理すべきAuthenticationクラスのチェック
        @Override
        public boolean supports(Class<?> authentication) {
            return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        }
    }

}