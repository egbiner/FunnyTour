package cn.imhtb.service.impl;

import cn.imhtb.dao.CommentMapper;
import cn.imhtb.pojo.Comment;
import cn.imhtb.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> listAllCommentByEssayId(Integer id) {
        List<Comment> list = commentMapper.selectAllByEssayId(id);
        return list;
    }
}
