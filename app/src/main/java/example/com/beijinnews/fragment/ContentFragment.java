package example.com.beijinnews.fragment;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import example.com.beijinnews.R;
import example.com.beijinnews.activity.MainActivity;
import example.com.beijinnews.adapter.ContentFragmentPagerAdapter;
import example.com.beijinnews.base.BaseFragment;
import example.com.beijinnews.base.BasePager;
import example.com.beijinnews.pager.GovaffairPager;
import example.com.beijinnews.pager.HomePager;
import example.com.beijinnews.pager.NewsCenterPager;
import example.com.beijinnews.pager.SettingPager;
import example.com.beijinnews.pager.SmartServicePager;
import example.com.beijinnews.utils.LogUtil;
import example.com.beijinnews.view.NoScrollViewPager;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/5/30
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 * 正文Fragment
 */
public class ContentFragment extends BaseFragment {

    //2.初始化控件
    @ViewInject(R.id.vp_viewpager)
    private NoScrollViewPager vp_viewPager;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    /**
     * 装五个页面的集合
     */
    private ArrayList<BasePager> basePagers;

    @Override

    public View initView() {
        LogUtil.d("正文Fragment视图被初始化了");
        View view = View.inflate(activity, R.layout.content_fragment, null);
        //        vp_viewPager = view.findViewById(R.id.vp_viewpager);
        //        rg_main = view.findViewById(R.id.rg_main);
        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.d("正文Fragment数据被初始化了");
        //初始化5个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(activity));    //主页面
        basePagers.add(new NewsCenterPager(activity));    //新闻中心页面
        basePagers.add(new SmartServicePager(activity));    //智慧服务页面
        basePagers.add(new GovaffairPager(activity));    //政要指南页面
        basePagers.add(new SettingPager(activity));    //设置中心页面
        //设置ViewPager的适配器
        vp_viewPager.setAdapter(new ContentFragmentPagerAdapter(basePagers));
        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //监听某个页面被选中，初始化对应的页面的数据
        vp_viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置默认选中首页
        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();
        //默认设置slidingMenu不可以滑动
        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
    }

    class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中的时候回调这个方法
         *
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            //调用被选中的页面的initData方法
            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnCheckedChangeListener implements OnCheckedChangeListener {

        /**
         * @param group     RadioGroup
         * @param checkedId 被选中的RadioButton
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home://主页面
                    vp_viewPager.setCurrentItem(0, false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter://新闻中心
                    vp_viewPager.setCurrentItem(1, false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice://智慧服务
                    vp_viewPager.setCurrentItem(2, false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair://政要指南
                    vp_viewPager.setCurrentItem(3, false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting://设置中心
                    vp_viewPager.setCurrentItem(4, false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }
    }

    /**
     * 根据传入的参数设置是否让SlidingMenu可以滑动
     * @param touchmodeFullscreen
     */
    private void isEnableSlidingMenu(int touchmodeFullscreen) {
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);
    }
}
