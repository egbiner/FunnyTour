package cn.imhtb.service;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Comment;
import cn.imhtb.vo.CommentVo;
import cn.imhtb.vo.HotEssayVo;

import java.util.List;

public interface ICommentService {
    List<Comment> listAllCommentByEssayId(Integer id);

    List<CommentVo> listAllCommentVoByEssayId(Integer id);

    List<HotEssayVo> getHotCommentEssay(int i);

    ServerResponse<String> add(Comment comment);

    ServerResponse<String> delete(Integer id);
}
