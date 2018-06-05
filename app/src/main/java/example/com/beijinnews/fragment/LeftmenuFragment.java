package example.com.beijinnews.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import example.com.beijinnews.base.BaseFragment;
import example.com.beijinnews.utils.LogUtil;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/5/30
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 * 左侧菜单的Fragment
 */
public class LeftmenuFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {
        LogUtil.d("左侧菜单视图被初始化了");
        textView = new TextView(activity);
        textView.setTextSize(23);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.d("左侧菜单数据被初始化了");
        textView.setText("左侧菜单菜单菜单界面");
    }
}
