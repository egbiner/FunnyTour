package cn.imhtb.service.impl;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.EssayMapper;
import cn.imhtb.dao.EssayOpMapper;
import cn.imhtb.pojo.Essay;
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
    @Autowired
    EssayMapper essayMapper;
    @Override
    public ServerResponse<String> add(EssayOp essayOp) {
        //用户是否点赞过了
        if (essayOp.getEssayOp().equals(Const.EssayOp.ESSAY_VOTE)){
            if (checkOp(essayOp.getEssayId(),essayOp.getEssayOp(),essayOp.getUserId())){
                return ServerResponse.createByErrorMessage("您已经投过票咯");
            }
        }
        essayOp.setCreateTime(new Date());
        int count = essayOpMapper.insert(essayOp);
        if (count>0){
            return ServerResponse.createBySuccessMessage("操作成功");
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }

    @Override
    public Boolean checkOp(int essayId,int essayOp,int userId){
        int count = essayOpMapper.checkOp(essayId,essayOp,userId);
        return count > 0;
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


    /**
     * 获取总数前limit的文章
     * @param limit
     * @return
     */
    @Override
    public List<HotEssayVo> getHotEssaysByOpAndLimit(int essayOp, int limit) {
        List<HotEssayVo> list = essayOpMapper.selectHotEssaysByOpAndLimit(essayOp,limit);
        return list;
    }

    @Override
    public List<HotEssayVo> getHotEssaysVoByOpAndLimit(int essayOp, int limit) {
        List<HotEssayVo> list = essayOpMapper.selectHotEssaysByOpAndLimit(essayOp,limit);
        //返回EssayVo只有id,number 还差city,title
        for (HotEssayVo h:list) {
            Essay essay = essayMapper.selectByPrimaryKey(h.getId());
            h.setCity(essay.getCity());
            h.setTitle(essay.getTitle());
        }
        return list;
    }


    public Date getLastWeek(){
        Calendar cal=Calendar.getInstance();
//        cal.add(Calendar.DATE,-8);
        cal.add(Calendar.DATE,-15);
        return cal.getTime();
    }
}
