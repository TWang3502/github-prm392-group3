package thanggun99.ailatrieuphu;

import android.app.Application;
import android.content.Context;

import thanggun99.ailatrieuphu.manager.MusicManager;

/**
 * Created by Mai Bui on 18/10/2023
 */

public class App extends Application {
    private static Context context;
    private static MusicManager musicPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        musicPlayer = new MusicManager(this);
    }

    public static MusicManager getMusicPlayer(){
        return musicPlayer;
    }

    public static Context getContext() {
        return context;
    }
}
