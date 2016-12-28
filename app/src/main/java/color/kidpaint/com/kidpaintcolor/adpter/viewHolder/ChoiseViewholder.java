package color.kidpaint.com.kidpaintcolor.adpter.viewHolder;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.fragment.MainFragment;
import color.kidpaint.com.kidpaintcolor.util.FragmentUtil;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class ChoiseViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @Bind(R.id.imgItem)
    ImageView imageView;
    private int drawableData;

    public ChoiseViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setData(int drawable) {
        drawableData = drawable;
        imageView.setImageDrawable(imageView.getContext().getDrawable(drawable));
    }

    @Override
    public void onClick(View view) {
        FragmentUtil.pushFragment((FragmentActivity)itemView.getContext(), MainFragment.newInstance(drawableData), null);
    }
}
