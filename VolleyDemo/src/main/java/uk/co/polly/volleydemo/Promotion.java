package uk.co.polly.volleydemo;

import com.google.gson.annotations.SerializedName;

public class Promotion {

    private String title;

    @SerializedName("brand_title")
    private String brandTitle;

    @SerializedName("my_image_base_url")
    private String imageBaseUrl;

    public String getTitle() {
        return title;
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public String getBrandTitle() {
        return brandTitle;
    }
}
