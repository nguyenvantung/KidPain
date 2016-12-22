package color.kidpaint.com.kidpaintcolor.fragment;

import android.view.View;

import butterknife.OnClick;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.util.FragmentUtil;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.homePlay)
    void gotoSelectItem(){
        FragmentUtil.pushFragment(getActivity(), ChoiseItemFragment.newInstance(), null);
    }
}
