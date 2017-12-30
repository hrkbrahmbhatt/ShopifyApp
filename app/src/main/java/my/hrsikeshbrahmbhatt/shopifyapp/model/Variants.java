package my.hrsikeshbrahmbhatt.shopifyapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-27.
 */

public class Variants {

    @SerializedName("title")
    @Expose

    private String productcolor;

    @SerializedName("weight")
    @Expose

    private String productWeight;

    @SerializedName("price")
    @Expose

    private String productPrice;

    public String getProductcolor() {
        return productcolor;
    }

    public void setProductcolor(String productcolor) {
        this.productcolor = productcolor;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
