package beijing.huimei.huimeiup.asyn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import beijing.huimei.huimeiup.utls.HttpCallBack;
import beijing.huimei.huimeiup.utls.HttpUtils;


// ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
// ┃　　　┃ 神兽保佑　　　　　　　　
// ┃　　　┃ 代码无BUG！
// ┃　　　┗━━━┓
// ┃　　　　　　　┣┓
// ┃　　　　　　　┏┛
// ┗┓┓┏━┳┓┏┛
// ┃┫┫　┃┫┫
// ┗┻┛　┗┻┛
public class AsyncHttpUtils extends AsyncTask<String, Void, String> {

    private ProgressDialog dialog;

    private HttpCallBack callBack;

    private Activity mDialogActivity;

    public AsyncHttpUtils(HttpCallBack callBack, Activity activity) {
        this.callBack = callBack;
        this.mDialogActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mDialogActivity != null) {
            dialog = ProgressDialog.show(mDialogActivity, "提示", "正在上传数据，请稍后....", true, false);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (params == null || params.length == 0)
            return null;
        return HttpUtils.sendPostConnection(params[0], params[1]);
    }

    @Override
    protected void onPostExecute(String s) {
        if (dialog != null && mDialogActivity != null) {
            dialog.dismiss();
        }

        callBack.onResponse(s);
    }
}
