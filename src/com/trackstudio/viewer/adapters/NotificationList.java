package com.trackstudio.viewer.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.trackstudio.viewer.R;
import com.trackstudio.viewer.models.NotificationItem;

import java.util.List;

/**
 * Filter list adapter.
 */
public class NotificationList extends ArrayAdapter<NotificationItem> {
    /**
     * Context.
     */
    private final Context context;

    /**
     * Default constructor.
     * @param context Context
     * @param items Items
     */
    public NotificationList(final Activity context, final List<NotificationItem> items) {
        super(context, R.layout.notifications_fragment, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.notifications_fragment, parent, false);
            viewHolder = this.initConvertView(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        this.updateFilterItem(viewHolder, position);
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
        viewHolder.date = (TextView) convertView.findViewById(R.id.date);
        viewHolder.text = (TextView) convertView.findViewById(R.id.text);
        convertView.setTag(viewHolder);
        return viewHolder;
    }

    /**
     * Update current item.
     * @param viewHolder View holder
     * @param position Position
     */
    private void updateFilterItem(final ViewHolder viewHolder, final int position) {
        final NotificationItem item = this.getItem(position);
        viewHolder.id.setText(item.getId());
        viewHolder.date.setText(item.getDate());
        viewHolder.text.setText(item.getText());
    }


    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     */
    private static class ViewHolder {
        TextView id;
        TextView date;
        TextView text;
    }
}