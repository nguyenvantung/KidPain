package color.kidpaint.com.kidpaintcolor.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;
import color.kidpaint.com.kidpaintcolor.R;
import color.kidpaint.com.kidpaintcolor.constan.AppConstance;
import color.kidpaint.com.kidpaintcolor.util.FragmentUtil;
import color.kidpaint.com.kidpaintcolor.util.Util;

/**
 * Created by Tung Nguyen on 12/22/2016.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.homePlay)
    ImageView imgPlay;

    @Bind(R.id.homeLayoutSound)
    RelativeLayout layoutSound;

    @Bind(R.id.homeSetting)
    ImageView imgSetting;

    @Bind(R.id.homeMusic)
    ImageView imgMusic;

    @Bind(R.id.homeSound)
    ImageView imgSound;

    private boolean isOpenSetting;


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
    public void gotoSelectItem() {
        FragmentUtil.pushFragment(getActivity(), ChoiseItemFragment.newInstance(), null);
    }

    @OnClick(R.id.homeMusic)
    public void handleMusic() {
        if (!AppConstance.isPlaymusic) {
            imgMusic.setSelected(true);
            AppConstance.isPlaymusic = true;
            Util.playMusic(getActivity()).start();
        } else {
            AppConstance.isPlaymusic = false;
            imgMusic.setSelected(false);
            Util.playMusic(getActivity()).stop();
        }
    }

    @OnClick(R.id.homeSound)
    public void handleSound() {
        if (!AppConstance.isSound) {
            imgSound.setSelected(true);
            AppConstance.isSound = true;
        } else {
            imgSound.setSelected(false);
            AppConstance.isSound = false;
        }
    }

    @OnClick(R.id.homeSetting)
    public void handleSlideSetting() {
      /*  TranslateAnimation animate;
        if (isOpenSetting) {
            animate = new TranslateAnimation(0, -0, 0, 0);
            isOpenSetting = false;
        } else {
            animate = new TranslateAnimation(0, (layoutSound.getWidth() * 2) / 3 - 10, 0, 0);
            isOpenSetting = true;
        }
        animate.setDuration(300);
        animate.setFillAfter(true);
        layoutSound.startAnimation(animate);*/

    }
}
