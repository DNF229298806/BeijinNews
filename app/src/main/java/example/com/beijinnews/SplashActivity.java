package example.com.beijinnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import example.com.beijinnews.activity.GuideActivity;
import example.com.beijinnews.activity.MainActivity;
import example.com.beijinnews.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {

    /**
     * 静态常量
     */
    public static final String START_MAIN = "start_main";
    private RelativeLayout rl_splash_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash_root = findViewById(R.id.rl_splash_root);
        //设置渐变动画，缩放动画，旋转动画 不透明度
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        //设置持续时间
        //播放以后停留在一个地方
        aa.setFillAfter(true);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_PARENT, 0.5f);
        sa.setFillAfter(true);
        RotateAnimation ra = new RotateAnimation(0, 360,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_PARENT, 0.5f);
        ra.setFillAfter(true);
        AnimationSet set = new AnimationSet(false);
        //添加三个动画没有先后顺序 目的是要同时播放动画
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);
        //设置持续时间
        set.setDuration(3000);
        set.setAnimationListener(new MyAnimationListener());
        rl_splash_root.startAnimation(set);
    }

    class MyAnimationListener implements AnimationListener {

        /**
         * 当动画开启播放的时候回调这个方法
         *
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * 当动画播放结束的时候 回调这个方法
         *
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
            Intent intent;
            if (isStartMain) {
                //如果进入过主界面，直接进入主界面
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                //如果没有进入过主界面，进入引导页面
                intent = new Intent(SplashActivity.this, GuideActivity.class);
            }
            startActivity(intent);
            //关闭SplashActivity界面
            finish();
            //Toast.makeText(SplashActivity.this, "动画播放完成了", Toast.LENGTH_SHORT).show();
        }

        /**
         * 当动画重复播放的时候 回调这个方法
         *
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
