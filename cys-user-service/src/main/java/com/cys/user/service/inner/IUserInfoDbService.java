package com.cys.user.service.inner;

import com.cys.user.pojo.UserInfo;

/**
 * @author cys
 * @date 2019/6/28
 */
public interface IUserInfoDbService {

    /**
     * 保存用户详情信息
     * @param userInfo userInfo
     * @param isCheckUserName 是否检查用户名
     * @return UserInfo
     */
    UserInfo saveUserInfo(UserInfo userInfo, boolean isCheckUserName);

    /**
     * 根据用户id获取用户详情信息
     * @param userId 用户id
     * @return UserInfo
     */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     * 更新
     * @param userInfo
     * @return
     */
    UserInfo updateUserInfo(UserInfo userInfo);
}
