package beijing.huimei.huimeiup.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2018/8/25.
 */

public class SyncHorizontalScrollView extends HorizontalScrollView {
    private View mView;

    public SyncHorizontalScrollView(Context context) {
        super(context);
    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("TAG", "onScrollChanged: ------" + l + "-------" + t);
        if (mView != null) {
            mView.scrollTo(l, t);
        }
    }

    public void setScrollView(View view) {
        mView = view;
    }
}
