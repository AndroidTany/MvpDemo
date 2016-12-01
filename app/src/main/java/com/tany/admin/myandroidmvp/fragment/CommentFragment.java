package com.tany.admin.myandroidmvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.tany.admin.myandroidmvp.R;

/**
 * Created by admin on 2016/11/30.
 */

public class CommentFragment extends LazyFragment{

    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.comment_fragment);
    }
}
