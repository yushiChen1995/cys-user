package com.cys.user.service.impl;

import com.cys.user.contract.req.CreateUserInfoRequest;
import com.cys.user.contract.resp.QueryUserInfoResult;
import com.cys.user.pojo.User;
import com.cys.user.pojo.UserInfo;
import com.cys.user.service.IUserService;
import com.cys.user.service.inner.IUserDbService;
import com.cys.user.service.inner.IUserInfoDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cys
 * @date 2019/6/28
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDbService userDbService;
    @Autowired
    private IUserInfoDbService userInfoDbService;

    @Override
    public QueryUserInfoResult registerUserByMobile(CreateUserInfoRequest request) {
        QueryUserInfoResult result = new QueryUserInfoResult();

        User user = new User();
        user.setMobile(request.getMobile());
        userDbService.saveUser(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setRealName(request.getRealName());
        userInfo.setGender(request.getGender());
        userInfoDbService.saveUserInfo(userInfo, false);

        result.setUser(user);
        result.setUserInfo(userInfo);
        return result;
    }
}
