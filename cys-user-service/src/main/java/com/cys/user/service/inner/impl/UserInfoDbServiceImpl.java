package com.cys.user.service.inner.impl;

import com.cys.user.UserUtil;
import com.cys.user.dao.UserInfoMapper;
import com.cys.user.model.UserInfoExample;
import com.cys.user.pojo.UserInfo;
import com.cys.user.service.inner.IUserInfoDbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cys
 * @date 2019/6/28
 */
@Service
@Slf4j
public class UserInfoDbServiceImpl implements IUserInfoDbService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo, boolean isCheckUserName) {
        UserInfo dbUserInfo = this.getUserInfoByUserId(userInfo.getUserId());
        long current = System.currentTimeMillis();
        userInfo.setUpdatedTime(current);
        if (userInfo.getRealName() != null) {
            if (isCheckUserName && !UserUtil.regexUserName(userInfo.getRealName())) {
                throw new RuntimeException("用户姓名不合法");
            }
        }
        if (userInfo.getNickName() != null) {
            userInfo.setNickName(userInfo.getNickName().trim());
            if (isCheckUserName && !UserUtil.regexUserName(userInfo.getNickName())) {
                throw new RuntimeException("用户昵称不合法");
            }
        }
        if (dbUserInfo != null) {
            userInfo.setId(dbUserInfo.getId());
            this.updateUserInfo(userInfo);
        } else {
            userInfo.setCreatedTime(current);
            try {
                userInfoMapper.insert(userInfo);
            } catch (DuplicateKeyException e) {
                log.warn("保存userInfo唯一性冲突", e);
                dbUserInfo = this.getUserInfoByUserId(userInfo.getUserId());
                if (dbUserInfo != null) {
                    userInfo.setId(dbUserInfo.getId());
                }
            }
        }
        return userInfo;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUserIdEqualTo(userId);
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return userInfo;
    }
}
