package color.kidpaint.com.kidpaintcolor.adpter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.bean.Pencil;
import color.kidpaint.com.kidpaintcolor.event.OnClickItemBush;
import color.kidpaint.com.kidpaintcolor.util.AnimationBottom;
import color.kidpaint.com.kidpaintcolor.util.AnimationTop;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PencilViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.imgItem)
    ImageView imgItem;

    @Bind(R.id.layoutPencil)
    RelativeLayout layoutPencil;

    public OnClickItemBush clickItemBush;
    private Pencil pencil;

    public PencilViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (clickItemBush != null) {
            pencil.select = true;
            clickItemBush.selectItemBush(pencil);
        }
    }

    public void setAnimation(boolean select){
        if (select) {
            layoutPencil.animate().translationY((float) imgItem.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.item_select)).setDuration(300)
                    .setInterpolator(new BounceInterpolator()).setListener(new AnimationTop(layoutPencil, itemView.getContext())).start();

        }else {
            layoutPencil.animate().translationY((float) imgItem.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.item_default)).setDuration(300)
                    .setInterpolator(new BounceInterpolator()).setListener(new AnimationBottom(layoutPencil, itemView.getContext())).start();
        }
    }

    public void setData(Pencil data) {
        pencil = data;
        imgItem.setImageResource(data.drawable);

    }

    public void setOnClickItemDraw(OnClickItemBush onClickItemDraw) {
        this.clickItemBush = onClickItemDraw;
    }


}
