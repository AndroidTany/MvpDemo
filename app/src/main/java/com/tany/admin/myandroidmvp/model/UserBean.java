package com.tany.admin.myandroidmvp.model;

import java.io.Serializable;

/**
 * Created by admin on 2016/11/29.
 */

public class UserBean implements Serializable,IUserModel{

    private String username;
    private String password;

    public UserBean(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPssword() {
        return password;
    }

    @Override
    public boolean isSuccess(String username, String password) {
        return username.equals(password);
    }
}
