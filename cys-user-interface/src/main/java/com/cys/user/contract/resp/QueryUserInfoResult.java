package com.cys.user.contract.resp;

import com.cys.user.pojo.OauthUser;
import com.cys.user.pojo.User;
import com.cys.user.pojo.UserInfo;
import lombok.Data;

import java.io.Serializable;


/**
 * @author chenyushi
 * @date 2019-06-27
 */
@Data
public class QueryUserInfoResult implements Serializable {

    private String token;

    private User user;

    private UserInfo userInfo;

    private OauthUser oauthUser;



}
