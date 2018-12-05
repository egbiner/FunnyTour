package cn.imhtb.service;

import cn.imhtb.pojo.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> listAllCommentByEssayId(Integer id);
}
