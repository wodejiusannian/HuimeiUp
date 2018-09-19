package beijing.huimei.huimeiup.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.adapter.MainAdapter;

/**
 * Created by 小五 on 2018/8/29.
 */

public class AttributeDialog extends Dialog {


    public AttributeDialog(Context context) {
        super(context);
    }

    public AttributeDialog(Context context, int themeResId) {
        super(context, R.style.MySelfDialog);

    }

    private RecyclerView content;
    private MainAdapter adapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        content = (RecyclerView) this.findViewById(R.id.dialog_custom);
        Context context = getContext();
        if (mData == null)
            mData = new ArrayList<>();
        if (adapter == null)
            adapter = new MainAdapter(mData, context);
        content.setLayoutManager(new LinearLayoutManager(context));
        content.setAdapter(adapter);
        mData.add("主图");
        mData.add("尺码对照表");
        mData.add("详情图1");
        mData.add("详情图2");
        mData.add("详情图3");
        adapter.notifyDataSetChanged();
        adapter.setOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                String s = mData.get(tag);
                onItemListener.itemListener(tag, s);
                AttributeDialog.this.dismiss();
            }
        });

    }

    private OnItemListener onItemListener;

    public void setOnListener(OnItemListener onListener) {
        onItemListener = onListener;
    }

    public interface OnItemListener {
        void itemListener(int p, String str);
    }
}
