package gt.scratchgame;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;

import java.util.ArrayList;
import java.util.List;

import gt.scratchgame.adapter.AdapterMainScoreBord;
import gt.scratchgame.bean.BeanPlayerScore;
import gt.scratchgame.database.DBhelper;

/**
 * Created by GT-5 on 2/21/2015.
 */
public class MainScoreBoardActivity extends Activity{
    public DBhelper dbhelper;
    private AdapterMainScoreBord adapterMainScoreBord;
    ListView listView;
    public List<BeanPlayerScore> categoryLists;
    private Typeface typeface;
    TextView textViewMainScoreBoardHeading;
    private StartAppAd startAppAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_score_board);
        listView = (ListView) findViewById(R.id.listViewMainScoreList);
        typeface = Typeface.createFromAsset(getAssets(),"customestyle.TTF");
        textViewMainScoreBoardHeading = (TextView) findViewById(R.id.textViewMainScoreBoardHeading);
        textViewMainScoreBoardHeading.setTypeface(typeface);
        initDB();

        adapterMainScoreBord = new AdapterMainScoreBord(getApplicationContext(),categoryLists);
        listView.setAdapter(adapterMainScoreBord);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        startAppAd = new StartAppAd(this);
        StartAppAd.showSlider(this);
        startAppAd.showAd();
    }

    private void initDB() {
        dbhelper = DBhelper.getInstance(getApplicationContext());
        dbhelper.open();
        categoryLists = new ArrayList<>();

        categoryLists =  dbhelper.getUserData();

    }
}
