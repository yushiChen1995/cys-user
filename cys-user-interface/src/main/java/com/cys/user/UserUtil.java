package com.cys.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cys
 * @date 2019/6/28
 */
public class UserUtil {

    public static Boolean regexUserName(String name) {
        Pattern p = null;
        Matcher m = null;
        Boolean retValue = true;
        //用户名只能包含中文，英文，数字的组合，不能是纯数字，不能含有@符号
        String numRegex = "[0-9]*";
        p = Pattern.compile(numRegex);
        m = p.matcher(name);
        if (m.matches()) {
            return false;
        }

        String atRegex = ".*@.*";
        p = Pattern.compile(atRegex);
        m = p.matcher(name);
        if (m.matches()) {
            return false;
        }

        String nameRegex = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
        p = Pattern.compile(nameRegex);
        m = p.matcher(name);
        retValue = m.matches();

        return retValue;
    }


}
