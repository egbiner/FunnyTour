package cn.imhtb.controller;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Essay;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IEssayService;
import cn.imhtb.service.IUserService;
import cn.imhtb.utils.BaiduMapPointUtils;
import cn.imhtb.vo.BaiduLocation;
import cn.imhtb.vo.EssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;



@Controller
public class IndexController {

    @Autowired
    IEssayService iEssayService;
    @Autowired
    IUserService iUserService;

//    @RequestMapping("/")
//    public String index(Model model){
//        model.addAttribute(Const.CURRENT_USER,false);
//        return "index";
//    }
//
//    @RequestMapping("/user/login")
//    public String login(){
//        return "user/login";
//    }
//
//    @RequestMapping("/point")
//    @ResponseBody
//    public List<Essay> point(){
//        List<Essay> essays = iEssayService.selectAll();
//        for (Essay e:essays) {
//            System.out.println(e.getPosition());
//        }
//        return essays;
//    }

    @RequestMapping("/")
    public String index(HttpSession session,Model model)
    {
//        ServerResponse<User> serverResponse = iUserService.login("admin", "admin");
//        ServerResponse<User> serverResponse = iUserService.login("user", "user");
        List<EssayVo> essayVos = iEssayService.selectAllVo();
//        session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        model.addAttribute("essays",essayVos);
        return "index";
    }

    @RequestMapping("/point")
    public String to_point(){
        return "point";
    }

    @RequestMapping("/more")
    public String to_more(Model model){
        List<EssayVo> list = iEssayService.selectAllVo();
        model.addAttribute("essays",list);
        return "jie/index";
    }

    @RequestMapping("/login")
    public String to_login(){
        return "user/login";
    }

    @RequestMapping("/register")
    public String to_register(){
        return "user/register";
    }

    @RequestMapping("/user/set")
    public String to_set(Model model,HttpSession session)
    {
        User user  = (User) session.getAttribute(Const.CURRENT_USER);
        model.addAttribute("user",user);
        return "user/set";
    }

    @RequestMapping("/user/message")
    public String to_message(){
        return "user/message";
    }

    @RequestMapping("/user/index")
    public String to_user_index(HttpSession session,Model model){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        List<Essay> essays = iEssayService.selectByUserIdWithSelective(user.getId(),null);
        int essaysCount = iEssayService.selectCountByUserId(user.getId());
        model.addAttribute("essaysCount",essaysCount);
        model.addAttribute("essays",essays);
        return "user/index";
    }

    @RequestMapping("/essay")
    public String to_essay_add(){
        return "jie/add";
    }

    @RequestMapping("/points")
    @ResponseBody
    public Object[] points(){
        List<BaiduLocation> baiduLocations = new ArrayList<>();
        List<Essay> essays = iEssayService.selectAll();
        for (Essay e:essays) {
            BaiduLocation baiduLocation = BaiduMapPointUtils.getBaiduLocation(Const.BAIDU_GET_POINT_URL_PREFIX+e.getPosition()+Const.BAIDU_GET_POINT_URL_SUFFIX+Const.BAIDU_AK);
            baiduLocation.setCount((int)(Math.random()*100));
            baiduLocations.add(baiduLocation);
        }
        return baiduLocations.toArray();
    }

}
