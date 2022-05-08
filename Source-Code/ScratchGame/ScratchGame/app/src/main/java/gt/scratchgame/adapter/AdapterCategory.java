package gt.scratchgame.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import gt.scratchgame.R;
import gt.scratchgame.bean.CategoryList;

/**
 * Created by GT-5 on 2/17/2015.
 */
public class AdapterCategory extends BaseAdapter {

    Context context;
    public List<CategoryList> categoryLists;
    LayoutInflater layoutInflater;
    Typeface typeface;
    public AdapterCategory(Context context, List<CategoryList> list)
    {
        this.context = context;
        categoryLists = list;
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
        return categoryLists.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.category_item,null);
            viewHolder.categoryListItem = (TextView) convertView.findViewById(R.id.categoryItemTextView);
            viewHolder.categoryListItem.setTypeface(typeface);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.categoryListItem.setText(categoryLists.get(position).category);
        return convertView;
    }

    class ViewHolder
    {
        TextView categoryListItem;
    }
}
