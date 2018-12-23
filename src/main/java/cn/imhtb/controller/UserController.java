package cn.imhtb.controller;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Essay;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IEssayService;
import cn.imhtb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IEssayService iEssayService;

    @RequestMapping("home")
    public String to_home(HttpSession session,Model model){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return "redirect:/login";
        }
        List<Essay> essays = iEssayService.selectByUserIdWithSelective(user.getId(),7);
        model.addAttribute("user",user);
        model.addAttribute("essays",essays);
        return "user/home";
    }

    @RequestMapping("home/{uid}")
    public String to_user_home(@PathVariable("uid") Integer uid, Model model){
        List<Essay> essays = iEssayService.selectByUserIdWithSelective(uid,7);
        User user = iUserService.getInfoById(uid);
        model.addAttribute("user",user);
        model.addAttribute("essays",essays);
        return "user/home";
    }

    @RequestMapping("login")
    @ResponseBody
    public ServerResponse<User> login(String username, String password,HttpSession session){
        ServerResponse<User> serverResponse = iUserService.login(username, password);
        session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        return serverResponse;
    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return "redirect:/login";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    @RequestMapping(value = "get_info",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }else{
            return ServerResponse.createBySuccess(user);
        }
    }

    @RequestMapping("repass")
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String newPassword){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }else{
            return ServerResponse.createBySuccess("修改成功");
        }
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
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

