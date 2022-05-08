package gt.scratchgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import gt.scratchgame.base.Session;
import gt.scratchgame.database.DBhelper;

/**
 * Created by GT-5 on 2/20/2015.
 */
public class ScoreSaveActivity extends Activity implements View.OnClickListener {

    public DBhelper dbhelper;
    Session session;
    EditText editText;
    TextView textView, textView4;
    Button button;
    private Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);
        session = Session.getInstance();
        typeface = Typeface.createFromAsset(getAssets(),"customestyle.TTF");
        editText = (EditText) findViewById(R.id.textViewUserName);
        textView = (TextView) findViewById(R.id.textViewDisplayScore);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setTypeface(typeface);
        textView.setTypeface(typeface);
        editText.setTypeface(typeface);
        button = (Button) findViewById(R.id.button);
        dbhelper = DBhelper.getInstance(getApplicationContext());
        dbhelper.open();
        textView.setText(session.getTotalScoreInSession()+"");
        button.setOnClickListener(this);
        initDb();

    }
    @Override
    protected void onResume() {
        super.onResume();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void initDb()     {

        // Toast.makeText(getApplicationContext()," DB size-- "+dbhelper.getUserData().size(),Toast.LENGTH_LONG).show();



    }

    @Override
    public void onClick(View v) {
        dbhelper.addData(session.getSelectedCategory().id,editText.getText().toString(),session.getTotalScoreInSession());
        Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
