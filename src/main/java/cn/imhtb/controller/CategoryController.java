package cn.imhtb.controller;


import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Category;
import cn.imhtb.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/category/")
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;

    @RequestMapping("add")
    @ResponseBody
    public ServerResponse<String> add(String categoryName, Integer parentId){
        if (StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return iCategoryService.add(categoryName,parentId);
    }

    @RequestMapping("edit")
    @ResponseBody
    public ServerResponse<String> edit(Category category){
        return iCategoryService.edit(category);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse<String> delete(Integer categoryId){
        return iCategoryService.delete(categoryId);
    }

    @RequestMapping("list_layer")
    @ResponseBody
    public ServerResponse<List<Category>> listLayer(@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        List<Category> list = iCategoryService.listLayer(categoryId);
        return ServerResponse.createBySuccess(list);
    }

    @RequestMapping("list_deep")
    @ResponseBody
    public ServerResponse<List<Category>> deep_list(@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        return iCategoryService.listDeep(categoryId);
    }
}
