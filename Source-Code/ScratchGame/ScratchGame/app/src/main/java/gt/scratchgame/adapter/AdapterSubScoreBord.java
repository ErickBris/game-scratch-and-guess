package gt.scratchgame.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

import gt.scratchgame.R;
import gt.scratchgame.base.Session;
import gt.scratchgame.bean.BeanEachQuetionScore;
import gt.scratchgame.bean.CategoryList;

/**
 * Created by GT-5 on 2/17/2015.
 */
public class AdapterSubScoreBord extends BaseAdapter {

    Context context;
    public List<BeanEachQuetionScore> categoryLists;
    LayoutInflater layoutInflater;
    AQuery aQuery;
    Session session;
    LinearLayout linearLayout;
    Typeface typeface;
    public AdapterSubScoreBord(Context context, List<BeanEachQuetionScore> scoreBeen)
    {
        session = Session.getInstance();
        this.context = context;
        categoryLists = scoreBeen;
        aQuery = new AQuery(this.context);
        typeface = Typeface.createFromAsset(context.getAssets(),"customestyle.TTF");
    }

    @Override
    public int getCount() {
        return categoryLists.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


       /* TextView quTextView = (TextView) convertView.findViewById(R.id.textViewSubScoreBordQuetionNo);
        quTextView.setText(11+"");*/
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.sub_score_bord_item,null);
            viewHolder.quetionNo = (TextView) convertView.findViewById(R.id.textViewSubScoreBordQuetionNo);
            viewHolder.quetionNo.setTypeface(typeface);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageViewSubScoreBordImage);
            viewHolder.trueAns = (TextView) convertView.findViewById(R.id.textViewSubScoreBordTrueAns);
            viewHolder.trueAns.setTypeface(typeface);
            viewHolder.playerAns = (TextView) convertView.findViewById(R.id.textViewSubScoreBordPlayerAns);
            viewHolder.playerAns.setTypeface(typeface);
            viewHolder.playerScore = (TextView) convertView.findViewById(R.id.textViewSubScoreBordScore);
            viewHolder.playerScore.setTypeface(typeface);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        linearLayout = (LinearLayout) convertView.findViewById(R.id.itemback);
        viewHolder.quetionNo.setText(categoryLists.get(position).quetionNo+"");
        aQuery.id(viewHolder.imageView).image(categoryLists.get(position).url, true, true);
        viewHolder.trueAns.setText(categoryLists.get(position).trueAns);
        viewHolder.playerAns.setText(categoryLists.get(position).playerAns);
        viewHolder.playerScore.setText(categoryLists.get(position).quetionScore+"");

        if(categoryLists.get(position).trueAns == categoryLists.get(position).playerAns) {
            linearLayout.setBackgroundColor(Color.parseColor("#23a003"));
        }
        else {
            linearLayout.setBackgroundColor(Color.parseColor("#0b3001"));
        }
        return convertView;
    }

    class ViewHolder
    {
        TextView quetionNo;
        ImageView imageView;
        TextView trueAns;
        TextView playerAns;
        TextView playerScore;
    }
}
