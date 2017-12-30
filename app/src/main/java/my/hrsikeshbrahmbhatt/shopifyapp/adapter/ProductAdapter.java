package my.hrsikeshbrahmbhatt.shopifyapp.adapter;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-26.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;


import java.util.List;

import my.hrsikeshbrahmbhatt.shopifyapp.R;
import my.hrsikeshbrahmbhatt.shopifyapp.activity.DetailActivity;
import my.hrsikeshbrahmbhatt.shopifyapp.model.ProductAlbum;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context mContext;
    private List<ProductAlbum> albumList;



    public ProductAdapter(Context mContext, List<ProductAlbum> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;

    }

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.album_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.MyViewHolder viewHolder, int i) {

        viewHolder.title.setText(albumList.get(i).getTitle());
        viewHolder.bodyHtml.setText(albumList.get(i).getBodyHtml());


        //load team album cover using picasso

        Picasso.with(mContext)
                .load(albumList.get(i).getImages().get(0).getSrc())
                .placeholder(R.drawable.shopify)
                .into(viewHolder.thumbnail);


    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, bodyHtml, vendor, productType, productColor, productWeight, productPrice;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            bodyHtml = view.findViewById(R.id.body_html);
            thumbnail =  view.findViewById(R.id.thumbnail);
            vendor =  view.findViewById(R.id.vendor_title);
            productType = view.findViewById(R.id.product_type);
            productColor = view.findViewById(R.id.product_color);
            productWeight = view.findViewById(R.id.product_weight);
            productPrice = view.findViewById(R.id.product_price);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                     int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                       ProductAlbum clickedDataIteam = albumList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("product_title", albumList.get(pos).getTitle());
                        intent.putExtra("product_body", albumList.get(pos).getBodyHtml());
                        intent.putExtra("thumbnail", albumList.get(pos).getImages().get(0).getSrc());
                        intent.putExtra("vendor_title", albumList.get(pos).getVendor());
                        intent.putExtra("product_type", albumList.get(pos).getProductType());
                        intent.putExtra("product_color", albumList.get(pos).getVariants().get(0).getProductcolor());
                        intent.putExtra("product_weight", albumList.get(pos).getVariants().get(0).getProductWeight());
                        intent.putExtra("product_price", albumList.get(pos).getVariants().get(0).getProductPrice());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(),"Product name is " + clickedDataIteam.getTitle() , Toast.LENGTH_SHORT).show();
                    }
                }


            });
        }
    }

}
