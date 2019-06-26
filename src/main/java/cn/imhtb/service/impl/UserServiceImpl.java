package cn.imhtb.service.impl;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.UserMapper;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        User user = userMapper.selectLogin(username,password);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        int rand = new Random().nextInt(1000);
        String avatar = "http://q1.qlogo.cn/g?b=qq&nk=49365"+rand+"&s=100";
        user.setCreateTime(new Date());
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setUpdateTime(new Date());
        user.setLevel(1);
        user.setAvatar(avatar);
        //TODO CheckValid
        int count = userMapper.insert(user);
        if (count>0){
            return ServerResponse.createBySuccessMessage("注册成功");
        }else {
            return ServerResponse.createByErrorMessage("注册失败");
        }
    }

    @Override
    public User getInfoById(Integer uid) {
        return userMapper.selectPartInfoByPrimaryKey(uid);
    }

    @Override
    public ServerResponse<User> update(User user) {
        user.setUpdateTime(new Date());
        user.setLevel(null);
        user.setPassword(null);
        user.setRole(null);
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count>0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }

    @Override
    public User findByUsername(String s) {
        return userMapper.selectByUsername(s);
    }

    @Override
    public String findRoleByUsername(String username) {
        return userMapper.selectRoleByUsername(username);
    }
}
