package color.kidpaint.com.kidpaintcolor.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.adpter.viewHolder.ChoiseViewholder;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class ChoiseFragmentAdapter extends RecyclerView.Adapter<ChoiseViewholder>{
    private int[] listData ;

    public ChoiseFragmentAdapter(int[] listData){
        this.listData = listData;
    }

    @Override
    public ChoiseViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChoiseViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choiseview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ChoiseViewholder holder, int position) {
        holder.setData(listData[position]);
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }
}
