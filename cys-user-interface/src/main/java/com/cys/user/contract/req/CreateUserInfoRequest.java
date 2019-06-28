package com.cys.user.contract.req;

import com.cys.constraints.Mobile;
import com.cys.utils.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author chenyushi
 */
@Data
public class CreateUserInfoRequest implements Serializable {

    /**
     * 手机号
     */
    @NotBlank
    @Mobile
    private String mobile;

    /**
     * 姓名
     */
    @NotBlank
    private String realName;

    /**
     * 性别：1 - 男 ，2 - 女
     */
    @NotBlank
    private Byte gender;


}
