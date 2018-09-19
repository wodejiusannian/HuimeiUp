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
import beijing.huimei.huimeiup.utls.Util;


/**
 * Created by 小五 on 2018/8/30.
 */

public class HozManyRecycleView extends RecyclerView {

    private List<BeanSelect> mData;


    private HozAdapter adapter;

    public HozManyRecycleView(Context context) {
        super(context);
    }

    public void setData(List<BeanSelect> data, List<String> str) {
        mData.clear();
        if (str == null) {
            mData.addAll(data);
        } else {
            for (BeanSelect b : data) {
                for (int i = 0; i < str.size(); i++) {
                    String s = str.get(i).toLowerCase();
                    if (s.equals(b.tip)) {
                        b.isSelect = true;
                    } else {
                        mData.add(new BeanSelect(true, s, s));
                    }
                }
                mData.add(b);
            }
        }
        mData = Util.pastLeep(mData);
        adapter.notifyDataSetChanged();
    }

    public HozManyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (mData == null)
            mData = new ArrayList<>();
        if (adapter == null)
            adapter = new HozAdapter();
        this.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL, false));
        this.setAdapter(adapter);
        this.addItemDecoration(new RvDecorationUtils(10));
    }

    public class HozAdapter extends Adapter<HozAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.select_item, null);
            MyViewHolder holder = new MyViewHolder(inflate);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            BeanSelect beanSelect = mData.get(position);
            if (beanSelect.isSelect)
                holder.test.setBackgroundColor(Color.RED);
            else
                holder.test.setBackgroundColor(Color.WHITE);
            holder.test.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> strings = new ArrayList<String>();
                    BeanSelect bb = mData.get(position);
                    bb.isSelect = !bb.isSelect;
                    notifyDataSetChanged();
                    for (BeanSelect b : mData) {
                        if (b.isSelect) {
                            strings.add(b.id);
                        }
                    }
                    mOnSelect.onSelectListener(strings);
                }
            });
            holder.test.setText(beanSelect.tip);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        public class MyViewHolder extends ViewHolder {
            private TextView test;

            public MyViewHolder(View itemView) {
                super(itemView);
                test = (TextView) itemView.findViewById(R.id.tv_test2);
            }
        }

    }

    public List<String> getName() {
        List<String> strings = new ArrayList<String>();
        for (BeanSelect b : mData) {
            if (b.isSelect) {
                strings.add(b.id);
            }
        }
        return strings;
    }

    private OnSelect mOnSelect;

    public void setOnListener(OnSelect onSelect) {
        mOnSelect = onSelect;
    }

    public interface OnSelect {
        void onSelectListener(List<String> beanSelect);
    }

    public void setData(List<BeanSelect> data) {
        mData.clear();
        mData.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public void clean() {
        mData.clear();
        adapter.notifyDataSetChanged();
    }
}
