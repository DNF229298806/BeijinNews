package example.com.beijinnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import example.com.beijinnews.R;
import example.com.beijinnews.SplashActivity;
import example.com.beijinnews.utils.CacheUtils;
import example.com.beijinnews.utils.DensityUtil;

public class GuideActivity extends AppCompatActivity {

    private static final String TAG = GuideActivity.class.getSimpleName();
    private ViewPager vp_viewpager;
    private Button bt_start_main;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;
    private ImageView iv_red_point;
    /**
     * 两点的间距
     */
    private int leftmax;
    private int widthdpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp_viewpager = findViewById(R.id.vp_viewpager);
        bt_start_main = findViewById(R.id.bt_start_main);
        ll_point_group = findViewById(R.id.ll_point_group);
        iv_red_point = findViewById(R.id.iv_red_point);
        //准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        //进行px和dp的转化
        widthdpi = DensityUtil.dip2px(this, 10);
        Log.d(TAG, "widthdpi="+widthdpi+"--------------------------");
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            //添加到集合中
            imageViews.add(imageView);
            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 单位是像素
             * 把单位当成dp转成对应的像素
             */
            LayoutParams params = new LayoutParams(widthdpi, widthdpi);
            if (i != 0) {
                //不包括第0个，所有的点距离左边有30个像素
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            // 添加到线性布局里面
            ll_point_group.addView(point);

        }

        //设置viewPager的适配器
        vp_viewpager.setAdapter(new MyPagerAdapter());

        //根据View的生命周期，当视图执行到onLayout或者onDraw的时候，视图的高和宽，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //得到屏幕滑动百分比
        vp_viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置按钮的点击事件
        bt_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.保存曾经进入过主界面
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);
                //2.跳转到主页面
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                //3.关闭当前界面
                finish();
            }
        });
    }

    class MyOnPageChangeListener implements OnPageChangeListener {

        /**
         * 当页面滚动了 会回调这个方法
         *
         * @param position             当前滑动页面的位置
         * @param positionOffset       页面滑动的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点间移动的距离 = 屏幕滑动百分比*间距
            //两点间滑动距离对应的坐标 = 原来的起始位置+两点间移动的距离
            int leftmargin = (int) (position * leftmax + (positionOffset * leftmax));
            Log.e(TAG, "position==" + position + "\n" + "leftmax==" + leftmax + "\n" + "leftmargin==" + leftmargin);
            //params.leftMargin = 两点间滑动距离对应的坐标
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当页面被选中的时候 回调这个方法
         *
         * @param position 被选中页面对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                //最后一个页面
                bt_start_main.setVisibility(View.VISIBLE);
            } else {
                //其他页面
                bt_start_main.setVisibility(View.GONE);
            }
        }

        /**
         * 当页面滑动状态发生变化的时候 比如滑到一半不划了 回弹回去
         * 三个状态 静止 正在拖 放开惯性回滚
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnGlobalLayoutListener implements OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            //执行不止一次
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            // 间距 = 第一个点距离左边的距离 - 第0个点距离左边的距离
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }
    }


    class MyPagerAdapter extends PagerAdapter {

        /**
         * 作用 相当于 getView
         *
         * @param container ViewPager
         * @param position  要创建页面的位置
         * @return 返回和创建当前页面有关系的值
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
            return imageView;
        }

        /***
         * 销毁页面
         * @param container ViewPager
         * @param position  要销毁页面的位置
         * @param object    要销毁的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            /*不去掉必蹦 super*/
            /*super.destroyItem(container, position, object);*/
            container.removeView((View) object);
        }

        /**
         * 返回数据的总个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 判断
         *
         * @param view   当前创建的视图
         * @param object 上面instantiateItem返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
