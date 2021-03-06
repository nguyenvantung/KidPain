package color.kidpaint.com.kidpaintcolor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import color.kidpaint.com.kidpaintcolor.fragment.HomeFragment;
import color.kidpaint.com.kidpaintcolor.util.FragmentUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtil.replaceFragment(this, new HomeFragment(),null);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* if (Util.playMusic(this).isPlaying()){
            Util.playMusic(this).stop();
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
