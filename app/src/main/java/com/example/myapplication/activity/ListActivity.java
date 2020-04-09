package com.hafizhamza.myapplication.activity;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hafizhamza.myapplication.Vehicle;
import com.hafizhamza.myapplication.R;
import com.hafizhamza.myapplication.adapter.ListAdapter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    ArrayList<Vehicle> items;
    private List<Vehicle> VehicleList;
    private ListAdapter VehicleListAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        VehicleList = new ArrayList<>();
        items = new ArrayList<Vehicle>();
        ButterKnife.bind(this);
        Log.d("Hello","fsf");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        swipeContainer.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getVehicle();
            }

        });

        getVehicle();
        VehicleListAdapter = new ListAdapter(this,items);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHorizontalScrollBarEnabled(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(VehicleListAdapter);
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

    @OnClick(R.id.buttonLeft)
    protected void backToMain(View view) {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
