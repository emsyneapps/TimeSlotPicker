package com.emsyne.timeslotpicker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.emsyne.timeslotpicker.CommonInterface;
import com.emsyne.timeslotpicker.R;
import com.emsyne.timeslotpicker.adapter.PrevSchedulesAdapter;
import com.emsyne.timeslotpicker.adapter.TimeGapAdapter;
import com.emsyne.timeslotpicker.model.PrevScheduleModel;
import com.emsyne.timeslotpicker.model.TimeGapModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeSlotPickerActivity extends AppCompatActivity implements CommonInterface {


    TextView tvDateSet;

    RecyclerView recyclerviewTimeGap;

    RecyclerView recyclerviewPrevSchedules;

    private ArrayList<String> timeList = new ArrayList<>();
    private ArrayList<TimeGapModel> timeGapList = new ArrayList<>();
    ArrayList<PrevScheduleModel> prevScheduleList = new ArrayList<>();

    private String selectedDate;
    private String selectedTime = "00:00";
    private int position = -1;
    private String date_string;
    private ArrayList<String> prev_schedules;
    private String today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_schedules);
//        ButterKnife.bind(this);
        tvDateSet = findViewById(R.id.tv_date_set);

        recyclerviewTimeGap = findViewById(R.id.recyclerview_time_gap);

        recyclerviewPrevSchedules = findViewById(R.id.recyclerview_prev_schedules);

        if (getIntent().getExtras() != null) {
            date_string = getIntent().getExtras().getString("DATE_SELECTED");
            prev_schedules = getIntent().getStringArrayListExtra("PREV_SCHEDULES");
        }

        setDateTextView(date_string);
        setTimeGap();
    }

    private void setDateTextView(String date_string) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());//format of date obtained from datepicker.
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMM yyyy , EEEE", Locale.getDefault());//format to which, date has to be converted.
        selectedDate = date_string;
        Date selected_date;
        Calendar calendar = Calendar.getInstance();
        today = sdf.format(calendar.getTime());

        try {
            selected_date = sdf.parse(date_string);
            if (selected_date != null) {
                calendar.setTime(selected_date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String calendar_time = sdf2.format(calendar.getTime());//convert date to string
        tvDateSet.setText(calendar_time);
    }

    @Override
    public void onTimeSelected(String time) {
        String dateTime = date_string + "  " + time;
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("TIME_SELECTED", dateTime);
//        startActivity(intent);
//        finish();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("TIME_SELECTED",dateTime);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onTimeGapSelected(String time, String time1) {
        selectedTime = time;
        getTimeList();
        getPrevSchedules();
    }

    private void getPrevSchedules() {
        prevScheduleList = new ArrayList<>();

        PrevScheduleModel t1 = new PrevScheduleModel();
        t1.setPerson_name("Santhosh Kumar");
        t1.setTime("14:00");
        t1.setAddress("Plakkal house, opposite federal bank, karayamparambu, Angamaly- 683572.");
        prevScheduleList.add(t1);

        PrevScheduleModel t2 = new PrevScheduleModel();
        t2.setPerson_name("Raj Kumar");
        t2.setTime("14:10");
        t2.setAddress("Puthenpurackal House, 4/56, Subash Nagar, Near Changapuzha park, Edapally -785875.");
        prevScheduleList.add(t2);

        PrevScheduleModel t3 = new PrevScheduleModel();
        t3.setPerson_name("Reena Mathew");
        t3.setTime("14:20");
        t3.setAddress(" House 4/56,near A J John School,Thalayolaparambu,Kottayam-665667");
        prevScheduleList.add(t3);

        PrevScheduleModel t4 = new PrevScheduleModel();
        t4.setPerson_name("Saourav Kosh");
        t4.setTime("14:30");
        t4.setAddress("Krishna House, 4/56, Subash Nagar, Near Changapuzha park, Edapally -785875.");
        prevScheduleList.add(t4);


        PrevScheduleModel t5 = new PrevScheduleModel();
        t5.setPerson_name("Jamuna Mishra");
        t5.setTime("11:30");
        t5.setAddress("2025 Federal tower, Subash Nagar, Near Changapuzha park, Edapally -785875.");
        prevScheduleList.add(t5);

//        PrevSchedulesAdapter prevSchedulesAdapter = new PrevSchedulesAdapter(prevScheduleList, timeList, this);
        PrevSchedulesAdapter prevSchedulesAdapter = new PrevSchedulesAdapter(prev_schedules, timeList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerviewPrevSchedules.setLayoutManager(mLayoutManager);
        recyclerviewPrevSchedules.setItemAnimator(new DefaultItemAnimator());
        recyclerviewPrevSchedules.setAdapter(prevSchedulesAdapter);
    }

    private void setTimeGap() {
        timeGapList = new ArrayList<>();

        TimeGapModel timeGap = new TimeGapModel();
        timeGap.setTime1("00:00");
        timeGap.setTime2("03:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("03:00");
        timeGap.setTime2("06:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("06:00");
        timeGap.setTime2("09:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("09:00");
        timeGap.setTime2("12:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("12:00");
        timeGap.setTime2("15:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("15:00");
        timeGap.setTime2("18:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("18:00");
        timeGap.setTime2("21:00");
        timeGapList.add(timeGap);

        timeGap = new TimeGapModel();
        timeGap.setTime1("21:00");
        timeGap.setTime2("24:00");
        timeGapList.add(timeGap);

        modifiedTimeGap();
    }

    private void modifiedTimeGap() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar calendar1 = Calendar.getInstance();//for current time
        Calendar calendar2 = Calendar.getInstance();//for time t1 in timegap
        Calendar calendar3 = Calendar.getInstance();//for time t2 in timegap

        String current_time = sdf.format(calendar1.getTime());
        Date d1;
        try {
            //current time
            d1 = sdf.parse(current_time);
            if (d1 != null) {
                calendar1.setTime(d1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date_string.equals(today)) {
            position = 1;
            //if date selected is today, disable the timegap, with time less than the current time.
            for (int i = 0; i < 8; i++) {
                timeGapList.get(i).setPosition(i);//set position for each timegap
                String min = timeGapList.get(i).getTime1();//time1 of timegap
                Date d2;
                try {
                    d2 = sdf.parse(min);
                    if (d2 != null) {
                        calendar2.setTime(d2);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String max = timeGapList.get(i).getTime2();//time2 of timegap
                Date d3;
                try {
                    d3 = sdf.parse(max);
                    if (d3 != null) {
                        calendar3.setTime(d3);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date x = calendar1.getTime();//current time
                if (x.after(calendar2.getTime()) && x.before(calendar3.getTime())) {
                    //current time is in between the time gap
                    timeGapList.get(i).setEnabled(true);
                    position = timeGapList.get(i).getPosition();//position of the time gap with current time
                    selectedTime = timeGapList.get(i).getTime1();//start time of the time gap with current time
                } else if (x.before(calendar2.getTime()) && x.before(calendar3.getTime())) {
                    //current time after the time gap(time1 & time2)
                    timeGapList.get(i).setEnabled(true);
                } else {
                    //if current time before the time gap(t1 & t2),disable the timegap
                    timeGapList.get(i).setEnabled(false);
                }
            }
        } else {
            //if date selected is not today,enable all time cards
            //default time selected will be 09:00 AM
            for (int i = 0; i < 8; i++) {
                timeGapList.get(i).setEnabled(true);
            }
            position = 1;
            selectedTime = "09:00";
        }

        TimeGapAdapter timeGapAdapter = new TimeGapAdapter(timeGapList, this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerviewTimeGap.setLayoutManager(mLayoutManager2);
        recyclerviewTimeGap.setItemAnimator(new DefaultItemAnimator());
        recyclerviewTimeGap.smoothScrollToPosition(position);//focus on timegap with current time
        recyclerviewTimeGap.setAdapter(timeGapAdapter);
        getTimeList();
    }

    private void getTimeList() {
        timeList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        //get current time
        Calendar now = Calendar.getInstance();
        Date d1;
        String current_time = sdf.format(now.getTime());
        try {
            d1 = sdf.parse(current_time);
            if (d1 != null) {
                now.setTime(d1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //get selected time
        Date selected_date;
        Calendar calendar = Calendar.getInstance();
        try {
            selected_date = sdf.parse(selectedTime);//time1 from timegap
            if (selected_date != null) {
                calendar.setTime(selected_date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String calendar_time = sdf.format(calendar.getTime());//convert date to string
        Date x = calendar.getTime();//selected time

        //time1 of timegap
        if (date_string.equals(today)) {
            //if date selected is today,add the time after the current time to the timelist
            if (x.after(now.getTime())) {
                timeList.add(calendar_time);
            }
        } else {
            timeList.add(calendar_time);
        }

        //10 minutes added to the time1 of timegap
        for (int i = 0; i < 18; i++) {
            calendar.add(Calendar.MINUTE, 10);
            calendar_time = sdf.format(calendar.getTime());
            x = calendar.getTime();

            if (date_string.equals(today)) {
                //if date selected is today,add the time after the current time to the timelist
                if (x.after(now.getTime())) {
                    timeList.add(calendar_time);
                }
            } else {
                timeList.add(calendar_time);//add all time if date selected is not today.
            }
        }
    }

}
