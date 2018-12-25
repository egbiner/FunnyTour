package cn.imhtb.service.impl;


import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.CommentMapper;
import cn.imhtb.dao.EssayMapper;
import cn.imhtb.dao.EssayOpMapper;
import cn.imhtb.dao.UserMapper;
import cn.imhtb.pojo.Essay;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IEssayService;
import cn.imhtb.vo.CityVo;
import cn.imhtb.vo.EssayVo;
import cn.imhtb.vo.HotEssayVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iEssayService")
public class EssayServiceImpl implements IEssayService {
    @Autowired
    EssayMapper essayMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    EssayOpMapper essayOpMapper;

    @Override
    public EssayVo select(Integer id) {
        Essay e =essayMapper.selectByPrimaryKey(id);
        EssayVo essayVo = new EssayVo();
        User user = userMapper.selectByPrimaryKey(e.getUserId());
        essayVo.setId(e.getId());
        essayVo.setTitle(e.getTitle());
        essayVo.setContent(e.getContent());
        essayVo.setCity(e.getCity());
        essayVo.setPosition(e.getPosition());
        essayVo.setCreateTime(e.getCreateTime());

        essayVo.setUserAvatar(user.getAvatar());
        essayVo.setUsername(user.getName());
        essayVo.setUserId(e.getUserId());

        int commentNum = commentMapper.selectCountByEssayId(e.getId());
        essayVo.setCommentNum(commentNum);
        int viewCount = essayOpMapper.selectOpCountByEssayIdAndOp(e.getId(), Const.EssayOp.ESSAY_VIEW);
        essayVo.setView(viewCount);
        int voteCount = essayOpMapper.selectOpCountByEssayIdAndOp(e.getId(), Const.EssayOp.ESSAY_VOTE);
        essayVo.setVote(voteCount);

        return essayVo;
    }

    @Override
    public ServerResponse<String> add(Essay essay) {
        essay.setView(0);
        essay.setVote(0);
        essay.setCreateTime(new Date());
        essay.setUpdateTime(new Date());
        int count = essayMapper.insert(essay);
        if (count>0){
            return ServerResponse.createBySuccessMessage("发表成功");
        }else {
            return ServerResponse.createByErrorMessage("发表失败");
        }
    }

    @Override
    public ServerResponse<String> edit(Essay essay) {
        essay.setUpdateTime(new Date());
        int count = essayMapper.updateByPrimaryKeySelective(essay);
        if (count>0){
            return ServerResponse.createBySuccessMessage("编辑成功");
        }else {
            return ServerResponse.createByErrorMessage("编辑失败");
        }
    }

    @Override
    public ServerResponse<String> delete(Integer id) {
        int count = essayMapper.deleteByPrimaryKey(id);
        if (count>0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public List<Essay> selectAll() {
        return essayMapper.selectAll();
    }

    @Override
    public List<Essay> selectByUserIdWithSelective(Integer userId,Integer count) {
        return essayMapper.selectByUserIdWithSelective(userId,count);
    }

    @Override
    public int selectCountByUserId(Integer userId) {
        return essayMapper.selectCountByUserId(userId);
    }

    public ServerResponse<List<CityVo>> getTopHotCitiesData() {
        List<CityVo> list = essayMapper.selectTopHotCities(10);
        return ServerResponse.createBySuccess(list);
    }

//    @Override
//    public List<EssayVo> selectAllVo() {
//        List<Essay> list = essayMapper.selectAll();
//        List<EssayVo> listVo = new ArrayList<>();
//        for (Essay e:list) {
//            User user = userMapper.selectByPrimaryKey(e.getUserId());
//            EssayVo essayVo = new EssayVo();
//            essayVo.setId(e.getId());
//            essayVo.setTitle(e.getTitle());
//            essayVo.setContent(e.getContent());
//            essayVo.setCreateTime(e.getCreateTime());
//
//            essayVo.setUserAvatar(user.getAvatar());
//            essayVo.setUsername(user.getName());
//            essayVo.setUserId(e.getUserId());
//
//            essayVo.setCommentNum(commentMapper.selectCountByEssayId(e.getId()));
//            essayVo.setView(e.getView());
//
//            listVo.add(essayVo);
//        }
//        return listVo;
//    }

    @Override
    public List<EssayVo> selectAllVoWithLimit(Integer limit) {
        List<Essay> list = essayMapper.selectWithLimit(limit);
        List<EssayVo> listVo = new ArrayList<>();
        for (Essay e:list) {
            User user = userMapper.selectByPrimaryKey(e.getUserId());
            EssayVo essayVo = new EssayVo();
            essayVo.setId(e.getId());
            essayVo.setTitle(e.getTitle());
            essayVo.setContent(e.getContent());
            essayVo.setCreateTime(e.getCreateTime());

            essayVo.setUserAvatar(user.getAvatar());
            essayVo.setUsername(user.getName());
            essayVo.setUserId(e.getUserId());

            essayVo.setCommentNum(commentMapper.selectCountByEssayId(e.getId()));
            int viewCount = essayOpMapper.selectOpCountByEssayIdAndOp(e.getId(), Const.EssayOp.ESSAY_VIEW);
            essayVo.setView(viewCount);

            listVo.add(essayVo);
        }
        return listVo;
    }

    /**
     * 根据essay的vote获取（过时）
     * @param limit
     * @return
     */
    @Override
    public List<HotEssayVo> getHotVotesEssay(int limit) {
        return essayMapper.selectHotVotesEssay(limit);
    }
    /**
     * 根据essay的view获取（过时）
     * @param limit
     * @return
     */
    @Override
    public List<HotEssayVo> getHotViewsEssay(int limit) {
        return  essayMapper.selectHotViewsEssay(limit);
    }

    @Override
    public ServerResponse<String> vote(Integer id) {
        int count = essayMapper.vote(id);
        if (count>0){
            return ServerResponse.createBySuccessMessage("投票成功");
        }else {
            return ServerResponse.createByErrorMessage("投票失败");
        }
    }

    @Override
    public ServerResponse<String> updateView(Integer essayId) {
        int count = essayMapper.updateViewByEssayId(essayId);
        if (count>0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }


}
