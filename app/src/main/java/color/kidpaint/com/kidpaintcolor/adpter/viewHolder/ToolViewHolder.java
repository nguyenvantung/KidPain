package color.kidpaint.com.kidpaintcolor.adpter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.event.OnClickToolDraw;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class ToolViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @Bind(R.id.imgItem)
    ImageView imageView;

    public OnClickToolDraw onClickToolDraw;

    public void setOnClickToolDraw(OnClickToolDraw onClickToolDraw){
        this.onClickToolDraw = onClickToolDraw;
    }

    public ToolViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setData(int drawable) {
        imageView.setImageDrawable(imageView.getContext().getDrawable(drawable));
    }

    @Override
    public void onClick(View view) {
        if(onClickToolDraw != null){
            onClickToolDraw.onClickTool(getAdapterPosition());
        }
    }
}
