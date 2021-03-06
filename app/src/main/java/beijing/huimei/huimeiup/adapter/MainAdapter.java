package beijing.huimei.huimeiup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import beijing.huimei.huimeiup.R;

/**
 * Created by 小五 on 2018/8/28.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private List<String> mData;

    private Context context;

    private View.OnClickListener onClickListener;

    public void setOnItemListener(View.OnClickListener onItemListener) {
        onClickListener = onItemListener;
    }

    ;

    public MainAdapter(List<String> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new MainAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MyViewHolder holder, int position) {
        holder.filePath.setText(mData.get(position));
        holder.filePath.setTag(position);
        holder.filePath.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView filePath;

        public MyViewHolder(View itemView) {
            super(itemView);
            filePath = (TextView) itemView.findViewById(R.id.tv_itemfile);
        }
    }
}
