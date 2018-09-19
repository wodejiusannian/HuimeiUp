package beijing.huimei.huimeiup;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 小五 on 2018/8/31.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
