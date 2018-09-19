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

public class CustomDialog extends Dialog {


    public CustomDialog(Context context) {
        super(context);
        if (mData == null)
            mData = new ArrayList<>();
        if (adapter == null)
            adapter = new MainAdapter(mData, context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, R.style.MySelfDialog);

    }

    private RecyclerView content;
    private MainAdapter adapter;
    private List<String> mData;

    private String[] array = {"货号（货号一定要选择,不清楚可以咨询", "出厂日期", "商品名称", "商品描述", "商户结算价格", "用户购买价格", "颜色","型号"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        content = (RecyclerView) this.findViewById(R.id.dialog_custom);
        Context context = getContext();
        content.setLayoutManager(new LinearLayoutManager(context));
        content.setAdapter(adapter);
      /*  mData.add("性别");
        mData.add("季节");
        mData.add("商品属性（例如上衣、下衣....）");
        mData.add("商品种类（例如T恤、西服....）");
        mData.add("版型（例如紧身、修身....）");
        mData.add("面料");
        mData.add("成分含量");
        mData.add("衣长");
        mData.add("型号（例如L,XL,XXL....）");
        mData.add("颜色");
        mData.add("库存");*/
        adapter.setOnItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                String s = mData.get(tag);
                onItemListener.itemListener(tag, s);
                CustomDialog.this.dismiss();
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

    public void setData(List<String> data) {
        if (data != null) {
            mData.clear();
            for (int i = 0; i < array.length; i++) {
                if (!data.contains(array[i])) {
                    mData.add(array[i]);
                }
            }
            if (adapter != null)
                adapter.notifyDataSetChanged();
            this.show();
        }
    }
}
