package beijing.huimei.huimeiup.utls;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import beijing.huimei.huimeiup.bean.BeanSelect;

/**
 * Created by 小五 on 2018/8/27.
 */

public class Util {
    /**
     * dp转换成px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /*
     * 这个是解压ZIP格式文件的方法
	 *
	 * @zipFileName：是传进来你要解压的文件路径，包括文件的名字；
	 *
	 * @outputDirectory:选择你要保存的路劲；
	 *
	 */
    public static void unzip(String zipFileName, String outputDirectory) throws Exception {

        ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));

        ZipEntry z;

        String name = "";

        String extractedFile = "";

        int counter = 0;

        while ((z = in.getNextEntry()) != null) {
            name = z.getName();
            if (z.isDirectory()) {
                // get the folder name of the widget
                name = name.substring(0, name.length() - 1);
                File folder = new File(outputDirectory + File.separator + name);
                folder.mkdirs();
                if (counter == 0) {
                    extractedFile = folder.toString();
                }
                counter++;
            } else {
                File file = new File(outputDirectory + File.separator + name);
                file.createNewFile();
                // get the output stream of the file
                FileOutputStream out = new FileOutputStream(file);
                int ch;
                byte[] buffer = new byte[1024];
                // read (ch) bytes into buffer
                while ((ch = in.read(buffer)) != -1) {
                    // write (ch) byte from buffer at the position 0
                    out.write(buffer, 0, ch);
                    out.flush();
                }
                out.close();
            }
        }

        in.close();

    }


    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it  Or Log it.
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static void swap(List<?> list, int i, int j) {
        final List l = list;
        l.set(i, l.set(j, l.get(i)));
    }


    public static List<String> strToArray(String str) {
        List<String> arr = new ArrayList<>();
        if (str.contains("，") || str.contains("-") || str.contains("、")) {
            if (str.contains("，")) {
                String[] split = str.split("，");
                for (String s : split) {
                    arr.add(s);
                }
                return arr;
            } else if (str.contains("-")) {
                String[] split = str.split("-");
                for (String s : split) {
                    arr.add(s);
                }
                return arr;
            } else if (str.contains("、")) {
                String[] split = str.split("、");
                for (String s : split) {
                    arr.add(s);
                }
            }
        } else {
            arr.add(str);
        }
        return arr;

    }

    // 删除ArrayList中重复元素，保持顺序
    //set集合去重，不改变原有的顺序
    public static List<BeanSelect> pastLeep(List<BeanSelect> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).id.equals(list.get(i).id)) {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}
