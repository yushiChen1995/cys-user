package com.cys.user.service.impl;

import com.cys.user.contract.req.CreateUserInfoRequest;
import com.cys.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author cys
 * @date 2019/6/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Test
    public void registerUserByMobile() {
        CreateUserInfoRequest request = new CreateUserInfoRequest();
        request.setMobile("18520201995");
        request.setRealName("石头");
        userService.registerUserByMobile(request);
    }
}