package cn.imhtb.service;


import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Essay;

import java.util.List;

public interface IEssayService {

    Essay select(Integer id);

    ServerResponse<String> add(Essay essay);

    ServerResponse<String> edit(Essay essay);

    ServerResponse<String> delete(Integer id);

    List<Essay> selectAll();

    List<Essay> selectByUserIdWithSelective(Integer userId,Integer count);

    int selectCountByUserId(Integer userId);
}
