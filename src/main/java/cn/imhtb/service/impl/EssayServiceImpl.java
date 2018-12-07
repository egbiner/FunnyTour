package cn.imhtb.service.impl;


import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.EssayMapper;
import cn.imhtb.pojo.Essay;
import cn.imhtb.service.IEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("iEssayService")
public class EssayServiceImpl implements IEssayService {
    @Autowired
    EssayMapper essayMapper;

    @Override
    public Essay select(Integer id) {
        return essayMapper.selectByPrimaryKey(id);
    }

    @Override
    public ServerResponse<String> add(Essay essay) {
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


}
