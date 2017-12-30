package my.hrsikeshbrahmbhatt.shopifyapp.activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import my.hrsikeshbrahmbhatt.shopifyapp.R;
import my.hrsikeshbrahmbhatt.shopifyapp.adapter.ProductAdapter;
import my.hrsikeshbrahmbhatt.shopifyapp.api.Client;
import my.hrsikeshbrahmbhatt.shopifyapp.api.Service;
import my.hrsikeshbrahmbhatt.shopifyapp.model.ProductAlbum;
import my.hrsikeshbrahmbhatt.shopifyapp.model.ProductResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hrsikeshbrahmbhatt on 2017-12-26.
 */


public class MainActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<ProductAlbum> albumList;
    ProgressDialog pd;

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        new LoadAlbumsAsync().execute();


    }

    //Get a Search Menu for Search Functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    //Search Functionality (Method)

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                filterItems(newText);
                return true;
            }
        });
    }

    //Logic For Filter the Data from RecyclerView

    private void filterItems(String text) {
        text = text.toLowerCase();

        List<ProductAlbum> filteredItems = new ArrayList<>();

        for (ProductAlbum album: albumList) {
            String itemName = album.getTitle().toLowerCase();
            if(itemName.contains(text))
                filteredItems.add(album);
        }
        recyclerView.setAdapter(new ProductAdapter(getApplicationContext(), filteredItems));

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }


    }


    /**
     * Converting dp to pixel
     */

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }


    @Override
    public void onBackPressed(){
        // DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        return super.onOptionsItemSelected(menuItem);

    }



    @Override
    public void finish() {
        super.finish();
        MainActivity.this.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }

    //Async Task Created UI Thread to handle UI, To Avoid Window leak and Frame skipped problems

    class LoadAlbumsAsync extends AsyncTask<Void, Void, List<ProductAlbum>>{

        @Override
        protected void onPreExecute() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("Fetching Data....");
                    pd.setCancelable(false);
                    pd.show();
                }
            });


            try {
                Glide.with(MainActivity.this).load(R.drawable.shopify7).into((ImageView) findViewById(R.id.backdrop));
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    @Override
    protected List<ProductAlbum> doInBackground(Void... voids) {



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new ProductAdapter(MainActivity.this, albumList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        try {
            Client client = new Client();
            Service service = client.getClient().create(Service.class);

            Call<ProductResponse> call = service.getAlbums();
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    List<ProductAlbum> iteams = response.body().getAlbums();
                    albumList = iteams;
                    recyclerView.setAdapter(new ProductAdapter(getApplicationContext(), iteams));
                    // pd.dismiss();

                }
                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data! Please Check your Internet Connection", Toast.LENGTH_LONG).show();
                    //pd.dismiss();

                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        return albumList;
    }

    @Override
    protected void onPostExecute(List<ProductAlbum> list) {
        super.onPostExecute(list);
        pd.dismiss();
    }

}

}


