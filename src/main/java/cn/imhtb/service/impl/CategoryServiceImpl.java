package cn.imhtb.service.impl;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.CategoryMapper;
import cn.imhtb.pojo.Category;
import cn.imhtb.service.ICategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse<String> add(String categoryName, @RequestParam(value = "parentId",defaultValue = "0") Integer parentId) {
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        int count = categoryMapper.insert(category);
        if (count>0){
            return ServerResponse.createByErrorMessage("添加失败");
        }else{
            return ServerResponse.createBySuccessMessage("添加成功");
        }
    }

    public ServerResponse<String> edit(Category category) {
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count>0){
            return ServerResponse.createByErrorMessage("编辑失败");
        }else{
            return ServerResponse.createBySuccessMessage("编辑成功");
        }
    }

    public ServerResponse<String> delete(Integer categoryId) {
        int count = categoryMapper.deleteByPrimaryKey(categoryId);
        if (count>0){
            return ServerResponse.createByErrorMessage("删除失败");
        }else{
            return ServerResponse.createBySuccessMessage("删除成功");
        }
    }


    @Override
    public List<Category> listLayer(Integer categoryId) {
        return categoryMapper.selectListLayerByParentId(categoryId);
    }

    @Override
    public ServerResponse<List<Category>> listDeep(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Category> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem);
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    private Set<Category> findChildCategory(Set<Category> categorySet , Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        List<Category> categoryList = categoryMapper.selectListLayerByParentId(categoryId);
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }


}
