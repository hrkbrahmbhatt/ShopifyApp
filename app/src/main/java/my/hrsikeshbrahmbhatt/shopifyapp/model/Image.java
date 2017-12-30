package my.hrsikeshbrahmbhatt.shopifyapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-27.
 */

public class Image {

    @SerializedName("src")
    @Expose

    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
