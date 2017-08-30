package base.lib.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * 王少岩 在 2017/3/10 创建了它
 */

public class PreferencesUtils {
    private static Context sContext;

    private PreferencesUtils() {
        throw new RuntimeException("PreferencesUtils cannot be initialized!");
    }

    public static void init(Context context){
        sContext = context;
    }

    public static void clear() {
        PreferenceManager.getDefaultSharedPreferences(sContext).edit().clear().apply();
    }

    public static void saveString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(sContext).edit().putString(key, value).apply();
    }

    public static void saveBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(sContext).edit().putBoolean(key, value).apply();
    }

    public static String getString(String key, String def) {
        return PreferenceManager.getDefaultSharedPreferences(sContext).getString(key, def);
    }

    public static boolean getBoolean(String key, boolean def) {
        return PreferenceManager.getDefaultSharedPreferences(sContext).getBoolean(key, def);
    }
}
