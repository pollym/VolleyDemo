package uk.co.polly.volleydemo;

import com.google.gson.annotations.SerializedName;

public class Promotion {

    public static class Image {
        String id;

        @SerializedName("image_type")
        String imageType;
    }

    private String type;
    private String title;
    private String url;
    private Image image;

    @SerializedName("short_synopsis")
    private String shortSynopsis;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Image getImage() {
        return image;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public String getType() {
        return type;
    }
}
