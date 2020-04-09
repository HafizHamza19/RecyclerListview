package com.hafizhamza.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hafizhamza.myapplication.Vehicle;
import com.hafizhamza.myapplication.R;
import com.hafizhamza.myapplication.activity.DetailActivity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * CopyRight by HafizHamza on 4/9/2020.
 */

public class ReleatedAdapter extends RecyclerView.Adapter<ReleatedAdapter.ViewHolder> {

    private Context context;
    private List<Vehicle> VehicleList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName, presidentName;
        public CircularImageView photo;
        public RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);

            layout = (RelativeLayout) view.findViewById(R.id.layout);
            countryName = (TextView) view.findViewById(R.id.country_name);
            presidentName = (TextView) view.findViewById(R.id.president_name);
            photo = (CircularImageView) view.findViewById(R.id.president);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    loadIntet(getAdapterPosition());

                }
            });
        }

    }

    private void loadIntet(int position) {
        Intent intent;
        intent = new Intent(context, DetailActivity.class);
        Intent data = ((Activity) context).getIntent();
        Vehicle vehicle = VehicleList.get(position);
        intent.putExtra("id", vehicle.getId());
        intent.putExtra("in", data.getStringExtra("in"));
        intent.putExtra("name", vehicle.getName());
        intent.putExtra("country", vehicle.getCountry());
        intent.putExtra("birth", changetToBirth(vehicle.getBirthOfDate()));
        intent.putExtra("description",vehicle.getDescription());
        intent.putExtra("period", changetToBirth(vehicle.getPeriod()));
        intent.putExtra("photo", vehicle.getPhoto());
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }

    public ReleatedAdapter(Context context, List<Vehicle> VehicleList) {
        this.context = context;
        this.VehicleList = VehicleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.releated_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Vehicle vehicle = VehicleList.get(position);
        holder.presidentName.setText(vehicle.getName());
        holder.countryName.setText(context.getResources().getString(R.string.car_name) + " " + vehicle.getCountry());
        Picasso.with(context).load(R.drawable.cultus).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return VehicleList.size();
    }

    public void clear() {
        VehicleList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Vehicle> list) {
        VehicleList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(Vehicle president) {
        VehicleList.add(president);
        notifyDataSetChanged();
    }

    protected String changetToBirth(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatterNew = new SimpleDateFormat("MMMM d, yyyy", Locale.US);

        try {
            Date date = formatter.parse(s);
            return formatterNew.format(date);

        } catch (ParseException e) {
            e.printStackTrace();

            return s;
        }
    }


}
