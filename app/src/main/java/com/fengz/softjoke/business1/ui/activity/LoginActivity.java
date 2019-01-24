package com.fengz.softjoke.business1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fengz.softjoke.R;
import com.fengz.softjoke.base.mvp.APresenter;
import com.fengz.softjoke.base.mvp.BaseActivity;
import com.fengz.softjoke.business1.contract.LoginContract;
import com.fengz.softjoke.business1.presenter.LoginPresenter;
import com.fengz.softjoke.business1.ui.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    public static final String PARA_FORM_MAIN = "para_form_main";

    public static Intent getCallingIntent(@NonNull Context context, boolean formMain) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(PARA_FORM_MAIN, formMain);
        return intent;
    }

    @Inject
    @APresenter
    LoginContract.Presenter mPresenter;

    @Inject
    Navigator mNavigator;

    @BindView(R.id.et_user_login_act)
    EditText mEtUser;
    @BindView(R.id.et_password_login_act)
    EditText mEtPassword;
    @BindView(R.id.btn_login_login_act)
    Button mBtnLogin;

    private boolean canBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        canBack = getIntent().getBooleanExtra(PARA_FORM_MAIN, false);
        setTitle("");
        setHomeAsUp(R.drawable.ic_cancel_gray);
        reloadLastUser();
    }

    private void reloadLastUser() {
        String[] userInfo = mPresenter.getLastUserInfo();
        mEtUser.setText(userInfo[0]);
        mEtPassword.setText(userInfo[1]);
    }

    @Override
    public void clearPwd() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        showLoadingB(true);
    }

    @Override
    public void dismissLoading() {
        dismissLoadingB();
    }

    @Override
    public void loginSuccess() {
        if (!canBack) {
            mNavigator.navigator2MainAct(this);
        }
        onBackPressed();
    }

    private boolean check(String name, String pwd) {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd));
    }

    @OnClick({R.id.btn_login_login_act, R.id.tv_skip_login_act})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login_act:
                String name = mEtUser.getText().toString();
                String pwd = mEtPassword.getText().toString();
                if (check(name, pwd)) {
                    mPresenter.login(name, pwd);
                }
                break;
            case R.id.tv_skip_login_act:
                // 假设登录处理
                loginSuccess();
                break;
        }
    }

    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED,
            value = {R.id.et_user_login_act, R.id.et_password_login_act})
    public void onTextChanged() {
        String name = mEtUser.getText().toString();
        String pwd = mEtPassword.getText().toString();
        mBtnLogin.setEnabled(check(name, pwd));
    }
}
