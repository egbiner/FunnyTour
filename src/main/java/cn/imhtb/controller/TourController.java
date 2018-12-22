package cn.imhtb.controller;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.service.IEssayService;
import cn.imhtb.vo.ChartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TourController {

    @Autowired
    IEssayService iEssayService;

    @RequestMapping("/recommend")
    public String to_recommend(Model model){
//        ChartsVo chartsVo = iEssayService.getTopHotCities().getData();
//        List<CityVo> list = new ArrayList<>();
//        for (int i = 0; i < chartsVo.getCategory().length; i++) {
//            CityVo cityVo = new CityVo();
//            cityVo.setName(chartsVo.getCategory()[i]);
//            cityVo.setNumber(chartsVo.getData()[i]);
//            list.add(cityVo);
//        }
        model.addAttribute("hotCities",iEssayService.getTopHotCitiesData().getData());
        return "recommend";
    }

    /**
     *  获取热点城市
     */
    @RequestMapping("/hotCities")
    @ResponseBody
    public ServerResponse<ChartsVo> hotCities(){
//        ServerResponse<ChartsVo> serverResponse = iEssayService.getTopHotCities();
//        return ServerResponse.createBySuccess(serverResponse.getData());
        return null;
    }

}
