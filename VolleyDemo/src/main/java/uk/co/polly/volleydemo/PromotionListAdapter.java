package uk.co.polly.volleydemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PromotionListAdapter extends BaseAdapter {
    private List<Promotion> mPromotions;

    public PromotionListAdapter(List<Promotion> promotions) {
        mPromotions = promotions;
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
        Promotion promotion = getItem(i);
        TextView promotionTitleView = (TextView) view.findViewById(R.id.promotion_title);
        promotionTitleView.setText(promotion.getTitle());
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
