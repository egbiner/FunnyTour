package cn.imhtb.controller;

import cn.imhtb.common.Const;
import cn.imhtb.pojo.Essay;
import cn.imhtb.service.IEssayService;
import cn.imhtb.utils.BaiduMapPointUtils;
import cn.imhtb.vo.BaiduLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.toArray;


@Controller
public class IndexController {

    @Autowired
    IEssayService iEssayService;

//    @RequestMapping("/")
//    public String index(Model model){
//        model.addAttribute(Const.CURRENT_USER,false);
//        return "index";
//    }
//
//    @RequestMapping("/user/login")
//    public String login(){
//        return "user/login";
//    }
//
//    @RequestMapping("/point")
//    @ResponseBody
//    public List<Essay> point(){
//        List<Essay> essays = iEssayService.selectAll();
//        for (Essay e:essays) {
//            System.out.println(e.getPosition());
//        }
//        return essays;
//    }

    @RequestMapping("/")
    public String index(){
        return "HotPoint";
    }

    @RequestMapping("/points")
    @ResponseBody
    public Object[] points(){
        List<BaiduLocation> baiduLocations = new ArrayList<>();
        List<Essay> essays = iEssayService.selectAll();
        for (Essay e:essays) {
            BaiduLocation baiduLocation = BaiduMapPointUtils.getBaiduLocation(Const.BAIDU_GET_POINT_URL_PREFIX+e.getPosition()+Const.BAIDU_GET_POINT_URL_SUFFIX+Const.BAIDU_AK);
            baiduLocation.setCount(100);
            baiduLocations.add(baiduLocation);
        }
        return baiduLocations.toArray();
    }

}
