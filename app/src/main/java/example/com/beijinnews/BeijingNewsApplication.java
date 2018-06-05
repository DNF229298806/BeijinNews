package example.com.beijinnews;

import android.app.Application;

import org.xutils.x;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/6/4
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 * 代表整个软件
 */
public class BeijingNewsApplication extends Application {
    /**
     * 所有组件被创建之前执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
