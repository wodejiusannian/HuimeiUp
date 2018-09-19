package beijing.huimei.huimeiup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.bean.BeanMainPic;

/**
 * Created by 小五 on 2018/8/31.
 */

public class MainPicAdapter extends RecyclerView.Adapter<MainPicAdapter.MyViewHolder> {
    private Context context;
    private List<BeanMainPic> mData;

    public MainPicAdapter(Context context, List<BeanMainPic> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public MainPicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mainpic, null);
        return new MainPicAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainPicAdapter.MyViewHolder holder, int position) {
        BeanMainPic beanMainPic = mData.get(position);
        Glide.with(context).load("file://" + beanMainPic.path).into(holder.pic);
        if (position == 0)
            holder.name.setText("主图");
        else if (position == 1)
            holder.name.setText("尺码对照表");
        else if (position == 2)
            holder.name.setText("详情图1");
        else if (position == 3)
            holder.name.setText("详情图2");
        else if (position == 4)
            holder.name.setText("详情图3");
        else
            holder.name.setText("");
        holder.ll.setTag(position);
        holder.ll.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic;
        private TextView name;
        private LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.iv_picmain);
            name = (TextView) itemView.findViewById(R.id.tv_namemain);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }

    private View.OnClickListener onClickListener;

    public void setOnItemLisener(View.OnClickListener onItemLisener) {
        onClickListener = onItemLisener;
    }
}
