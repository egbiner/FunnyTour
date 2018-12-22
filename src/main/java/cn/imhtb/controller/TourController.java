package cn.imhtb.controller;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.service.ICommentService;
import cn.imhtb.service.IEssayService;
import cn.imhtb.vo.ChartsVo;
import cn.imhtb.vo.CityVo;
import cn.imhtb.vo.HotEssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class TourController {

    @Autowired
    IEssayService iEssayService;
    @Autowired
    ICommentService iCommentService;

    @RequestMapping("/recommend")
    public String to_recommend(Model model){
        model.addAttribute("hotCities",iEssayService.getTopHotCitiesData().getData());
        model.addAttribute("hotCommentsEssays",iCommentService.getHotCommentEssay(10));
        model.addAttribute("hotVotesEssays",iEssayService.getHotVotesEssay(10));
        return "recommend";
    }

    /**
     *  获取热点城市
     */
    @RequestMapping("/hotCities")
    @ResponseBody
    public ServerResponse<ChartsVo> hotCities(){
        ChartsVo chartsVo = new ChartsVo();
        List<CityVo> list = iEssayService.getTopHotCitiesData().getData();
        //e-charts数据相反
        int index = list.size()-1;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        for (CityVo c:list) {
            category[index] = c.getName();
            data[index] = c.getNumber();
            index--;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }

    /**
     * 获取热评文章
     */
    @RequestMapping("/hotComments")
    @ResponseBody
    public ServerResponse<ChartsVo> hotComments(){
        List<HotEssayVo> list = iCommentService.getHotCommentEssay(10);
        ChartsVo chartsVo = new ChartsVo();
        int index = list.size()-1;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        for (HotEssayVo c:list) {
            category[index] = c.getId().toString();
            data[index] = c.getNumber();
            index--;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }


    /**
     * 获取点赞最高文章
     */
    @RequestMapping("/hotVotes")
    @ResponseBody
    public ServerResponse<ChartsVo> hotVotes(){
        List<HotEssayVo> list = iEssayService.getHotVotesEssay(10);
        ChartsVo chartsVo = new ChartsVo();
        int index = list.size()-1;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        for (HotEssayVo c:list) {
            category[index] = c.getId().toString();
            data[index] = c.getNumber();
            index--;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }

    /**
     * 获取浏览最高文章
     */
    @RequestMapping("/hotViews")
    @ResponseBody
    public ServerResponse<ChartsVo> hotViews(){
        List<HotEssayVo> list = iEssayService.getHotViewsEssay(10);
        ChartsVo chartsVo = new ChartsVo();
        int index = list.size()-1;
        String[] category = new String[list.size()];
        Integer[] data = new Integer[list.size()];
        for (HotEssayVo c:list) {
            category[index] = c.getId().toString();
            data[index] = c.getNumber();
            index--;
        }
        chartsVo.setCategory(category);
        chartsVo.setData(data);
        return ServerResponse.createBySuccess(chartsVo);
    }

}
