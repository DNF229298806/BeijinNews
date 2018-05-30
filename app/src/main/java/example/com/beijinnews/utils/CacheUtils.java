package example.com.beijinnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/5/29
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 * 缓存软件的一些参数和数据
 */
public class CacheUtils {

    public static final String BJ_CONFIG = "bj_config";

    /**
     * 得到缓存值
     * @param context   上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(BJ_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保存软件参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(BJ_CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
}
