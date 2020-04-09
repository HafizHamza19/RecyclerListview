package com.hafizhamza.myapplication.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hafizhamza.myapplication.Vehicle;
import com.hafizhamza.myapplication.R;
import com.hafizhamza.myapplication.adapter.ReleatedAdapter;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.about) TextView about;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.birth_of_date) TextView birth;
    @BindView(R.id.period) TextView term;
    @BindView(R.id.photo) ImageView photo;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.nestedListview)
    NestedScrollView nestedListview;
    ArrayList<Vehicle> items;

    private List<Vehicle> VehicleList;
    private ReleatedAdapter VehicleListAdapter;
    private LinearLayoutManager layoutManager;
    private Intent data;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        items = new ArrayList<Vehicle>();

        ButterKnife.bind(this);
        data = getIntent();

        title.setText("data.getStringExtra()");
        about.setText("getResources().getString(R.string.about)" + " " + "data.getStringExtra()");
        description.setText("data.getStringExtra(description)");
        birth.setText("data.getStringExtra(birth)");
        term.setText("data.getStringExtra(period) +  - NOW");

        Picasso.with(this).load(R.drawable.cultus).memoryPolicy(MemoryPolicy.NO_CACHE).into(photo);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        swipeContainer.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getVehicle();
            }
        });

        getVehicle();

        VehicleList = new ArrayList<>();
        VehicleListAdapter = new ReleatedAdapter(this, VehicleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setHorizontalScrollBarEnabled(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(VehicleListAdapter);

        nestedListview.smoothScrollTo(0,0);

    }


    private void getVehicle() {
        items.clear();
        items.add(new Vehicle(1,"Civic","","All Wheel Drive","sadsa","Luxury/SUV","sad","sad","212"));
        items.add(new Vehicle(1,"Corolla","","All Wheel Drive","sadsa","Luxury/SUV","x","sad","212"));
        items.add(new Vehicle(1,"Cultus","","All Wheel Drive","sadsa","Luxury/SUV","xx","sad","212"));
        items.add(new Vehicle(1,"Swift","","All Wheel Drive","sadsa","Standard","xxx","sad","212"));
        items.add(new Vehicle(1,"Audi","","All Wheel Drive","sadsa","Standard","xxxx","sad","212"));
        items.add(new Vehicle(1,"Mehran","","All Wheel Drive","sadsa","Economy","xxxxx","sad","212"));

//        presidentList.clear();
        //  presidentList.addAll(new President(1,"asd","sad","sad","sadsa","asd","sad","sad","212"));
        //presidentListAdapter.notifyDataSetChanged();

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
    protected void dialogVote(String name) {
        final Dialog dialog = new Dialog(DetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_vote);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        final TextView num = (TextView) dialog.findViewById(R.id.num);

        Button plus = (Button) dialog.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int n = Integer.parseInt(num.getText().toString());
                if(n < 5) {
                    n+=1;
                    num.setText("" + String.valueOf(n));
                }
            }
        });

        Button minus = (Button) dialog.findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int n = Integer.parseInt(num.getText().toString());
                if(n > 1) {
                    n-=1;
                    num.setText("" + String.valueOf(n));
                }
            }
        });


        TextView title = (TextView) dialog.findViewById(R.id.title);
        title.setText(name);

        dialog.setTitle("");
        dialog.show();
    }

    @OnClick(R.id.buttonDecision)
    protected void decision(View view) {
        dialogVote(getResources().getString(R.string.decision));
    }

    @OnClick(R.id.buttonFirmness)
    protected void firmness(View view) {
        dialogVote(getResources().getString(R.string.firmness));
    }

    @OnClick(R.id.buttonLeadership)
    protected void leadership(View view) {
        dialogVote(getResources().getString(R.string.leadership));
    }

    @OnClick(R.id.buttonLeft)
    protected void backToMain(View view) {
        super.onBackPressed();
        Intent intent;

        if(data.getStringExtra("in").equals("gr")){
        //     intent = new Intent(DetailActivity.this, GridActivity.class);
        }else{
            intent = new Intent(DetailActivity.this, ListActivity.class);
        }
        intent = new Intent(DetailActivity.this, ListActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
