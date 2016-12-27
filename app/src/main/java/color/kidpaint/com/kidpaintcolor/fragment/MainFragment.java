package color.kidpaint.com.kidpaintcolor.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.adpter.BucketAdapter;
import color.kidpaint.com.kidpaintcolor.adpter.ColorDrawToolAdapter;
import color.kidpaint.com.kidpaintcolor.adpter.PencilAdapter;
import color.kidpaint.com.kidpaintcolor.bean.Bucket;
import color.kidpaint.com.kidpaintcolor.bean.Pencil;
import color.kidpaint.com.kidpaintcolor.event.OnClickItemDraw;
import color.kidpaint.com.kidpaintcolor.event.OnClickToolDraw;
import color.kidpaint.com.kidpaintcolor.util.ColoringUtility;

/**
 * Created by Tung Nguyen on 12/26/2016.
 */
public class MainFragment extends BaseFragment implements OnClickItemDraw, OnClickToolDraw{

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    private int[] listPencil = { R.drawable.pencil_aquamarine, R.drawable.pencil_beige, R.drawable.pencil_black, R.drawable.pencil_blue, R.drawable.pencil_bright_green,
            R.drawable.pencil_brown, R.drawable.pencil_copper, R.drawable.pencil_crimson, R.drawable.pencil_gold, R.drawable.pencil_green, R.drawable.pencil_grey,
            R.drawable.pencil_light_blue, R.drawable.pencil_magenta, R.drawable.pencil_orange, R.drawable.pencil_pink, R.drawable.pencil_purple, R.drawable.pencil_red,
            R.drawable.pencil_vailet, R.drawable.pencil_white, R.drawable.pencil_yellow};
    private int[] listBucket = {R.drawable.bucket_aquamarine, R.drawable.bucket_beige, R.drawable.bucket_black, R.drawable.bucket_blue, R.drawable.bucket_bright_green,
            R.drawable.bucket_brown, R.drawable.bucket_copper, R.drawable.bucket_crimson, R.drawable.bucket_gold, R.drawable.bucket_green, R.drawable.bucket_grey,
            R.drawable.bucket_light_blue, R.drawable.bucket_magenda, R.drawable.bucket_orange, R.drawable.bucket_pink, R.drawable.bucket_purple, R.drawable.bucket_red,
            R.drawable.bucket_violet, R.drawable.bucket_white, R.drawable.bucket_yellow};
    private int[] listTool = {R.drawable.item_bucket, R.drawable.item_tool_bush, R.drawable.item_eraser, R.drawable.tool_delete, R.drawable.tool_share, R.drawable.tool_done};

    private List<Pencil> listPencilData = new ArrayList<>();
    private List<Bucket> listBucketData = new ArrayList<>();

    @Bind(R.id.listBucket)
    RecyclerView recyclerViewBucket;

    @Bind(R.id.listPencil)
    RecyclerView recyclerViewPencil;

    @Bind(R.id.listHeaderTool)
    RecyclerView recyclerViewTool;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View root) {
        recyclerViewTool.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewBucket.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPencil.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    protected void initData() {
        initTool();
        PencilAdapter pencilAdapter = new PencilAdapter(listPencilData);
        //pencilAdapter.setOnClickItemDraw(this);
        recyclerViewPencil.setAdapter(pencilAdapter);
        //
        BucketAdapter bucketAdapter = new BucketAdapter(listBucketData);
        // bucketAdapter.setOnClickItemDraw(this);
        recyclerViewBucket.setAdapter(bucketAdapter);
        //
        ColorDrawToolAdapter adapter = new ColorDrawToolAdapter(listTool);
        //adapter.setOnClickToolDraw(this);
        recyclerViewTool.setAdapter(adapter);

    }

    public void initTool() {
        for (int i = 0; i < listPencil.length; i++) {
            Pencil pencil = new Pencil();
            pencil.drawable = listPencil[i];
            pencil.color = ColoringUtility.COLORS_MAPS.get(i);
            listPencilData.add(pencil);
        }

        for (int i = 0; i < listBucket.length; i++) {
            Bucket bucket = new Bucket();
            bucket.color = ColoringUtility.COLORS_MAPS.get(i);
            bucket.drawable = listPencil[i];
            listBucketData.add(bucket);

        }
    }

    @Override
    public void onclickItemDraw(int color, boolean pencil) {

    }

    @Override
    public void onClickTool(int posion) {

    }
}
