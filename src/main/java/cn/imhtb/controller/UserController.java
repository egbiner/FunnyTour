package cn.imhtb.controller;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @RequestMapping("/login")
    public ServerResponse<User> login(String username, String password){
        return iUserService.login(username, password);
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("登出成功");
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    @RequestMapping(value = "/get_info",method = RequestMethod.GET)
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }else{
            return ServerResponse.createBySuccess(user);
        }
    }

    @RequestMapping("/repass")
    public ServerResponse<String> resetPassword(HttpSession session, String newPassword){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }else{
            return ServerResponse.createBySuccess("修改成功");
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ServerResponse<User> edit(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }else{
//            return ServerResponse.createBySuccess(user);
            return ServerResponse.createBySuccessMessage("修改成功");
        }
    }
}

