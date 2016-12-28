package color.kidpaint.com.kidpaintcolor.adpter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.bean.Pencil;
import color.kidpaint.com.kidpaintcolor.event.OnClickItemDraw;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PencilViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @Bind(R.id.imgItem)
    ImageView imgItem;
    public OnClickItemDraw onClickItemDraw;
    private Pencil pencil;

    public PencilViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onClickItemDraw != null){
            onClickItemDraw.onclickItemDraw(pencil.color, true);
        }
    }

    public void setData(Pencil data){
        pencil = data;
        imgItem.setImageResource(data.drawable);
    }

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw){
        this.onClickItemDraw = onClickItemDraw;
    }

}
