package beijing.huimei.huimeiup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.bean.BeanExcel;

/**
 * Created by Administrator on 2018/8/25.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {


    private List<BeanExcel> mData;

    private Context context;

    public ContentAdapter(List<BeanExcel> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.text.setText(mData.get(position).content);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
