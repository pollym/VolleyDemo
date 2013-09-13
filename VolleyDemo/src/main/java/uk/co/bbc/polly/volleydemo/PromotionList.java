package uk.co.bbc.polly.volleydemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mceldp01 on 13/09/2013.
 */
public class PromotionList {

    @SerializedName("blocklist")
    private List<Promotion> promotions;

    public List<Promotion> getPromotions() {
        return promotions;
    }
}
