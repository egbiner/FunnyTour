package cn.imhtb.service.impl;

import cn.imhtb.common.Const;
import cn.imhtb.common.ServerResponse;
import cn.imhtb.dao.UserMapper;
import cn.imhtb.pojo.User;
import cn.imhtb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        user.setCreateTime(new Date());
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setUpdateTime(new Date());
        //TODO CheckValid
        int count = userMapper.insert(user);
        if (count>0){
            return ServerResponse.createBySuccessMessage("注册成功");
        }else {
            return ServerResponse.createByErrorMessage("注册失败");
        }
    }
}
