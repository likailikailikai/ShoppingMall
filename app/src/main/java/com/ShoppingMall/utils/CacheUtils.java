package com.ShoppingMall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 情v枫 on 2017/2/27.
 */

public class CacheUtils {

    private static SharedPreferences sp;

    /**
     * 获取软件保存的boolean类型参数
     * @param context
     * @param key
     * @return 没有找到,返回false
     * @author Wangnan
     */
    public static boolean getBoolean(Context context, String key){
        if(sp==null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, false);
    }

    /**
     * 设置boolean类型的软件参数
     * @param context
     * @param key
     * @param value
     * @author Wangnan
     */
    public static void setBoolean(Context context, String key, boolean value){
        if(sp==null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }


    /**
     * 设置String类型的软件参数
     * @param context
     * @param key
     * @param value
     * @author Wangnan
     */
    public static void setString(Context context, String key, String value){
        if(sp==null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取软件保存的String类型参数
     * @param context
     * @param key
     * @return 没有不到,返回""
     * @author Wangnan
     */
    public static String getString(Context context, String key) {
        if (sp == null) {
            if (context != null) {
                sp = context.getSharedPreferences("config",
                        Context.MODE_PRIVATE);
            }

        }
        return sp.getString(key, "");
    }

    /**
     * 设置int类型的软件参数
     * @param context
     * @param key
     * @param value
     * @author Wangnan
     */
    public static void setInt(Context context, String key, int value) {
        if (sp == null) {
            if (context != null) {
                sp = context.getSharedPreferences("config",
                        Context.MODE_PRIVATE);
            }

        }
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 获取软件保存的int类型参数
     * @param context
     * @param key
     * @return 没有不到,返回""
     * @author Wangnan
     */
    public static int getInt(Context context, String key){
        if(sp==null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, 0);
    }


}
