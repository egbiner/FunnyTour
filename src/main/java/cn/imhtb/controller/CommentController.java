package cn.imhtb.controller;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Comment;
import cn.imhtb.pojo.EssayOp;
import cn.imhtb.pojo.User;
import cn.imhtb.service.ICommentService;
import cn.imhtb.service.IEssayOpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/comment/")
public class CommentController {
    @Autowired
    ICommentService iCommentService;
    @Autowired
    IEssayOpService iEssayOpService;

    @RequestMapping("reply")
    @ResponseBody
    public ServerResponse<String> commentReply(Comment comment, HttpSession session){
        iEssayOpService.add(new EssayOp(comment.getEssayId(),Const.EssayOp.ESSAY_COMMENT));

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        comment.setUserId(user.getId());
        comment.setUpdateTime(new Date());
        comment.setCreateTime(new Date());
        return iCommentService.add(comment);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse<String> commentDelete(Integer id, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        //TODO权限验证
        return iCommentService.delete(id);
    }
}
