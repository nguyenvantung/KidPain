package color.kidpaint.com.kidpaintcolor.adpter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.widget.ColorPicketDialog;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PickerViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Bind(R.id.imgItem)
    ImageView imageView;

    public PickerViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setData(int drawable) {
        //imageView.setImageDrawable(imageView.getContext().getDrawable(drawable));
    }

    @Override
    public void onClick(View view) {
      openDialog(false);
    }

    void openDialog(boolean supportsAlpha) {
        int color = 0xffffff00;
        ColorPicketDialog dialog = new ColorPicketDialog(itemView.getContext(), color, supportsAlpha, new ColorPicketDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(ColorPicketDialog dialog, int color) {
              /*  Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                AmbilWarnaDemoActivity.this.color = color;
                displayColor();*/
            }

            @Override
            public void onCancel(ColorPicketDialog dialog) {
               // Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
