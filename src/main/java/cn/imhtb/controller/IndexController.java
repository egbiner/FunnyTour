package cn.imhtb.controller;

import cn.imhtb.common.Const;
import cn.imhtb.pojo.Essay;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IEssayService;
import cn.imhtb.service.IUserService;
import cn.imhtb.utils.BaiduMapPointUtils;
import cn.imhtb.vo.AttractionVo;
import cn.imhtb.vo.BaiduLocation;
import cn.imhtb.vo.EssayVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/")
    public String index(Model model)
    {
//        List<EssayVo> essayVos = iEssayService.selectAllVo();
        List<EssayVo> essayVos = iEssayService.selectAllVoWithLimit(10);
        model.addAttribute("essays",essayVos);
        return "index";
    }

    @RequestMapping("/point")
    public String to_point(){
        return "point";
    }

    @RequestMapping("/more")
    public String to_more(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "limit",defaultValue = "10") Integer limit, Model model){
        PageHelper.startPage(page,limit);
        List<EssayVo> list = iEssayService.selectAllVoWithLimit(null);
        //TODO 优化
        PageHelper.startPage(page,limit);
        PageInfo<Essay> pageInfo = new PageInfo<>(iEssayService.selectAll());
        model.addAttribute("essays",list);
        model.addAttribute("pageInfo",pageInfo);
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

    /**
     * 热力图数据
     * @return
     */
    @RequestMapping("/points")
    @ResponseBody
    public Object[] points(){
        List<BaiduLocation> baiduLocations = new ArrayList<>();
        List<AttractionVo> attractionVoList = iEssayService.getAttractionDataOrderByCount(null).getData();
        for (AttractionVo a:attractionVoList) {
            String cityPosition = a.getCityName()+a.getName();
            BaiduLocation baiduLocation = BaiduMapPointUtils.getBaiduLocation(Const.BAIDU_GET_POINT_URL_PREFIX+cityPosition+Const.BAIDU_GET_POINT_URL_SUFFIX+Const.BAIDU_AK);
            baiduLocation.setCount(a.getValue()*30);
            baiduLocations.add(baiduLocation);
        }
        return baiduLocations.toArray();
    }

    @RequestMapping("/introduce")
    public String to_introduce(){
        return "introduce";
    }


}
