package lee.vioson.vrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lee.vioson.vrecyclerviewlibrary.VRecyclerViewAdapter;
import lee.vioson.vrecyclerviewlibrary.VRecyclerViewFooter;

/**
 * Author:李烽
 * Date:2016-12-08
 * FIXME
 * Todo
 */

public class ListAdapter extends VRecyclerViewAdapter {
    private List<String> mDatas;
    private Context mContext;

    public ListAdapter(VRecyclerViewFooter vRecyclerViewFooter, List<String> data) {
        super(vRecyclerViewFooter);
        this.mDatas = data;
        this.mContext = vRecyclerViewFooter.getContext();
    }

    @Override
    protected int getVItemViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onVCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item, null));
        return holder;
    }

    @Override
    public void onVBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.name.setText(mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
