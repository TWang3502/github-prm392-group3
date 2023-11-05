package thanggun99.ailatrieuphu.activity;

import android.app.ListActivity;
import android.os.Bundle;

import thanggun99.ailatrieuphu.App;
import thanggun99.ailatrieuphu.R;
import thanggun99.ailatrieuphu.adapter.HighScoreAdapter;
import thanggun99.ailatrieuphu.manager.DatabaseManager;

public class HighScoreActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        DatabaseManager databaseManager = new DatabaseManager(App.getContext());
        getListView().setAdapter(new HighScoreAdapter(databaseManager.getHighScore()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getMusicPlayer().pauseBgMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getMusicPlayer().resumeBgMusic();

    }
}
