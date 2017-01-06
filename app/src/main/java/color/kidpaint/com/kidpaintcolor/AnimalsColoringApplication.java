package color.kidpaint.com.kidpaintcolor;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import color.kidpaint.com.kidpaintcolor.bean.Perspective;
import color.kidpaint.com.kidpaintcolor.command.CommandManager;
import color.kidpaint.com.kidpaintcolor.tools.Tool;
import color.kidpaint.com.kidpaintcolor.widget.DrawingSurface;

/**
 * Created by Tung Nguyen on 12/28/2016.
 */
public class AnimalsColoringApplication extends Application {
    private static final String PROPERTY_ID = "UA-52348719-11";
    private static AnimalsColoringApplication mInstance;

    private static AnimalsColoringApplication instance;
    public static Context applicationContext;
    public static DrawingSurface drawingSurface;
    public static Perspective perspective;
    public static boolean isSaved = true;
    public static Uri savedPictureUri = null;
    public static boolean saveCopy = false;
    public static boolean scaleImage = true;
    public static boolean openedFromCatroid = false;
    public static CommandManager commandManager;
    public static Tool currentTool;
    public static String catroidPicturePath;
    public static boolean isPlainImage = true;

    public AnimalsColoringApplication() {
        instance = this;
    }

    public static AnimalsColoringApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return mInstance;
    }


}

