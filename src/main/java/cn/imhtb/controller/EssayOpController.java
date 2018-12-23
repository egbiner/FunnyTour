package cn.imhtb.controller;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.service.IEssayOpService;
import cn.imhtb.vo.ChartsVo;
import cn.imhtb.vo.HotEssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EssayOpController {
    @Autowired
    IEssayOpService iEssayOpService;

    /**
     * 获取近一个星期浏览剧增的文章
     */
    @RequestMapping("/hotViewLeap")
    @ResponseBody
    public ServerResponse<ChartsVo> hotViewLeap(Date startDate,Date endDate){
        List<HotEssayVo> list = iEssayOpService.getHotViewLeap(startDate,endDate);
        ChartsVo chartsVo = new ChartsVo();
        int index = 0;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (HotEssayVo c:list) {
            category[index] = sdf.format(c.getCreateTime());
            data[index] = c.getNumber();
            index++;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }


    /**
     * 获取近一个星期电赞剧增的文章
     */
    @RequestMapping("/hotVoteLeap")
    @ResponseBody
    public ServerResponse<ChartsVo> hotVoteLeap(Date startDate,Date endDate){
        List<HotEssayVo> list = iEssayOpService.getHotVoteLeap(startDate,endDate);
        ChartsVo chartsVo = new ChartsVo();
        int index = 0;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (HotEssayVo c:list) {
            category[index] = sdf.format(c.getCreateTime());
            data[index] = c.getNumber();
            index++;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }


    /**
     * 获取近一个星期评论剧增的文章
     */
    @RequestMapping("/hotCommentLeap")
    @ResponseBody
    public ServerResponse<ChartsVo> hotCommentLeap(Date startDate,Date endDate){
        List<HotEssayVo> list = iEssayOpService.getHotCommentLeap(startDate,endDate);
        ChartsVo chartsVo = new ChartsVo();
        int index = 0;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (HotEssayVo c:list) {
            category[index] = sdf.format(c.getCreateTime());
            data[index] = c.getNumber();
            index++;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }
}
