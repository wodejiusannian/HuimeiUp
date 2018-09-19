package beijing.huimei.huimeiup.utls;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {


    public static String getUserData(Context context) {
        SharedPreferences user = context.getSharedPreferences("user", 1);
        return user.getString("user_id", "");
    }

    public static void writeUserData(Context context, String user_id) {
        SharedPreferences user = context.getSharedPreferences("user", 1);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("user_id", user_id);
        edit.commit();
    }


    public static String getName(Context context) {
        SharedPreferences user = context.getSharedPreferences("user", 1);
        return user.getString("name", "");
    }

    public static void writeName(Context context, String user_id) {
        SharedPreferences user = context.getSharedPreferences("user", 1);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("name", user_id);
        edit.commit();
    }

    public static String getUploader(Context context) {
        SharedPreferences user = context.getSharedPreferences("user", 1);
        return user.getString("uploader", "");
    }

    public static void writeUploader(Context context, String user_id) {
        SharedPreferences user = context.getSharedPreferences("user", 1);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("uploader", user_id);
        edit.commit();
    }
}
