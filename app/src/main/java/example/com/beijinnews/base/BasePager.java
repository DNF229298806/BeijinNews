package example.com.beijinnews.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import example.com.beijinnews.R;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/6/4
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 * 基类或者说公共类
 */
public class BasePager {
    /**
     * 上下文
     */
    public final Context context;   //MainActivity

    /**
     * 视图，代表各个不同的页面
     */
    public View rootView;
    /**
     * 显示标题
     */
    @ViewInject(R.id.tv_title)
    public TextView tv_title;
    /**
     * 点击侧滑的
     */
    @ViewInject(R.id.ib_menu)
    public ImageButton ib_menu;
    /**
     * 加载各个子页面
     */
    @ViewInject(R.id.fl_content)
    public FrameLayout fl_content;

    public BasePager(Context context) {
        this.context = context;
        //构造方法一执行，视图就被初始化了
        rootView = initView();
    }

    /**
     * 用于初始化化公共部分的视图，并且初始化加载子视图的FrameLayout
     *
     * @return
     */
    private View initView() {
        View view = View.inflate(context, R.layout.base_pager, null);
        /*tv_title = view.findViewById(R.id.tv_title);
        ib_menu = view.findViewById(R.id.ib_menu);
        fl_content = view.findViewById(R.id.fl_content);*/
        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(BasePager.this, view);
        return view;
    }

    /**
     * 初始化数据，当孩子需要初始化数据，或者绑定数据，联网请求数据并绑定的时候，重写该方法
     */
    public void initData() {

    }
}
