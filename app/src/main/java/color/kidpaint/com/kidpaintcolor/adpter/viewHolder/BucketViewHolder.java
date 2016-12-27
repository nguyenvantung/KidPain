package color.kidpaint.com.kidpaintcolor.adpter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.bean.Bucket;
import color.kidpaint.com.kidpaintcolor.event.OnClickItemDraw;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class BucketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.imgItem)
    ImageView imgItem;
    public OnClickItemDraw onClickItemDraw;
    private Bucket bucket;

    public BucketViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onClickItemDraw.onclickItemDraw(bucket.color, true);
    }

    public void setData(Bucket data) {
        bucket = data;
        imgItem.setImageResource(data.drawable);
    }

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw) {
        this.onClickItemDraw = onClickItemDraw;
    }
}