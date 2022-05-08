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
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;

import gt.scratchgame.R;
import gt.scratchgame.base.Session;
import gt.scratchgame.bean.BeanEachQuetionScore;
import gt.scratchgame.bean.BeanPlayerScore;

/**
 * Created by GT-5 on 2/17/2015.
 */
public class AdapterMainScoreBord extends BaseAdapter {

    Context context;
    public List<BeanPlayerScore> categoryLists;
    LayoutInflater layoutInflater;
    AQuery aQuery;
    Session session;
    LinearLayout linearLayout;
    Typeface typeface;
    public AdapterMainScoreBord(Context context, List<BeanPlayerScore> scoreBeen)
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
            convertView = layoutInflater.inflate(R.layout.item_main_score_board,null);
            viewHolder.No = (TextView) convertView.findViewById(R.id.textViewMainScoreBoardNo);
            viewHolder.No.setTypeface(typeface);
            viewHolder.playerName = (TextView) convertView.findViewById(R.id.textViewMainScoreBoardPlayerName);
            viewHolder.playerName.setTypeface(typeface);
            viewHolder.playerScore = (TextView) convertView.findViewById(R.id.textViewMainScoreBoardScore);
            viewHolder.playerScore.setTypeface(typeface);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        linearLayout = (LinearLayout) convertView.findViewById(R.id.itemback);
        viewHolder.No.setText(position+"");
        viewHolder.playerName.setText(categoryLists.get(position).playerName);
        viewHolder.playerScore.setText(categoryLists.get(position).playerScore+"");

        return convertView;
    }

    class ViewHolder
    {
        TextView No;
        TextView playerName;
        TextView playerScore;
    }
}
