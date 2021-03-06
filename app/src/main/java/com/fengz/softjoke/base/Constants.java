package com.fengz.softjoke.base;

import android.text.TextUtils;

import com.fengz.softjoke.business1.model.entity.UserModel;

/**
 * 创建时间：2019/1/18
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：常量存储
 */
public class Constants {
    private static UserModel user;

    public static UserModel getUser() {
        return user;
    }

    public static void setUser(UserModel user) {
        Constants.user = user;
    }

    public static boolean isLogin(){
        return !(user == null || TextUtils.isEmpty(user.getPhone()));
    }
}
