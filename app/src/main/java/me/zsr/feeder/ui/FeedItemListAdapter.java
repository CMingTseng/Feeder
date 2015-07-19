package me.zsr.feeder.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.jsoup.Jsoup;

import java.text.SimpleDateFormat;

import me.zsr.feeder.App;
import me.zsr.feeder.R;
import me.zsr.feeder.dao.FeedItem;
import me.zsr.feeder.dao.FeedSource;
import me.zsr.feeder.util.DateUtil;
import me.zsr.feeder.util.VolleySingleton;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * @description:
 * @author: Zhangshaoru
 * @date: 15-6-12
 */
public class FeedItemListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private FeedSource mFeedSource;
    private LayoutInflater mLayoutInflater;

    public FeedItemListAdapter(FeedSource feedSource) {
        mFeedSource = feedSource;
        mLayoutInflater = LayoutInflater.from(App.getInstance());
    }

    @Override
    public int getCount() {
        return mFeedSource.getFeedItems().size();
    }

    @Override
    public Object getItem(int position) {
        return mFeedSource.getFeedItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        FeedItem feedItem = mFeedSource.getFeedItems().get(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.feed_item_list_item, null);
            viewHolder.imageView = (NetworkImageView) convertView.findViewById(R.id.feed_item_list_item_img);
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.feed_item_list_item_title_txt);
            viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.feed_item_list_item_description_txt);
            viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.feed_item_list_item_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageUrl(mFeedSource.getFavicon(), VolleySingleton.getInstance().getImageLoader());
        viewHolder.titleTextView.setText(feedItem.getTitle());
        viewHolder.descriptionTextView.setText(Jsoup.parse(feedItem.getDescription()).text());
        viewHolder.timeTextView.setText(DateUtil.formatTime(feedItem.getDate()));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        FeedItem feedItem = mFeedSource.getFeedItems().get(position);
        if (convertView == null) {
            headerViewHolder = new HeaderViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.feed_item_list_headers, null);
            headerViewHolder.textView = (TextView) convertView.findViewById(R.id.feed_item_list_header_txt);
            convertView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }
        headerViewHolder.textView.setText(DateUtil.formatDate(feedItem.getDate()));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        //todo format date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD");
        long id = Long.valueOf(simpleDateFormat.format(mFeedSource.getFeedItems().get(position).getDate()));
        return id;
    }

    class HeaderViewHolder {
        TextView textView;
    }

    class ViewHolder {
        NetworkImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView timeTextView;
    }
}
