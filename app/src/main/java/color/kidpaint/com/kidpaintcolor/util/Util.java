package color.kidpaint.com.kidpaintcolor.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import color.kidpaint.com.kidpaintcolor.R;

/**
 * Created by Tung Nguyen on 12/23/2016.
 */
public class Util {

    public static void playSound(Activity activity, int sound){
        MediaPlayer mp = MediaPlayer.create(activity, sound);
        mp.start();
    }

    public static MediaPlayer playMusic(Activity activity){
        MediaPlayer player = MediaPlayer.create(activity, R.raw.bgr_be_happy);
        AudioManager audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        player.setVolume(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        player.setLooping(true);
        return player;
    }


}
