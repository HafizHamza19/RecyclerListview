package com.hafizhamza.myapplication.adapter;

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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<Vehicle> VehicleList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryName, presidentName, birthDate, rate;
        public CircularImageView photo, flag;
        public RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            layout = (RelativeLayout) view.findViewById(R.id.layout);
            countryName = (TextView) view.findViewById(R.id.country_name);
            presidentName = (TextView) view.findViewById(R.id.president_name);
            birthDate = (TextView) view.findViewById(R.id.birth_of_date);
            rate = (TextView) view.findViewById(R.id.rate);
            photo = (CircularImageView) view.findViewById(R.id.president);
           // flag = (CircularImageView) view.findViewById(R.id.country);

            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                loadIntet(getAdapterPosition());
                }
            });

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

        Vehicle president = VehicleList.get(position);
        intent.putExtra("id", president.getId());
        intent.putExtra("in", "ls");
        intent.putExtra("name", president.getName());
        intent.putExtra("country", president.getCountry());
        intent.putExtra("birth", changetToBirth(president.getBirthOfDate()));
        intent.putExtra("description", president.getDescription());
        intent.putExtra("period", changetToBirth(president.getPeriod()));
        intent.putExtra("photo", president.getPhoto());

        context.startActivity(intent);
    }

    public ListAdapter(Context context, List<Vehicle> VehicleList) {
        this.context = context;
        this.VehicleList = VehicleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Vehicle vehicle = VehicleList.get(position);
        holder.presidentName.setText(vehicle.getName());
        holder.countryName.setText(context.getResources().getString(R.string.car_name) + " " + vehicle.getCountry());
        holder.birthDate.setText(changetToBirth(vehicle.getBirthOfDate()));

        Picasso.with(context).load(R.drawable.cultus).into(holder.photo);
        //Picasso.with(context).load(App.URL + "files/flags/" + president.getPhoto()).into(holder.flag);

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
