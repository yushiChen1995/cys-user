package com.cys.user.service;

import com.cys.user.contract.req.CreateUserInfoRequest;
import com.cys.user.contract.resp.QueryUserInfoResult;

/**
 * @author cys
 * @date 2019/6/28
 * 用户微服务
 */
public interface IUserService {

    /**
     * 根据手机号注册用户
     * @param request CreateUserInfoRequest
     * @return QueryUserInfoResult
     */
    QueryUserInfoResult registerUserByMobile(CreateUserInfoRequest request);
}
