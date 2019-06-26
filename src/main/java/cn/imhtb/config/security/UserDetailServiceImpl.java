//package cn.imhtb.config.security;
//
//import cn.imhtb.pojo.User;
//import cn.imhtb.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * @author PinTeh
// * @date 2019/6/25
// */
////@Component
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private IUserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByUsername(username);
//        String role = userService.findRoleByUsername(username);
//
//        return null;
//    }
//
//}
