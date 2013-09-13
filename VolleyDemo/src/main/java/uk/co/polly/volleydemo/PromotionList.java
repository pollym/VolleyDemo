package uk.co.polly.volleydemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromotionList {

    @SerializedName("blocklist")
    private List<Promotion> promotions;

    public List<Promotion> getPromotions() {
        return promotions;
    }
}
