package color.kidpaint.com.kidpaintcolor.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.adpter.viewHolder.ToolViewHolder;
import color.kidpaint.com.kidpaintcolor.event.OnClickToolDraw;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class ColorDrawToolAdapter extends RecyclerView.Adapter<ToolViewHolder>{
    private int[] listData ;

    private OnClickToolDraw onClickToolDraw;

    public void setOnClickToolDraw(OnClickToolDraw onClickToolDraw){
        this.onClickToolDraw = onClickToolDraw;
    }

    public ColorDrawToolAdapter(int[] listData){
        this.listData = listData;
    }

    @Override
    public ToolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ToolViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ToolViewHolder holder, int position) {
        holder.setOnClickToolDraw(onClickToolDraw);
        holder.setData(listData[position]);
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }
}
