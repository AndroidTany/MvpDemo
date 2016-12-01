package com.tany.admin.myandroidmvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.tany.admin.myandroidmvp.model.IUserModel;
import com.tany.admin.myandroidmvp.model.UserBean;
import com.tany.admin.myandroidmvp.view.IView;

/**
 * Created by admin on 2016/11/29.
 */

public class UserPresenter implements IUserPresenter{

    IView mIView;
    IUserModel mIModel;
    Handler mHandler;

    public UserPresenter(IView iView) {
        mIView = iView;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void setclearText() {
        mIView.onClesrText();
    }

    @Override
    public void setLogin(String username, String password) {
        mIModel = new UserBean(username, password);
        final boolean isSuccess = mIModel.isSuccess(username, password);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIView.onSuccessAndRefuslView(isSuccess);
                mIView.onShowDIalog(!isSuccess);
            }
        }, 3000);
    }

    @Override
    public void setProgressBarVisiblity(boolean result) {
        mIView.onShowDIalog(result);
    }
}
