package example.com.beijinnews.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.beijinnews.base.BasePager;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/6/5
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ContentFragmentPagerAdapter extends PagerAdapter {

    private ArrayList<BasePager> basePagers;

    public ContentFragmentPagerAdapter(ArrayList<BasePager> basePagers) {
        this.basePagers = basePagers;
    }
    @NonNull
    @Override

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BasePager basePager = basePagers.get(position);     //各个页面的实例
        View rootView = basePager.rootView;     //各个子页面
        //调用各个页面的initData()
        //basePager.initData();   //初始化数据
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return basePagers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
