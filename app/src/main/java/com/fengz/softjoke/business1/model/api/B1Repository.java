package com.fengz.softjoke.business1.model.api;

import com.fengz.softjoke.business1.model.entity.JokeModule;
import com.fengz.softjoke.business1.model.entity.UserModel;
import com.fengz.softjoke.http.TransformerHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * 创建时间：2019/1/2
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：business1 Service 数据仓库
 */
@Singleton
public class B1Repository {

    private UserService mUserService;

    @Inject
    public B1Repository(UserService userService) {
        mUserService = userService;
    }

    public Observable<UserModel> registerUser(String phone, String password) {
        return mUserService.register("00d91e8e0cca2b76f515926a36db68f5", phone, password)
                .compose(TransformerHelper.observableTransformer());
    }

    public Observable<UserModel> loginUser(String phone, String password) {
        return mUserService.login("00d91e8e0cca2b76f515926a36db68f5", phone, password)
                .compose(TransformerHelper.observableTransformer());
    }

    public Observable<List<JokeModule>> getJokes(int type, int page) {
        return mUserService.getJokes(type,page)
                .compose(TransformerHelper.observableTransformer());
    }
}
