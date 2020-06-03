package com.emsyne.timeslotpicker.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.emsyne.timeslotpicker.CommonInterface;
import com.emsyne.timeslotpicker.R;
import com.emsyne.timeslotpicker.activity.TimeSlotPickerActivity;
import com.emsyne.timeslotpicker.model.TimeGapModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TimeGapAdapter extends RecyclerView.Adapter<TimeGapAdapter.MyViewHolder> {

    private ArrayList<TimeGapModel> timeList;
    private CommonInterface commonInterface;
    private int selectedPosition = -1;
    private boolean currentSlot = false;

    public TimeGapAdapter(ArrayList<TimeGapModel> timeList, TimeSlotPickerActivity commonInterface) {
        this.timeList = timeList;
        this.commonInterface = commonInterface;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_timegap, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.time1.setText(timeIn12hrFormat(timeList.get(position).getTime1()));
        holder.time2.setText(timeIn12hrFormat(timeList.get(position).getTime2()));

        if (selectedPosition == position) {
            holder.timeLayout.setBackgroundColor(Color.parseColor("#e5fcee"));
        } else {
            holder.timeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        if (timeList.get(position).isEnabled()) {
            if (!currentSlot) {
                currentSlot = true;
                String currentTime1 = timeList.get(position).getTime1();
                String currentTime2 = timeList.get(position).getTime2();
                commonInterface.onTimeGapSelected(currentTime1, currentTime2);
                holder.timeLayout.setBackgroundColor(Color.parseColor("#e5fcee"));
            }
            holder.layoutBg.setBackgroundColor(Color.parseColor("#30cc72"));
            holder.time1.setTextColor(Color.parseColor("#30cc72"));
            holder.time2.setTextColor(Color.parseColor("#30cc72"));
            holder.viewLine.setBackgroundColor(Color.parseColor("#30cc72"));
            holder.card.setClickable(true);
            holder.card.setEnabled(true);
        } else {
            holder.layoutBg.setBackgroundColor(Color.parseColor("#9e9d9d"));
            holder.time1.setTextColor(Color.parseColor("#9e9d9d"));
            holder.time2.setTextColor(Color.parseColor("#9e9d9d"));
            holder.viewLine.setBackgroundColor(Color.parseColor("#9e9d9d"));
            holder.card.setClickable(false);
            holder.card.setEnabled(false);
        }

        holder.card.setOnClickListener(view -> {
            selectedPosition = position;
            notifyDataSetChanged();
            commonInterface.onTimeGapSelected(timeList.get(position).getTime1(), timeList.get(position).getTime2());
        });
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

//        @BindView(R.id.time1)
//        TextView time1 = TextView.findViewById(R.id.time1);;
//        @BindView(R.id.time2)
//        TextView time2;
//        @BindView(R.id.card_timegap)
//        CardView card;
//        @BindView(R.id.view_line)
//        View viewLine;
//        @BindView(R.id.layout_bg)
//        LinearLayout layoutBg;
//        @BindView(R.id.ll_time_layout)
//        LinearLayout timeLayout;
//
//
//
//        MyViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//        }


        TextView time1,time2;
        CardView card;
        View viewLine;
        LinearLayout layoutBg;
        LinearLayout timeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            time1 = (TextView) itemView.findViewById(R.id.time1);
            time2 = (TextView) itemView.findViewById(R.id.time2);
            card = (CardView) itemView.findViewById(R.id.card_timegap);
            viewLine = (View) itemView.findViewById(R.id.view_line);
            layoutBg = (LinearLayout) itemView.findViewById(R.id.layout_bg);
            timeLayout = (LinearLayout) itemView.findViewById(R.id.ll_time_layout);
        }
    }
}