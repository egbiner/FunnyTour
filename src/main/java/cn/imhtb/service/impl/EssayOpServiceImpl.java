package cn.imhtb.service.impl;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.EssayOpMapper;
import cn.imhtb.pojo.EssayOp;
import cn.imhtb.service.IEssayOpService;
import cn.imhtb.vo.HotEssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("iEssayOpService")
public class EssayOpServiceImpl implements IEssayOpService {
    @Autowired
    EssayOpMapper essayOpMapper;
    @Override
    public ServerResponse<String> add(EssayOp essayOp) {
        essayOp.setCreateTime(new Date());
        int count = essayOpMapper.insert(essayOp);
        if (count>0){
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public List<HotEssayVo> getHotViewLeap(Date startDate, Date endDate) {
        if (startDate==null||endDate==null){
            endDate=new Date();
            startDate = getLastWeek();
        }
        int hotViewLeapEssayId = essayOpMapper.selectHotLeapEssayIdByOp(Const.EssayOp.ESSAY_VIEW,startDate,endDate);
        List<HotEssayVo> list = essayOpMapper.selectHotLeapList(hotViewLeapEssayId,Const.EssayOp.ESSAY_VIEW,startDate,endDate);
        return list;
    }

    @Override
    public List<HotEssayVo> getHotVoteLeap(Date startDate, Date endDate) {
        if (startDate==null||endDate==null){
            endDate=new Date();
            startDate = getLastWeek();
        }
        int hotViewLeapEssayId = essayOpMapper.selectHotLeapEssayIdByOp(Const.EssayOp.ESSAY_VOTE,startDate,endDate);
        List<HotEssayVo> list = essayOpMapper.selectHotLeapList(hotViewLeapEssayId,Const.EssayOp.ESSAY_VOTE,startDate,endDate);
        return list;
    }

    @Override
    public List<HotEssayVo> getHotCommentLeap(Date startDate, Date endDate) {
        if (startDate==null||endDate==null){
            endDate=new Date();
            startDate = getLastWeek();
        }
        int hotViewLeapEssayId = essayOpMapper.selectHotLeapEssayIdByOp(Const.EssayOp.ESSAY_COMMENT,startDate,endDate);
        List<HotEssayVo> list = essayOpMapper.selectHotLeapList(hotViewLeapEssayId,Const.EssayOp.ESSAY_COMMENT,startDate,endDate);
        return list;
    }

    public Date getLastWeek(){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-8);
        return cal.getTime();
    }
}
