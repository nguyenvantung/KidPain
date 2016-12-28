package color.kidpaint.com.kidpaintcolor;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tung Nguyen on 12/28/2016.
 */
public class AnimalsColoringApplication extends Application {
    private static final String PROPERTY_ID = "UA-52348719-11";
    private static AnimalsColoringApplication mInstance;

    public AnimalsColoringApplication() {
        mInstance = this;
    }

    public void onCreate() {
        super.onCreate();
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

