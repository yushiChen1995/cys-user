package com.cys.user.service.inner.impl;

import com.cys.user.dao.UserMapper;
import com.cys.user.model.UserExample;
import com.cys.user.pojo.User;
import com.cys.user.service.inner.IUserDbService;
import com.cys.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class UserDbServiceImpl implements IUserDbService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveUser(User user) {
        long current = System.currentTimeMillis();
        user.setUpdatedTime(current);
        if (StringUtils.isBlank(user.getPasswd())) {
            user.setPasswd(null);
        } else {
            user.setPasswd(Utilities.encodePassword(user.getPasswd()));
        }
        User dbUser = StringUtils.isBlank(user.getMobile()) ? null : findUserByMobile(user.getMobile());
        if (dbUser != null) {
            user.setId(dbUser.getId());
        }
        if (user.getId() == null) {
            user.setCreatedTime(current);
            try {
                userMapper.insertSelective(user);
            } catch (DuplicateKeyException e) {
                log.warn("用户插入唯一键冲突", e);
                dbUser = StringUtils.isBlank(user.getMobile()) ? null : findUserByMobile(user.getMobile());
                if (dbUser != null) {
                    user.setId(dbUser.getId());
                } else {
                    throw e;
                }
            }
        } else {
            updateUser(user);
        }
        return user;
    }

    @Override
    public User findUserByMobile(String mobile) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        example.setOrderByClause("mobile_status desc");
        List<User> list = userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public User updateUser(User user) {
        user.setCreatedTime(null);
        user.setUpdatedTime(System.currentTimeMillis());
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }
}
