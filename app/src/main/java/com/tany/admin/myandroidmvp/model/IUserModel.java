package com.tany.admin.myandroidmvp.model;

/**
 * Created by admin on 2016/11/29.
 */

public interface IUserModel {

    String getUsername();
    String getPssword();
    boolean isSuccess(String username,String password);
}
