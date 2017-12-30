package my.hrsikeshbrahmbhatt.shopifyapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-26.
 */

public class Client {


    public static final String BASE_URL = "https://shopicruit.myshopify.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
