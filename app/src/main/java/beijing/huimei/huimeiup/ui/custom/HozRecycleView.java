package beijing.huimei.huimeiup.ui.custom;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.bean.BeanSelect;
import beijing.huimei.huimeiup.utls.RvDecorationUtils;


/**
 * Created by 小五 on 2018/8/30.
 */

public class HozRecycleView extends RecyclerView {

    private List<BeanSelect> mData;


    private HozAdapter adapter;

    public HozRecycleView(Context context) {
        super(context);
    }


    public HozRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (mData == null)
            mData = new ArrayList<>();
        if (adapter == null)
            adapter = new HozAdapter();
        this.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL, false));
        this.setAdapter(adapter);
        this.addItemDecoration(new RvDecorationUtils(10));
    }

    public class HozAdapter extends RecyclerView.Adapter<HozAdapter.MyViewHolder> {


        @Override
        public HozAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.select_item, null);
            HozAdapter.MyViewHolder holder = new HozAdapter.MyViewHolder(inflate);
            return holder;
        }

        @Override
        public void onBindViewHolder(HozAdapter.MyViewHolder holder, final int position) {
            BeanSelect beanSelect = mData.get(position);
            if (beanSelect.isSelect)
                holder.test.setBackgroundColor(Color.RED);
            else
                holder.test.setBackgroundColor(Color.WHITE);
            holder.test.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mData.size(); i++) {
                        mData.get(i).isSelect = false;
                    }
                    BeanSelect bb = mData.get(position);
                    bb.isSelect = true;
                    if (mOnSelect != null)
                        mOnSelect.onSelectListener(bb);
                    notifyDataSetChanged();
                }
            });
            holder.test.setText(beanSelect.tip);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView test;

            public MyViewHolder(View itemView) {
                super(itemView);
                test = (TextView) itemView.findViewById(R.id.tv_test2);
            }
        }

    }

    private OnSelect mOnSelect;

    public void setOnListener(OnSelect onSelect) {
        mOnSelect = onSelect;
    }

    public interface OnSelect {
        void onSelectListener(BeanSelect beanSelect);
    }

    public void setData(List<BeanSelect> data) {
        mData.clear();
        mData.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public void setData(List<BeanSelect> data, String str) {
        mData.clear();
        boolean flag = true;
        for (BeanSelect beanSelect : data) {
            if (beanSelect.tip.contains(str)) {
                beanSelect.isSelect = true;
                flag = false;
            }
            mData.add(beanSelect);
        }
        if (flag)
            mData.add(new BeanSelect(true, str, str));
        adapter.notifyDataSetChanged();
    }


    public String getName() {
        for (BeanSelect select : mData) {
            if (select.isSelect) {
                return select.tip;
            }
        }
        return null;
    }

    public String getIdd() {
        for (BeanSelect select : mData) {
            if (select.isSelect) {
                return select.id;
            }
        }
        return null;
    }
}
