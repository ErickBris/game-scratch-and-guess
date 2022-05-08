package gt.scratchgame;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gt.scratchgame.adapter.AdapterCategory;
import gt.scratchgame.base.Session;
import gt.scratchgame.bean.CategoryList;

/**
 * Created by GT-5 on 2/17/2015.
 */
public class SelectCategoryActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView categoryList;
    AdapterCategory adapterCategory;
    private List<CategoryList> listItem;
    AQuery aQuery;
    Session session;
    TextView textView;
    Typeface typeface;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        aQuery = new AQuery(this.getApplicationContext());
        listItem = new ArrayList<CategoryList>();
        typeface = Typeface.createFromAsset(getAssets(),"customestyle.TTF");
        dialog = new ProgressDialog(this);
        initDialogProperty();
        dialog.show();
        asyncJson();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // intiUI();
    }

    private void intiUI() {

        categoryList = (ListView) findViewById(R.id.listViewCategory);
        textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(typeface);
        session = Session.getInstance();
        adapterCategory = new AdapterCategory(getApplicationContext(),listItem);
        categoryList.setAdapter(adapterCategory);
        dialog.dismiss();
        categoryList.setOnItemClickListener(this);

    }
    private void initDialogProperty() {
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
    }


    public void asyncJson() {

        // perform a Google search in just a few lines of code

        String url = "http://gurutechnolabs.com/demo/scratch/demo.php";

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
                                    CategoryList categoryList = new CategoryList();
                                    categoryList.id = subObject.getInt("id");
                                    categoryList.category = subObject.getString("category");
                                    listItem.add(categoryList);
                                }
                            }
                            intiUI();
                        }

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //Toast.makeText(getApplicationContext(), listItem.size()+"", Toast.LENGTH_LONG).show();
                    //showResult(json, status);
                } else {
                    // ajax error, show error code
                    Toast.makeText(aQuery.getContext(),
                            "Error:" + status.getMessage(), Toast.LENGTH_LONG)
                           .show();
                }
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        session.setSelectedCategory(listItem.get(position));
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
