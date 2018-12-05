package cn.imhtb.service;


import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.Category;

import java.util.List;

public interface ICategoryService {

    ServerResponse<String> add(String categoryName, Integer parentId);

    ServerResponse<String> edit(Category category);

    ServerResponse<String> delete(Integer categoryId);

    List<Category> listLayer(Integer categoryId);

    ServerResponse<List<Category>> listDeep(Integer categoryId);
}
