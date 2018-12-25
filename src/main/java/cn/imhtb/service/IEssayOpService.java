package cn.imhtb.service;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.EssayOp;
import cn.imhtb.vo.HotEssayVo;

import java.util.Date;
import java.util.List;

public interface IEssayOpService {
    ServerResponse<String> add(EssayOp essayOp);

    List<HotEssayVo> getHotViewLeap(Date startDate, Date endDate);

    List<HotEssayVo> getHotVoteLeap(Date startDate, Date endDate);

    List<HotEssayVo> getHotCommentLeap(Date startDate, Date endDate);

    List<HotEssayVo> getHotEssaysByOpAndLimit(int essayOp, int limit);

    List<HotEssayVo> getHotEssaysVoByOpAndLimit(int essayOp, int limit);

    Boolean checkOp(int essayId, int essayOp, int userId);
}
