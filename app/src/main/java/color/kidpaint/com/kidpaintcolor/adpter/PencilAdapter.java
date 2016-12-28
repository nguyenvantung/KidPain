package color.kidpaint.com.kidpaintcolor.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.adpter.viewHolder.PencilViewholder;
import color.kidpaint.com.kidpaintcolor.adpter.viewHolder.PickerViewholder;
import color.kidpaint.com.kidpaintcolor.bean.Pencil;
import color.kidpaint.com.kidpaintcolor.event.OnClickItemDraw;

/**
 * Created by Tung Nguyen on 12/27/2016.
 */
public class PencilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int HEADER = 0;
    private static final int BODY = 1;

    private List<Pencil> pencilList = new ArrayList<>();

    private OnClickItemDraw onClickItemDraw;

    public void setOnClickItemDraw(OnClickItemDraw onClickItemDraw){
        this.onClickItemDraw = onClickItemDraw;
    }

    public PencilAdapter(List<Pencil> pencilList){
        this.pencilList = pencilList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER){
            return new PickerViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
        }else {
            return new PencilViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pencil, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > 0){
            PencilViewholder viewholder = (PencilViewholder) holder;
            viewholder.setOnClickItemDraw(onClickItemDraw);
            viewholder.setData(pencilList.get(position - 1));
        }else {
            PickerViewholder pickerViewholder = (PickerViewholder) holder;
            pickerViewholder.setData(R.drawable.color_picker);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return HEADER;
        }else {
            return BODY;
        }
    }

    @Override
    public int getItemCount() {
        return pencilList.size() + 1;
    }
}
