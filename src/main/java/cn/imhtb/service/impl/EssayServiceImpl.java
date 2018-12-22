package cn.imhtb.service.impl;


import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.EssayMapper;
import cn.imhtb.pojo.Category;
import cn.imhtb.pojo.Essay;
import cn.imhtb.service.IEssayService;
import cn.imhtb.vo.ChartsVo;
import cn.imhtb.vo.CityVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

//    @Override
//    public ServerResponse<ChartsVo> getTopHotCities() {
//        List<Essay> list = essayMapper.selectTopHotCities(10);
//        Map<String,Integer> map = new HashMap<>();
//        for (Essay essay:list) {
//            if (StringUtils.isBlank(essay.getCity()))
//                continue;
//            if (map.containsKey(essay.getCity())){
//                map.put(essay.getCity(),map.get(essay.getCity())+1);
//            }else{
//                map.put(essay.getCity(),1);
//            }
//        }
//        int index = 0;
//        String[] category = new String[map.size()];
//        Integer[] data = new Integer[map.size()];
//        for (Map.Entry<String, Integer> en: map.entrySet()) {
//            category[index] = en.getKey();
//            data[index] = en.getValue();
//            index ++;
//        }
//        ChartsVo chartsVo = new ChartsVo();
//        chartsVo.setCategory(category);
//        chartsVo.setData(data);
//        return ServerResponse.createBySuccess(chartsVo);
//    }
    public ServerResponse<List<CityVo>> getTopHotCitiesData() {
        List<CityVo> list = essayMapper.selectTopHotCities(10);
//        Map<String,Integer> map = new HashMap<>();
//        for (Essay essay:list) {
//            if (StringUtils.isBlank(essay.getCity()))
//                continue;
//            if (map.containsKey(essay.getCity())){
//                map.put(essay.getCity(),map.get(essay.getCity())+1);
//            }else{
//                map.put(essay.getCity(),1);
//            }
//        }
//        int index = 0;
//        String[] category = new String[list.size()];
//        Integer[] data = new Integer[list.size()];
//        for (Map.Entry<String, Integer> en: map.entrySet()) {
//            category[index] = en.getKey();
//            data[index] = en.getValue();
//            index ++;
//        }
//        ChartsVo chartsVo = new ChartsVo();
//        chartsVo.setCategory(category);
//        chartsVo.setData(data);
        return ServerResponse.createBySuccess(list);
    }


}
