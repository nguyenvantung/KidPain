package color.kidpaint.com.kidpaintcolor.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class ChoiseViewholder extends RecyclerView.ViewHolder {
    @Bind(R.id.imgItem)
    ImageView imageView;

    public ChoiseViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(int drawable) {
       imageView.setImageDrawable(imageView.getContext().getDrawable(drawable));
    }
}
