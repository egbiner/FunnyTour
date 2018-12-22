package cn.imhtb.service.impl;

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
        List<Comment> list = commentMapper.selectAllByEssayId(id);
        return list;
    }

    @Override
    public List<CommentVo> listAllCommentVoByEssayId(Integer id) {
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
            commentVo.setBooleanAuthor(essayMapper.selectByPrimaryKey(c.getEssayId()).getUserId()==c.getUserId());
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
