package beijing.huimei.huimeiup.asyn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import beijing.huimei.huimeiup.utls.Util;

/**
 * Created by 小五 on 2018/8/23.
 */

public class FileUpzipAsync extends AsyncTask<String, Integer, List<String>> {

    private Context context;

    public FileUpzipAsync(Context context) {
        this.context = context;
    }

    private ProgressDialog show;

    private ReadFile readFile;

    public void setReadFileListener(ReadFile readFile) {
        this.readFile = readFile;
    }

    public interface ReadFile {

        void end();

    }

    /*
    * 开始加载
    * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        show = ProgressDialog.show(context, "进度", "解压中....", false);
    }

    /*
    * 加载进度
    * */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /*
    * 加载中
    * */
    @Override
    protected List<String> doInBackground(String... params) {
        try {
            Util.unzip(params[0], params[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 加载完成
    * */
    @Override
    protected void onPostExecute(List<String> beanShops) {
        super.onPostExecute(beanShops);
        if (readFile != null)
            readFile.end();
        if (show != null)
            show.dismiss();
    }
}
