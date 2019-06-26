package cn.imhtb.dao;

import cn.imhtb.pojo.Essay;
import cn.imhtb.vo.AttractionVo;
import cn.imhtb.vo.CityVo;
import cn.imhtb.vo.HotEssayVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EssayMapper {

    int vote(Integer id);

    List<Essay> selectAll();

    int updateViewByEssayId(Integer essayId);

    int deleteByPrimaryKey(Integer id);

    int insert(Essay record);

    int insertSelective(Essay record);

    Essay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Essay record);

    int updateByPrimaryKey(Essay record);

    int selectCountByUserId(Integer userId);

    List<CityVo> selectTopHotCities(int limit);

    List<HotEssayVo> selectHotVotesEssay(int limit);

    List<HotEssayVo> selectHotViewsEssay(int limit);

    List<Essay> selectWithLimit(@Param("limit") Integer limit);

    List<AttractionVo> selectAttractionDataOrderByCount(@Param("limit") Integer limit);

    List<AttractionVo> selectTopHotCitiesAttractionData(@Param("city") String cityName,@Param("limit") Integer limit);

    List<Essay> selectByUserIdWithSelective(@Param("userId") Integer userId,@Param("count") Integer count);

    List<Essay> selectCityByUserIdWithSelective(@Param("userId") Integer userId,@Param("count") Integer count);


}