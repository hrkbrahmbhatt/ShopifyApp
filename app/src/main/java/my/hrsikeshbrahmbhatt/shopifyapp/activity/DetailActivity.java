package my.hrsikeshbrahmbhatt.shopifyapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import my.hrsikeshbrahmbhatt.shopifyapp.R;


/**
 * Created by hrsikeshbrahmbhatt on 2017-12-27.
 */

public class DetailActivity extends AppCompatActivity {

    TextView title, body, vendor, productType, productColor, productWeight, productPrice;
    ImageView imageView;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        imageView =  findViewById(R.id.thumbnail_image_header);
        title =  findViewById(R.id.product_title);
        body =  findViewById(R.id.product_body);
        vendor =  findViewById(R.id.vendor_title);
        productType =  findViewById(R.id.product_type);
        productColor =  findViewById(R.id.product_color);
        productWeight =  findViewById(R.id.product_weight);
        productPrice =  findViewById(R.id.product_price);

        String pro_title= getIntent().getExtras().getString("product_title");
        String pro_body = getIntent().getExtras().getString("product_body");
        String thumbnail = getIntent().getExtras().getString("thumbnail");
        String vendor_name = getIntent().getExtras().getString("vendor_title");
        String product_Type = getIntent().getExtras().getString("product_type");
        String product_Color = getIntent().getExtras().getString("product_color");
        String product_Weight = getIntent().getExtras().getString("product_weight");
        String product_Price = getIntent().getExtras().getString("product_price");

        vendor.setText(vendor_name);
        title.setText(pro_title);
        body.setText(pro_body);
        productType.setText(product_Type);
        productColor.setText(product_Color);
        productWeight.setText(product_Weight);
        productPrice.setText(product_Price);

        Glide.with(this)
                .load(thumbnail)
                .placeholder(R.drawable.shopify)
                .into(imageView);


        Button goBack =  findViewById(R.id.goUpP1);


        final ScrollView scrollView = findViewById(R.id.ScrollView02);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });



    }
}
