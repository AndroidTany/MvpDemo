package com.tany.admin.myandroidmvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tany.admin.myandroidmvp.widget.WaveView3;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2016/12/5.
 */

public class WaveActivity extends Activity{

    @BindView(R.id.wave_view)
    WaveView3 waveView3;
    @BindView(R.id.image)
    ImageView imageView;
    public Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave_activity);
        unbinder = ButterKnife.bind(this);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2,-2);
        lp.gravity = Gravity.BOTTOM| Gravity.CENTER;
        waveView3.setOnWaveAnimationListener(new WaveView3.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0,0,0,(int)y+2);
                imageView.setLayoutParams(lp);
            }
        });
    }
}
