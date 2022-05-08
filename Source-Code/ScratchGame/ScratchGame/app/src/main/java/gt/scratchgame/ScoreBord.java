package gt.scratchgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import gt.scratchgame.adapter.AdapterSubScoreBord;
import gt.scratchgame.base.Session;

/**
 * Created by GT-5 on 2/19/2015.
 */
public class ScoreBord extends Activity implements View.OnClickListener {
    private AdapterSubScoreBord adapterSubScoreBord;
    ListView subScoreBordList;
    Session session;
    TextView textViewScoreBordTotalScore, textViewTotalfooter, textView2, t1, t2, t3, t4, t5;
    private Button buttonSaveScore;
    private float totalScore;
    private Button cancel,save;
    Typeface typeface;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_bord);
        session = Session.getInstance();
        initUI();
    }

    private void initUI() {


        typeface = Typeface.createFromAsset(getAssets(),"customestyle.TTF");
        findView();
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        View footerView = ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listview_footer, null, false);
        subScoreBordList.addFooterView(footerView);
        adapterSubScoreBord = new AdapterSubScoreBord(getApplicationContext(),session.getScoreBeen());
        subScoreBordList.setAdapter(adapterSubScoreBord);
        textViewScoreBordTotalScore = (TextView) footerView.findViewById(R.id.textViewScoreBordTotalScore);
        textViewScoreBordTotalScore.setTypeface(typeface);
        textViewScoreBordTotalScore.setText(String.format("%.2f", session.getTotalScoreInSession()));
        textViewTotalfooter = (TextView) footerView.findViewById(R.id.textViewTotalfooter);
        textViewTotalfooter.setTypeface(typeface);
        cancel = (Button) findViewById(R.id.buttonCancel);
        cancel.setTypeface(typeface);
        save = (Button) findViewById(R.id.buttonSave);
        save.setTypeface(typeface);

        cancel.setOnClickListener(this);
        save.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void findView() {
        subScoreBordList = (ListView) findViewById(R.id.subScoreBordListView);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTypeface(typeface);
        t1 = (TextView) findViewById(R.id.t1);
        t1.setTypeface(typeface);
        t2 = (TextView) findViewById(R.id.t2);
        t2.setTypeface(typeface);
        t3 = (TextView) findViewById(R.id.t3);
        t3.setTypeface(typeface);
        t4 = (TextView) findViewById(R.id.t4);
        t4.setTypeface(typeface);
        t5 = (TextView) findViewById(R.id.t5);
        t5.setTypeface(typeface);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        session.getScoreBeen().clear();
        Intent intent = new Intent(getApplicationContext(),SelectCategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCancel:

                Intent intent = new Intent(getApplicationContext(), SelectCategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.buttonSave:

                Intent intentSave = new Intent(getApplicationContext(),ScoreSaveActivity.class);
                intentSave.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentSave);
                break;
        }
        }

}

