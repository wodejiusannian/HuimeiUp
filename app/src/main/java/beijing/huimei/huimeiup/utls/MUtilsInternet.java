package beijing.huimei.huimeiup.utls;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Map;

import beijing.huimei.huimeiup.bean.BeanMainPic;

/**
 * Created by 小五 on 2017/1/12.
 */
public class MUtilsInternet {

    private static MUtilsInternet instance;

    private Handler handler;


    private MUtilsInternet() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static MUtilsInternet getInstance() {
        if (instance == null) {
            synchronized (MUtilsInternet.class) {
                if (instance == null) {
                    instance = new MUtilsInternet();
                }
            }
        }
        return instance;
    }

    public void post(Context context, List<BeanMainPic> pics, final XCallBack callback) {
        final ProgressDialog dialog = ProgressDialog.show(context, "提示", "正在上传图片，请稍后....", true, false);

        RequestParams params = new RequestParams("http://192.168.1.110/admiral/common/upload/picUpload.htm?token=82D5FBD40259C743ADDEF14D0E22F347");


        if (pics != null && !pics.isEmpty()) {
            for (BeanMainPic bean : pics) {
                params.addBodyParameter("file", new File(bean.path));
            }
        }

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void post(Context context, String json, final XCallBack callback) {
        final ProgressDialog dialog = ProgressDialog.show(context, "提示", "正在上传数据，请稍后....", true, false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams("http://192.168.1.110/admiral/clothes/upload/addClothes.htm?token=82D5FBD40259C743ADDEF14D0E22F347");

        params.addBodyParameter("data", json);

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void post(Context context, Map<String, String> maps, final XCallBack callback) {
        final ProgressDialog dialog = ProgressDialog.show(context, "提示", "正在登录，请稍后....", true, true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams("http://192.168.1.110/admiral/seller/login/login.htm?token=82D5FBD40259C743ADDEF14D0E22F347");

        if (maps != null && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                onSuccessResponse(result, callback);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 异步get请求返回结果,json字符串
     *
     * @param result
     * @param callBack
     */
    private void onSuccessResponse(final String result, final XCallBack callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onResponse(result);
                }
            }
        });
    }

    public interface XCallBack {
        void onResponse(String result);
    }

}
