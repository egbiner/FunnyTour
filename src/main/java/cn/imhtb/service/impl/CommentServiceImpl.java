package cn.imhtb.service.impl;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.CommentMapper;
import cn.imhtb.dao.EssayMapper;
import cn.imhtb.dao.UserMapper;
import cn.imhtb.pojo.Comment;
import cn.imhtb.pojo.Essay;
import cn.imhtb.pojo.User;
import cn.imhtb.service.ICommentService;
import cn.imhtb.vo.CommentVo;
import cn.imhtb.vo.HotEssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    EssayMapper essayMapper;

    @Override
    public List<Comment> listAllCommentByEssayId(Integer id) {
        return commentMapper.selectAllByEssayId(id);
    }

    @Override
    public List<CommentVo> listAllCommentVoByEssayId(Integer id, HttpSession session) {
        List<Comment> list = commentMapper.selectAllByEssayId(id);
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment c:list) {
            User user = userMapper.selectByPrimaryKey(c.getUserId());
            CommentVo commentVo = new CommentVo();
            commentVo.setId(c.getId());
            commentVo.setContent(c.getContent());
            commentVo.setCreateTime(c.getCreateTime());

            commentVo.setUsername(user.getName());
            commentVo.setUserId(c.getUserId());
            commentVo.setUserAvatar(user.getAvatar());
            commentVo.setUserLevel(user.getLevel());

            Essay essay = essayMapper.selectByPrimaryKey(id);
            User current_user = (User) session.getAttribute(Const.CURRENT_USER);
            if (current_user==null){
                commentVo.setBooleanCurrentUserAuthor(false);
                commentVo.setBooleanAuthor(false);
                commentVo.setBooleanFloorHost(false);
            }else if (current_user.getId().equals(essay.getUserId())){
                commentVo.setBooleanCurrentUserAuthor(true);
                commentVo.setBooleanAuthor(c.getUserId().equals(essay.getUserId()));
                commentVo.setBooleanFloorHost(current_user.getId().equals(c.getUserId()));
            }else{
                commentVo.setBooleanCurrentUserAuthor(false);
                commentVo.setBooleanAuthor(c.getUserId().equals(essay.getUserId()));
                commentVo.setBooleanFloorHost(current_user.getId().equals(c.getUserId()));
            }
            commentVos.add(commentVo);
        }
        return commentVos;
    }

    @Override
    public List<HotEssayVo> getHotCommentEssay(int limit) {
        List<HotEssayVo> list = commentMapper.selectHotCommentEssay(limit);
        for (HotEssayVo h:list) {
            Essay essay = essayMapper.selectByPrimaryKey(h.getId());
            h.setTitle(essay.getTitle());
            h.setCity(essay.getCity());
        }
        return list;
    }

    @Override
    public ServerResponse<String> add(Comment comment) {
        int count = commentMapper.insert(comment);
        if (count>0){
            return ServerResponse.createBySuccessMessage("回复成功");
        }
        return ServerResponse.createBySuccessMessage("回复失败");
    }

    @Override
    public ServerResponse<String> delete(Integer id) {
        int count = commentMapper.deleteByPrimaryKey(id);
        if (count>0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createBySuccessMessage("删除失败");
    }


}
