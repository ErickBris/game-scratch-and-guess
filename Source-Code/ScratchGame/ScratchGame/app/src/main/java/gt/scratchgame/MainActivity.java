package gt.scratchgame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import gt.scratchgame.base.Session;
import gt.scratchgame.bean.AnsOptionBean;
import gt.scratchgame.bean.BeanEachQuetionScore;
import gt.scratchgame.bean.QuestionAnsbean;
import gt.scratchgame.constants.AppConstants;
import gt.scratchgame.database.DBhelper;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private WScratchView scratchView;
    private TextView percentageView, scorelabel, textViewNo, textViewcounter, textViewTotalScore, textView3;
    private float mPercentage;
    public DBhelper dbhelper;
    AQuery aQuery;
    private static QuestionAnsbean questionAnsbean1;
    private static String trueAns = null;
    private static int quetionNumber = 0;
    protected Button gameActivityButton1, gameActivityButton2, gameActivityButton3, gameActivityButton4;
    ImageView imageView;
    Session session;
    Bitmap bitmap;
    Typeface typeface;
    private static float totalScore = 0;
    public List<QuestionAnsbean> questionAnsList;
    private List<BeanEachQuetionScore> scoreList;
    ProgressDialog dialog;
    private StartAppAd startAppAd;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        questionAnsList = new ArrayList<QuestionAnsbean>();
        startAppAd = new StartAppAd(this);
        aQuery = new AQuery(this);
        session = Session.getInstance();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scratchview1);
        scoreList = new ArrayList<BeanEachQuetionScore>();
        scratchView = (WScratchView) findViewById(R.id.scratch_view);
        scratchView.setScratchBitmap(bitmap);
        typeface = Typeface.createFromAsset(getAssets(),"customestyle.TTF");
        totalScore = 0;
        quetionNumber = 0;
        dialog = new ProgressDialog(this);
        initDialogProperty();
        asyncJson();

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        StartAppSDK.init(this, AppConstants.DEVELOPER_ID, AppConstants.APP_ID, true);
       // StartAppAd.showSlider(this);
        startAppAd.showAd();

    }

    private void initDialogProperty() {
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
    }

    private void initUI() {
        percentageView = (TextView) findViewById(R.id.percentage);
        percentageView.setTypeface(typeface);
        scorelabel = (TextView) findViewById(R.id.scorelabel);
        scorelabel.setTypeface(typeface);
        textViewNo = (TextView) findViewById(R.id.textViewNo);
        textViewNo.setTypeface(typeface);
        textViewcounter = (TextView) findViewById(R.id.textViewcounter);
        textViewcounter.setTypeface(typeface);
        textViewTotalScore = (TextView) findViewById(R.id.textViewTotalScore);
        textViewTotalScore.setTypeface(typeface);
        textViewTotalScore.setText(totalScore + "");
        textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setTypeface(typeface);
        dbhelper = DBhelper.getInstance(getApplicationContext());
        dbhelper.open();
        initDb();

        Collections.shuffle(questionAnsList);

         /*Game Activity 4 option button*/
        gameActivityButton1 = (Button) findViewById(R.id.gameActivityButton1);
        gameActivityButton2 = (Button) findViewById(R.id.gameActivityButton2);
        gameActivityButton3 = (Button) findViewById(R.id.gameActivityButton3);
        gameActivityButton4 = (Button) findViewById(R.id.gameActivityButton4);

        gameActivityButton1.setTypeface(typeface);
        gameActivityButton2.setTypeface(typeface);
        gameActivityButton3.setTypeface(typeface);
        gameActivityButton4.setTypeface(typeface);

        gameActivityButton1.setOnClickListener(this);
        gameActivityButton2.setOnClickListener(this);
        gameActivityButton3.setOnClickListener(this);
        gameActivityButton4.setOnClickListener(this);



        quetionInitialize();
        // customize attribute programmatically
        scratchView.setScratchable(true);
        scratchView.setRevealSize(50);
        scratchView.setAntiAlias(true);
       // scratchView.setOverlayColor(Color.RED);

        scratchView.setBackgroundClickable(true);

        // add callback for update scratch percentage
        scratchView.setOnScratchCallback(new WScratchView.OnScratchCallback() {

            @Override
            public void onScratch(float percentage) {
                updatePercentage(percentage);
            }

            @Override
            public void onDetach(boolean fingerDetach) {
               /* if(mPercentage > 1){
                    scratchView.setScratchAll(true);
                    updatePercentage(100);
                }*/
            }
        });
        updatePercentage(0f);
    }
    private void updatePercentage(float percentage) {
        mPercentage = percentage;
        String percentage2decimal = String.format("%.2f", (100 - percentage));
        percentageView.setText(percentage2decimal);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void initDb()     {

       // Toast.makeText(getApplicationContext()," DB size-- "+dbhelper.getUserData().size(),Toast.LENGTH_LONG).show();
       // dbhelper.addData(session.getSelectedCategory().id,"guru",123.36f);


    }

    public void asyncJson() {
        // String url = "http://gurutechnolabs.com/demo/kids/demo.php?category=+""&level=level";
       // Toast.makeText(getApplicationContext(),"aaaaaa",Toast.LENGTH_LONG).show();
        int id = session.getSelectedCategory().id;
        dialog.show();
        String url = "http://gurutechnolabs.com/demo/scratch/demo.php?category="+id;
        Log.d("*************",url);
        aQuery.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                if (json != null) {
                    try {
                        JSONObject mainJsonObject = new JSONObject(json.toString());
                        if(mainJsonObject != null)
                        {
                            JSONArray list = mainJsonObject.getJSONArray("data");
                            if(list != null)
                            {
                                for(int i = 0 ; i < list.length() ; i++)
                                {
                                    JSONObject subObject = list.getJSONObject(i);
                                    //	aQuery.id(R.id.result).visible().text(subObject.toString());
                                    QuestionAnsbean questionAnsbean = new QuestionAnsbean();
                                    questionAnsbean.id = subObject.getInt("id");
                                    questionAnsbean.cat_id = subObject.getInt("cat_id");
                                    questionAnsbean.url = subObject.getString("url");
                                    JSONArray questionAnsOptionArrary = subObject.getJSONArray("answers");
                                    for (int j = 0; j < questionAnsOptionArrary.length(); j++)
                                    {
                                        JSONObject suObject1 = questionAnsOptionArrary.getJSONObject(j);
                                        AnsOptionBean ansOptionBean = new AnsOptionBean();
                                        ansOptionBean.answer = suObject1.getString("answer");
                                        ansOptionBean.result = suObject1.getInt("result");
                                        questionAnsbean.ansOptionList.add(ansOptionBean);
                                    }

                                    questionAnsList.add(questionAnsbean);

                                    // session.setOperationLists1(questionAnsbean);
                                    // Toast.makeText(getApplicationContext(),levelLists.size()+"",Toast.LENGTH_LONG).show();
                                    //gameLevelGridAdapter.notifyDataSetChanged();
                                }
                                // Collections.shuffle(ansOptionList);
                            }
                            // Toast.makeText(getApplicationContext(),questionAnsList.size()+" This is list size in asin aquery ",Toast.LENGTH_LONG).show();
                            initUI();
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //Toast.makeText(getApplicationContext(), listItem.size()+"", Toast.LENGTH_LONG).show();
                    //showResult(json, status);

                } else {

                    // ajax error, show error code
                   /* Toast.makeText(aQuery.getContext(),
                            "Error:" + status.getMessage(), Toast.LENGTH_LONG)
                            .show();*/
                }
            }
        });
    }
    private void quetionInitialize() {
        scratchView.resetView();
        scratchView.setScratchAll(false);
        textViewcounter.setText(quetionNumber+"");
        questionAnsbean1 = new QuestionAnsbean();
        questionAnsbean1 = questionAnsList.get(quetionNumber);
        dialog.dismiss();
        aQuery.id(R.id.extra).progress(dialog).image(questionAnsList.get(quetionNumber).url, true, true);
        ansOptionInitialize();
    }
    /*Quation ans intialize and set*/
    private void ansOptionInitialize() {
        try {
            Collections.shuffle(questionAnsbean1.ansOptionList);
            gameActivityButton1.setText(questionAnsbean1.ansOptionList.get(0).answer);
            gameActivityButton2.setText(questionAnsbean1.ansOptionList.get(1).answer);
            gameActivityButton3.setText(questionAnsbean1.ansOptionList.get(2).answer);
            gameActivityButton4.setText(questionAnsbean1.ansOptionList.get(3).answer);


            if(questionAnsbean1.ansOptionList.get(0).result == 1)
            {
                trueAns = questionAnsbean1.ansOptionList.get(0).answer;
            }
            else if(questionAnsbean1.ansOptionList.get(1).result == 1)
            {
                trueAns = questionAnsbean1.ansOptionList.get(1).answer;
            }
            else if(questionAnsbean1.ansOptionList.get(2).result == 1)
            {
                trueAns = questionAnsbean1.ansOptionList.get(2).answer;
            }
            else if(questionAnsbean1.ansOptionList.get(3).result == 1)
            {
                trueAns = questionAnsbean1.ansOptionList.get(3).answer;
            }
        }
        catch (Exception e)
        {

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gameActivityButton1:
                checkAns(gameActivityButton1.getText().toString());
                break;

            case R.id.gameActivityButton2:
                checkAns(gameActivityButton2.getText().toString());
                break;

            case R.id.gameActivityButton3:
                checkAns(gameActivityButton3.getText().toString());
                break;

            case R.id.gameActivityButton4:
                checkAns(gameActivityButton4.getText().toString());
                break;

            default:
                break;
        }
    }

    private void checkAns(String text) {

        BeanEachQuetionScore beanEachQuetionScore = new BeanEachQuetionScore();
        beanEachQuetionScore.quetionNo = quetionNumber;
        beanEachQuetionScore.url = questionAnsList.get(quetionNumber).url;
        beanEachQuetionScore.playerAns = text;
        beanEachQuetionScore.trueAns = trueAns;
        beanEachQuetionScore.quetionScore = Double.parseDouble(percentageView.getText().toString());
        scoreList.add(beanEachQuetionScore);

        if (text.equals(trueAns)) {

            totalScore = totalScore + Float.parseFloat(percentageView.getText().toString());
            textViewTotalScore.setText(String.format("%.2f", totalScore));
            quetionNumber++;
            if(quetionNumber < questionAnsList.size()) {
                quetionInitialize();
            }
            else {
                gameOver();
            }

        }
        else {

            totalScore = totalScore - Float.parseFloat(percentageView.getText().toString());
            textViewTotalScore.setText(String.format("%.2f", totalScore));
            quetionNumber++;
            if(quetionNumber < questionAnsList.size()) {
                quetionInitialize();
            }
            else {
                gameOver();
            }

        }
    }

    private void gameOver() {
        session.setScoreBeen(scoreList);
        session.setTotalScoreInSession(Float.parseFloat(textViewTotalScore.getText().toString()));
        Intent intent = new Intent(getApplicationContext(),ScoreBord.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),SelectCategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
