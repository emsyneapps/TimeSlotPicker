package com.emsyne.timeslotpicker.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.emsyne.timeslotpicker.CommonInterface;
import com.emsyne.timeslotpicker.R;
import com.emsyne.timeslotpicker.activity.TimeSlotPickerActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PrevSchedulesAdapter extends RecyclerView.Adapter<PrevSchedulesAdapter.MyViewHolder> {

    private ArrayList<String> timeList;
    private CommonInterface commonInterface;
    private ArrayList<String> scheduleList;

    public PrevSchedulesAdapter(ArrayList<String> scheduleList, ArrayList<String> timeList, TimeSlotPickerActivity commonInterface) {
        this.scheduleList = scheduleList;
        this.timeList = timeList;
        this.commonInterface = commonInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_timeline, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (scheduleList != null) {
            for (int i = 0; i < scheduleList.size(); i++) {

                Log.e("scheduleList",scheduleList.get(i));
                Log.e("timeList",timeList.get(position));
                if (timeList.get(position).equals(scheduleList.get(i))) {
                    holder.llColorLayout.setBackgroundColor(Color.parseColor("#9e9d9d"));
                    holder.tvPickTime.setText("Not Available");
                    holder.tvPickTime.setTextColor(Color.parseColor("#9e9d9d"));
                    holder.imgRound.setImageResource(R.drawable.circle_filled_grey);
                    holder.cardview.setClickable(false);
                    holder.cardview.setEnabled(false);
                    break;
                } else {
                    holder.llColorLayout.setBackgroundColor(Color.parseColor("#30cc72"));
                    holder.tvPickTime.setText("Pick this time");
                    holder.tvPickTime.setTextColor(Color.parseColor("#30cc72"));
                    holder.imgRound.setImageResource(R.drawable.circle_filled_green);
                    holder.cardview.setClickable(true);
                    holder.cardview.setEnabled(true);
                }
            }
        }
        holder.tvTime.setText(timeIn12hrFormat(timeList.get(position)));
        holder.cardview.setOnClickListener(view -> commonInterface.onTimeSelected(holder.tvTime.getText().toString()));
    }

    private static String timeIn12hrFormat(String time) {
        SimpleDateFormat sdf_24_hr = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat sdf_12_hr = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        Date date = null;
        try {
            date = sdf_24_hr.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            return sdf_12_hr.format(date);
        } else {
            return "";
        }
    }


    @Override
    public int getItemCount() {
        if (timeList != null) {
            return timeList.size();
        } else
            return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llColorLayout;

        TextView tvTime;

        ImageView imgRound;

        CardView cardview;

        TextView tvPickTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPickTime = (TextView) itemView.findViewById(R.id.tv_pick_time);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            cardview = (CardView) itemView.findViewById(R.id.schedule_cardview);
            imgRound = (ImageView) itemView.findViewById(R.id.img_round);
            llColorLayout = (LinearLayout) itemView.findViewById(R.id.ll_color_layout);

        }
    }
}