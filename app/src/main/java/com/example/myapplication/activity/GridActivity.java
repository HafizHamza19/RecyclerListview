package com.hafiz.myapplication.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.hafiz.myapplication.Vehicle;
import com.hafiz.myapplication.adapter.GridAdapter;
import com.hafizhamza.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GridActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ArrayList<Vehicle> items;

    private List<Vehicle> presidentList;
    private GridAdapter presidentGridAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        items = new ArrayList<Vehicle>();
        ButterKnife.bind(this);
        layoutManager = new GridLayoutManager(this, 2);

        swipeContainer.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getVehicle();
            }
        });

        getVehicle();

        presidentList = new ArrayList<>();
        presidentGridAdapter = new GridAdapter(this, presidentList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHorizontalScrollBarEnabled(true);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(16), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(presidentGridAdapter);
    }

    private void getVehicle() {
        items.clear();
        items.add(new Vehicle(1,"Civic","","All Wheel Drive","sadsa","Luxury/SUV","sad","sad","212"));
        items.add(new Vehicle(1,"Corolla","","All Wheel Drive","sadsa","Luxury/SUV","x","sad","212"));
        items.add(new Vehicle(1,"Cultus","","All Wheel Drive","sadsa","Luxury/SUV","xx","sad","212"));
        items.add(new Vehicle(1,"Swift","","All Wheel Drive","sadsa","Standard","xxx","sad","212"));
        items.add(new Vehicle(1,"Audi","","All Wheel Drive","sadsa","Standard","xxxx","sad","212"));
        items.add(new Vehicle(1,"Mehran","","All Wheel Drive","sadsa","Economy","xxxxx","sad","212"));
        //VehicleAdapter.notifyDataSetChanged();

        recyclerView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                        for (int i = 0; i < recyclerView.getChildCount(); i++) {
                            View v = recyclerView.getChildAt(i);
                            v.setAlpha(0.0f);
                            v.animate().alpha(1.0f)
                                    .setDuration(300)
                                    .setStartDelay(i * 50)
                                    .start();
                        }

                        return true;
                    }
                });
        swipeContainer.setRefreshing(false);

    }


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
                    outRect.top = 0;
                }
                outRect.bottom = 0; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = 0; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @OnClick(R.id.buttonLeft)
    protected void backToMain(View view) {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
