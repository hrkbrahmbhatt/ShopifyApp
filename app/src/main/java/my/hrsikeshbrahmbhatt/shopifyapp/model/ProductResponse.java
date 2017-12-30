package my.hrsikeshbrahmbhatt.shopifyapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-26.
 */

public class ProductResponse {

   


    @SerializedName("products")
    @Expose

    private List<ProductAlbum> albums;

    public List<ProductAlbum> getAlbums(){

        return albums;
    }
    public void setAlbums(List<ProductAlbum>albums){
        this.albums = albums;
    }


}
