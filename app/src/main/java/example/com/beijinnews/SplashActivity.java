package example.com.beijinnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

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
        set.setDuration(5000);
        set.setAnimationListener(new MyAnimationListener());
        rl_splash_root.startAnimation(set);
    }

    class MyAnimationListener implements AnimationListener {

        /**
         * 当动画开启播放的时候回调这个方法
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * 当动画播放结束的时候 回调这个方法
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {
            Toast.makeText(SplashActivity.this, "动画播放完成了", Toast.LENGTH_SHORT).show();
        }

        /**
         * 当动画重复播放的时候 回调这个方法
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
