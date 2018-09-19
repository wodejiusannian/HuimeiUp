package beijing.huimei.huimeiup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import beijing.huimei.huimeiup.R;
import beijing.huimei.huimeiup.bean.BeanFile;

/**
 * Created by 小五 on 2018/8/21.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.MyViewHolder> {

    private List<BeanFile> mData;

    private Context context;

    private View.OnClickListener onClickListener;

    public void setOnItemListener(View.OnClickListener onItemListener) {
        onClickListener = onItemListener;
    }

    ;

    public FileAdapter(List<BeanFile> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BeanFile beanFile = mData.get(position);
        holder.filePath.setText((Html.fromHtml("点击自动解压<font color='#FF0000'>" + beanFile.filePath + "</font>")));
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
