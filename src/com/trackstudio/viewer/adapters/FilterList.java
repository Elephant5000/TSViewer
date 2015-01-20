package com.trackstudio.viewer.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.models.FilterItem;

import java.util.List;

/**
 * Filter list adapter.
 */
public class FilterList extends ArrayAdapter<FilterItem> {

    /**
     * Default constructor.
     * @param context Context
     * @param items Items
     */
    public FilterList(final Activity context, final List<FilterItem> items) {
        super(context, R.layout.filters_fragment, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            convertView = inflater.inflate(R.layout.filters_fragment, parent, false);
            viewHolder = this.initConvertView(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        this.updateFilterItem(viewHolder, position);
        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.rgb(245, 245, 245));
        } else {
            convertView.setBackgroundColor(Color.rgb(238, 236, 243));
        }
        return convertView;
    }

    /**
     * Initiate convert view.
     * @param convertView Convert view
     * @return View holder
     */
    private ViewHolder initConvertView(View convertView) {
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.id = (TextView) convertView.findViewById(R.id.id);
        viewHolder.name = (TextView) convertView.findViewById(R.id.name);
        convertView.setTag(viewHolder);
        return viewHolder;
    }

    /**
     * Update current item.
     * @param viewHolder View holder
     * @param position Position
     */
    private void updateFilterItem(final ViewHolder viewHolder, final int position) {
        final FilterItem item = this.getItem(position);
        viewHolder.id.setText(item.getId());
        viewHolder.name.setText(item.getName());
    }


    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     */
    private static class ViewHolder {
        TextView id;
        TextView name;
    }
}