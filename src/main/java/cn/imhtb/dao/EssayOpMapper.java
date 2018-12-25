package cn.imhtb.dao;

import cn.imhtb.pojo.EssayOp;
import cn.imhtb.vo.HotEssayVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface EssayOpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EssayOp record);

    int insertSelective(EssayOp record);

    EssayOp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EssayOp record);

    int updateByPrimaryKey(EssayOp record);

    List<HotEssayVo> selectHotLeapList(@Param("essayId") int essayId,@Param("essayOp") int essayOp,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    int selectHotLeapEssayIdByOp(@Param("essayOp") int essayOp,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    int selectOpCountByEssayIdAndOp(@Param("essayId")Integer essayId,@Param("essayOp") int essayOp);

    int checkOp(@Param("essayId") Integer essayId,@Param("essayOp") Integer essayOp,@Param("userId") Integer userId);

    List<HotEssayVo> selectHotEssaysByOpAndLimit(@Param("essayOp")int essayOp,@Param("limit") int limit);

}