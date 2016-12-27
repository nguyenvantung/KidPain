package color.kidpaint.com.kidpaintcolor.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.adpter.viewHolder.BucketViewHolder;
import color.kidpaint.com.kidpaintcolor.bean.Bucket;
import color.kidpaint.com.kidpaintcolor.event.OnClickItemDraw;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class BucketAdapter extends RecyclerView.Adapter<BucketViewHolder>{

    public List<Bucket> bucketList = new ArrayList<>();

    public OnClickItemDraw onClickItemDraw;

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw){
        this.onClickItemDraw = onClickItemDraw;
    }

    public BucketAdapter(List<Bucket> bucketList){
        this.bucketList = bucketList;
    }

    @Override
    public BucketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BucketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BucketViewHolder holder, int position) {
        holder.setOnClickItemDraw(onClickItemDraw);
        holder.setData(bucketList.get(position));
    }

    @Override
    public int getItemCount() {
        return bucketList.size();
    }
}
