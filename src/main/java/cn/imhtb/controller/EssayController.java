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
//        iEssayOpService.add(new EssayOp(id,Const.EssayOp.ESSAY_VIEW));
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
        String[] positions = {"林芝市","华北电力大学(保定二校区)","和咖啡农业科技展览中心","塘沽森林公园","中国农业大学(烟台校区)","花果山风景区"};
        Essay essay = new Essay();
        essay.setUserId(2);
        essay.setCategoryId(1);
        essay.setTitle("标题");
        essay.setSubTitle("子标题");
        essay.setContent("内容");
        essay.setSubContent("子内容");
        for (String s:positions) {
            essay.setPosition(s);
            iEssayService.add(essay);
        }
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
