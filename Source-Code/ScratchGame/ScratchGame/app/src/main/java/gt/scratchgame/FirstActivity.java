package gt.scratchgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

import gt.scratchgame.constants.AppConstants;

/**
 * Created by GT-5 on 2/21/2015.
 */
public class FirstActivity extends Activity implements View.OnClickListener {
    Button button,buttonPlay;
    Typeface typeface;
    TextView textView7;
    private StartAppAd startAppAd;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        typeface = Typeface.createFromAsset(getAssets(),"customestyle.TTF");

        if(!isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(),"You don't have internet connection.",Toast.LENGTH_LONG).show();
            return;
        }
        startAppAd = new StartAppAd(this);
        button = (Button) findViewById(R.id.mainScoreBoard);
        buttonPlay = (Button) findViewById(R.id.buttonFirstPlay);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setTypeface(typeface);
        button.setTypeface(typeface);
        buttonPlay.setTypeface(typeface);
        button.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        StartAppSDK.init(this, AppConstants.DEVELOPER_ID, AppConstants.APP_ID, true);
        StartAppAd.showSlider(this);
        startAppAd.showAd();

    }


    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonFirstPlay:
                Intent intent1 = new Intent(getApplicationContext(),SelectCategoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.mainScoreBoard:
                Intent intent = new Intent(getApplicationContext(),MainScoreBoardActivity.class);
                startActivity(intent);
                break;
        }

    }
}
