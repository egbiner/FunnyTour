package cn.imhtb.service;

import cn.imhtb.common.ServerResponse;
import cn.imhtb.pojo.User;

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    User getInfoById(Integer uid);
}
