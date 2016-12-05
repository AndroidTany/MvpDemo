package com.tany.admin.myandroidmvp;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tany.admin.myandroidmvp.fragment.CommentFragment;
import com.tany.admin.myandroidmvp.slidebar.ColorBar;
import com.tany.admin.myandroidmvp.slidebar.DisplayUtil;
import com.tany.admin.myandroidmvp.slidebar.DrawableBar;
import com.tany.admin.myandroidmvp.slidebar.ScrollBar;
import com.tany.admin.myandroidmvp.slidebar.SpringBar;
import com.tany.admin.myandroidmvp.transition.IndicatorViewPager;
import com.tany.admin.myandroidmvp.transition.OnTransitionTextListener;
import com.tany.admin.myandroidmvp.transition.ScrollIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2016/11/30.
 */

public class MoreTabActivity extends FragmentActivity {

    @BindView(R.id.moretab_indicator)
    ScrollIndicatorView scrollIndicatorView;
    @BindView(R.id.moretab_viewPager)
    ViewPager viewPager;

    private IndicatorViewPager indicatorViewPager;
    private List<Fragment> list = new ArrayList<>();
    private Unbinder unbinder;
    private int unSelectColor;
    private int unSelectTextColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_tabui);
        unbinder = ButterKnife.bind(this);
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        list.add(new CommentFragment());
        if (getIntent().getIntExtra("type", -1) == 1) {
            float unSelectSize = 12;
            float selectSize = unSelectSize * 1.3f;
            scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF2196F3, Color.GRAY).setSize(selectSize, unSelectSize));
            scrollIndicatorView.setScrollBar(new ColorBar(this, 0xFF2196F3, 4));
            viewPager.setOffscreenPageLimit(4);
            indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
            indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
        } else if (getIntent().getIntExtra("type", -1) == 0) {
            int selectColor = Color.parseColor("#f8f8f8");
            unSelectColor = Color.parseColor("#010101");
            scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor));
            scrollIndicatorView.setScrollBar(new SpringBar(getApplicationContext(), Color.BLUE));
            viewPager.setOffscreenPageLimit(4);
            indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
            indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
        } else if (getIntent().getIntExtra("type", -1) == 2){
            scrollIndicatorView.setBackgroundColor(Color.RED);
            scrollIndicatorView.setScrollBar(new DrawableBar(this, R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
                @Override
                public int getHeight(int tabHeight) {
                    return tabHeight - dipToPix(12);
                }

                @Override
                public int getWidth(int tabWidth) {
                    return tabWidth - dipToPix(12);
                }
            });

            unSelectTextColor = Color.WHITE;
            // 设置滚动监听
            scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, unSelectTextColor));
            viewPager.setOffscreenPageLimit(4);
            indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
            indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
        } else {
            scrollIndicatorView.setBackgroundColor(Color.RED);
            scrollIndicatorView.setScrollBar(new DrawableBar(this, R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
                @Override
                public int getHeight(int tabHeight) {
                    return tabHeight - dipToPix(12);
                }

                @Override
                public int getWidth(int tabWidth) {
                    return tabWidth - dipToPix(12);
                }
            });

            unSelectTextColor = Color.WHITE;
            // 设置滚动监听
            scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, unSelectTextColor));
            viewPager.setOffscreenPageLimit(4);
            indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
            indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), list));

            scrollIndicatorView.setPinnedTabView(true);
            // 设置固定tab的shadow，这里不设置的话会使用默认的shadow绘制
            scrollIndicatorView.setPinnedShadow(R.mipmap.tabshadow, dipToPix(4));
            scrollIndicatorView.setPinnedTabBgColor(Color.RED);
        }
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] versions = {"Cupcake", "Donut", "Éclair", "Honeycomb","Tany"};
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

            int witdh = getTextWidth(textView);
            int padding = DisplayUtil.dipToPix(getApplicationContext(), 20);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.3f) + padding);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return mlist.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    public int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

    public void userCommit(){

    }
}
