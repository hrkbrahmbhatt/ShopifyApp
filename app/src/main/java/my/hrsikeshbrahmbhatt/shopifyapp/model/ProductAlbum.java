package my.hrsikeshbrahmbhatt.shopifyapp.model;



import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-26.
 */

public class ProductAlbum {

    @SerializedName("body_html")
    @Expose

    private String bodyHtml;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("images")
    @Expose
    private List<Image> images;

    @SerializedName("vendor")
    @Expose
    private String vendor;

    @SerializedName("product_type")
    @Expose
    private String productType;

    @SerializedName("variants")
    @Expose
    private List<Variants> variants;

    public ProductAlbum(){

    }

    public ProductAlbum(String bodyHtml, String title, List<Image> images, String vendor, String productType, List<Variants> variants){
        this.bodyHtml = bodyHtml;
        this.title = title;
        this.images = images;
        this.vendor = vendor;
        this.productType = productType;
        this.variants = variants;

    }

    public String getBodyHtml(){
        return bodyHtml;
    }


    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public  String getTitle(){
        return title;
    }

    public void  setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }
}
