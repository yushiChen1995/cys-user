package com.cys.user.service.inner;

import com.cys.user.pojo.User;

/**
 * @author cys
 * @date 2019/6/28
 */
public interface IUserDbService {

    /**
     * 新增用户
     * @param user User
     * @return User
     */
    User saveUser(User user);

    /**
     * 根据手机号获取用户信息
     * @param mobile 手机号
     * @return User
     */
    User findUserByMobile(String mobile);

    /**
     * 更新用户
     * @param user User
     * @return User
     */
    User updateUser(User user);
}
