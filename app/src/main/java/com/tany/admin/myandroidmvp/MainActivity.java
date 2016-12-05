package com.tany.admin.myandroidmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tany.admin.myandroidmvp.presenter.IUserPresenter;
import com.tany.admin.myandroidmvp.presenter.UserPresenter;
import com.tany.admin.myandroidmvp.view.IView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVP小测试
 * Tany
 */
public class MainActivity extends AppCompatActivity implements IView{

    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.btn_login_clear)
    Button btnLoginClear;
    @BindView(R.id.progress_login)
    ProgressBar progressLogin;

    public Unbinder unbinder;
    public IUserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mPresenter = new UserPresenter(this);
        mPresenter.setProgressBarVisiblity(false);
    }

    @OnClick({R.id.btn_login_login, R.id.btn_login_clear,R.id.btn_xian,R.id.btn_yuan,R.id.btn_tuoyuan,R.id.btn_guding,R.id.btn_a,R.id.btn_b,R.id.btn_c,R.id.btn_d})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                mPresenter.setProgressBarVisiblity(true);
                mPresenter.setLogin(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
                break;
            case R.id.btn_login_clear:
//                onClesrText();
                startActivity(new Intent(MainActivity.this,WaveActivity.class));
                break;
            case R.id.btn_xian:
                startActivity(new Intent(MainActivity.this,MoreTabActivity.class).putExtra("type",1));
                break;
            case R.id.btn_yuan:
                startActivity(new Intent(MainActivity.this,MoreTabActivity.class).putExtra("type",0));
                break;
            case R.id.btn_tuoyuan:
                startActivity(new Intent(MainActivity.this,MoreTabActivity.class).putExtra("type",2));
                break;
            case R.id.btn_guding:
                startActivity(new Intent(MainActivity.this,MoreTabActivity.class).putExtra("type",3));
                break;
            case R.id.btn_a:
                startActivity(new Intent(MainActivity.this,CommentActivity.class).putExtra("type",0));
                break;
            case R.id.btn_b:
                startActivity(new Intent(MainActivity.this,CommentActivity.class).putExtra("type",1));
                break;
            case R.id.btn_c:
                startActivity(new Intent(MainActivity.this,CommentActivity.class).putExtra("type",2));
                break;
            case R.id.btn_d:
                startActivity(new Intent(MainActivity.this,CommentActivity.class).putExtra("type",3));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClesrText() {
        etLoginUsername.setText("");
        etLoginPassword.setText("");
    }

    @Override
    public void onSuccessAndRefuslView(boolean result) {
        Toast.makeText(MainActivity.this,result?"成功":"失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowDIalog(boolean result) {
        progressLogin.setVisibility(result?View.VISIBLE:View.GONE);
    }
}
