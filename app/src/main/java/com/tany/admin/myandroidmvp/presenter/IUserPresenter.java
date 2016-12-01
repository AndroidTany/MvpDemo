package com.tany.admin.myandroidmvp.presenter;

/**
 * Created by admin on 2016/11/29.
 */

public interface IUserPresenter {

    /**
     * 清空输入框文字
     */
    void setclearText();
    /**
     * 登录
     */
    void setLogin(String username,String password);
    /**
     * 设置progressBar是否显示
     */
    void setProgressBarVisiblity(boolean result);
}
