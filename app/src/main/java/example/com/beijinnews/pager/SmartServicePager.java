package example.com.beijinnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import example.com.beijinnews.base.BasePager;
import example.com.beijinnews.utils.LogUtil;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/6/4
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 * 智慧服务
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("智慧服务被初始化了...");
        //1.设置标题
        tv_title.setText("智慧服务");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("我是智慧服务内容");
    }
}
