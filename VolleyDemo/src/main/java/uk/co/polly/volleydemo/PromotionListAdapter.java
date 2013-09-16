package uk.co.polly.volleydemo;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import uk.co.bbc.polly.volleydemo.R;

public class PromotionListAdapter extends BaseAdapter {
    private final ImageLoader.ImageCache mImageCache;
    private List<Promotion> mPromotions;
    private RequestQueue mRequestQueue;

    public PromotionListAdapter(List<Promotion> promotions, RequestQueue requestQueue) {
        mPromotions = promotions;
        mRequestQueue = requestQueue;
        mImageCache = new MemoryLruImageCache();
    }

    @Override
    public int getCount() {
        return mPromotions.size();
    }

    @Override
    public Promotion getItem(int i) {
        return mPromotions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_row, null);
        }
        final Promotion promotion = getItem(i);
        final TextView brandTitleView = (TextView) view.findViewById(R.id.brand_title);
        final TextView titleView = (TextView) view.findViewById(R.id.title);
        final NetworkImageView networkImageView = (NetworkImageView) view.findViewById(R.id.promotion_image);

        //text
        brandTitleView.setText(promotion.getBrandTitle());
        titleView.setText(promotion.getTitle());

        //image
        String imageUrl = promotion.getImageBaseUrl()+"_320_180.jpg";
        networkImageView.setImageUrl(imageUrl, new ImageLoader(mRequestQueue, mImageCache));

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}