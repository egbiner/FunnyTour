package cn.imhtb.dao;

import cn.imhtb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectLogin(@Param("username") String username,@Param("password") String password);

    User selectPartInfoByPrimaryKey(Integer id);

    User selectByUsername(String s);

    String selectRoleByUsername(String username);
}