package cn.imhtb.controller;


import cn.imhtb.common.Const;
import cn.imhtb.common.ResponseCode;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Essay;
import cn.imhtb.pojo.EssayOp;
import cn.imhtb.pojo.User;
import cn.imhtb.service.ICommentService;
import cn.imhtb.service.IEssayOpService;
import cn.imhtb.service.IEssayService;
import cn.imhtb.vo.CommentVo;
import cn.imhtb.vo.EssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/essay/")
public class EssayController {

    @Autowired
    IEssayService iEssayService;
    @Autowired
    ICommentService iCommentService;
    @Autowired
    IEssayOpService iEssayOpService;

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public String showById(@PathVariable("id")Integer id, Model model,HttpSession session){
        iEssayOpService.add(new EssayOp(id,Const.EssayOp.ESSAY_VIEW));
        //TODO
        //iEssayService.updateView(id);
        EssayVo essayVo = iEssayService.select(id);
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user!=null){
            essayVo.setBooleanVote(iEssayOpService.checkOp(essayVo.getId(),Const.EssayOp.ESSAY_VOTE,user.getId()));
        }else{
            essayVo.setBooleanVote(false);
        }
        List<CommentVo> commentVo = iCommentService.listAllCommentVoByEssayId(essayVo.getId(),session);
        model.addAttribute("essay",essayVo);
        model.addAttribute("comments",commentVo);
        return "jie/detail";
    }

    @RequestMapping(value = "vote")
    @ResponseBody
    public ServerResponse<String> voteById(Integer id,HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录后再尝试");
        }
        EssayOp essayOp = new EssayOp(id,Const.EssayOp.ESSAY_VOTE);
        essayOp.setUserId(user.getId());
        return iEssayOpService.add(essayOp);
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public List<Essay> list(@RequestParam(required = false)Integer category){
        if (category!=null){
            //TODO 条件搜索
            System.out.println(category);
        }
        return  iEssayService.selectAll();
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> add(Essay essay, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录后再尝试");
        }
        essay.setUserId(user.getId());
        return iEssayService.add(essay);
    }

    @RequestMapping("add_test")
    @ResponseBody
    public ServerResponse<String> addTest(){
        String[] cities = { "张家口","廊坊","上海","南通","台州","温州","丽水","重庆","南充","吐鲁番","那曲","昆明","玉溪","张家界",
                "惠州","汕尾","河源","云浮","桂林","钦州",
                "海口","三亚"};
        int length = cities.length;
        int rand = new Random().nextInt(length-2)+1;
        String[] titles = {"人在旅途，开启一段寻鲜之旅","冬季旅游最佳地，体会热门城市慢时光","一个来了就走不掉的城市","让我梦寐不忘的4天3晚美食之行",
        "爱在夕阳黄昏下","在新旧交叠的空间里","寻觅舌尖心上的山城故事","探秘太湖原生态岛居生活","春天有哪些值得游玩景区","深秋的后坞生活","毛里求斯丨马克吐温说他是天堂原乡","赴一场三年之约","打卡湾区新地标，感受珠海最奢华"};

        String[] positions = {"林芝市","华北电力大学(保定二校区)","和咖啡农业科技展览中心","塘沽森林公园","中国农业大学(烟台校区)","花果山风景区"};
        Essay essay = new Essay();
        essay.setUserId(new Random().nextInt(11)+1);
        essay.setCategoryId(1);
        essay.setCity(cities[rand]);
        essay.setTitle(cities[rand]+","+titles[new Random().nextInt(titles.length-2)+1]);
        essay.setSubTitle("子标题");
        essay.setContent("内容"+cities[rand]);
        essay.setSubContent("子内容"+cities[rand]);
        essay.setPosition(cities[rand]+"市景点");
        iEssayService.add(essay);
        return ServerResponse.createBySuccessMessage("OK");
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> edit(Essay essay, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录后再尝试");
        }
        //Integer之间的比较
        if (essay.getUserId().equals(user.getId())){
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        return iEssayService.edit(essay);
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(Integer id, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录后再尝试");
        }
        if ((iEssayService.select(id).getUserId()).equals(user.getId())){
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        return iEssayService.delete(id);
    }

}
