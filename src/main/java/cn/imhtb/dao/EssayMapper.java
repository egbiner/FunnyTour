package cn.imhtb.dao;

import cn.imhtb.pojo.Essay;
import cn.imhtb.vo.CityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EssayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Essay record);

    int insertSelective(Essay record);

    Essay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Essay record);

    int updateByPrimaryKey(Essay record);

    List<Essay> selectAll();

    List<Essay> selectByUserIdWithSelective(@Param("userId") Integer userId,@Param("count") Integer count);

    int selectCountByUserId(Integer userId);

    List<CityVo> selectTopHotCities(int limit);
}