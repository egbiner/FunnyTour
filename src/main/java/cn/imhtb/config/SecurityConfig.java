//package cn.imhtb.config;
//
//import jdk.nashorn.internal.ir.annotations.Ignore;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @author PinTeh
// * @date 2019/6/25
// */
////@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/user/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/login").permitAll()
//                .anyRequest()
//                .permitAll();
//    }
//}
