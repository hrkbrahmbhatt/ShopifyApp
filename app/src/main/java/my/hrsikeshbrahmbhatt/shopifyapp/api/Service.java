package my.hrsikeshbrahmbhatt.shopifyapp.api;



import my.hrsikeshbrahmbhatt.shopifyapp.model.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-26.
 */

public interface Service {

    @GET("/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<ProductResponse> getAlbums();



}
