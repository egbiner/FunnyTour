package cn.imhtb.service;


import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Essay;
import cn.imhtb.vo.AttractionVo;
import cn.imhtb.vo.CityVo;
import cn.imhtb.vo.EssayVo;
import cn.imhtb.vo.HotEssayVo;

import java.util.List;

public interface IEssayService {

    EssayVo select(Integer id);

    ServerResponse<String> add(Essay essay);

    ServerResponse<String> edit(Essay essay);

    ServerResponse<String> delete(Integer id);

    List<Essay> selectAll();

    List<Essay> selectByUserIdWithSelective(Integer userId,Integer count);

    int selectCountByUserId(Integer userId);

    ServerResponse<List<CityVo>> getTopHotCitiesData(Integer limit);

//    List<EssayVo> selectAllVo();

    List<EssayVo> selectAllVoWithLimit(Integer limitCount);

    List<HotEssayVo> getHotVotesEssay(int i);

    List<HotEssayVo> getHotViewsEssay(int i);

    ServerResponse<String> vote(Integer id);

    ServerResponse<String> updateView(Integer essayId);

    ServerResponse<List<AttractionVo>> getTopHotCitiesAttractionData(String city,Integer limit);

    ServerResponse<List<AttractionVo>> getAttractionDataOrderByCount(Integer limit);

    List<Essay> selectCityByUserIdWithSelective(int i, int count);
}
