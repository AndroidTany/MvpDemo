package com.tany.admin.myandroidmvp;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tany.admin.myandroidmvp.fragment.CommentFragment;
import com.tany.admin.myandroidmvp.slidebar.ColorBar;
import com.tany.admin.myandroidmvp.slidebar.LayoutBar;
import com.tany.admin.myandroidmvp.slidebar.ScrollBar;
import com.tany.admin.myandroidmvp.transition.Indicator;
import com.tany.admin.myandroidmvp.transition.IndicatorViewPager;
import com.tany.admin.myandroidmvp.transition.OnTransitionTextListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2016/11/30.
 */

public class CommentActivity extends FragmentActivity {


    @BindView(R.id.fragment_tabmain_indicator)
    Indicator indicator;
    @BindView(R.id.moretab_viewPager)
    ViewPager viewPager;

    private Unbinder unbinder;
    private IndicatorViewPager indicatorViewPager;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_ui);
        unbinder = ButterKnife.bind(this);
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        Resources res = getResources();
        switch (getIntent().getIntExtra("type",-1)) {
            case 0:
                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));
                break;
            case 1:
                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
                break;
            case 2:
                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5, ScrollBar.Gravity.TOP));
                break;
            case 3:
                indicator.setScrollBar(new LayoutBar(getApplicationContext(), R.layout.layout_slidebar, ScrollBar.Gravity.CENTENT_BACKGROUND));
                break;
        }
        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = res.getColor(R.color.tab_top_text_2);
        int unSelectColor = res.getColor(R.color.tab_top_text_1);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] versions = {"Cupcake", "Donut", "Éclair", "Honeycomb"};
        List<Fragment> mlist;

        public MyAdapter(FragmentManager fragmentManager, List<Fragment> mlist) {
            super(fragmentManager);
            this.mlist = mlist;
        }

        @Override
        public int getCount() {
            return versions.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(versions[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return mlist.get(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
