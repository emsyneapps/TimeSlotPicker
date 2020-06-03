package com.example.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;


import com.emsyne.timeslotpicker.activity.TimeSlotPickerActivity;
import com.emsyne.timeslotpicker.utils.SlotPicker;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_select_date)
    Button btnSelectDate;
    @BindView(R.id.tv_date_time_selected)
    TextView tvDateTimeSelected;
    ArrayList<String> scheduledtimes= new ArrayList<String>();
    String dateSelected;
    private DatePickerDialog dpDialog;
    int LAUNCH_SECOND_ACTIVITY = 1;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ctx = getApplicationContext();
//        scheduledtimes.add("16:50");
//        scheduledtimes.add("16:20");
//        scheduledtimes.add("15:50");

        if (getIntent().getExtras() != null) {
            String date_time = getIntent().getExtras().getString("TIME_SELECTED");
            if (date_time != null) {
                tvDateTimeSelected.setText(date_time);
            }
        }




        datePicker();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
//                String result=data.getStringExtra("result");
                String date_time = data.getStringExtra("TIME_SELECTED");
                if (date_time != null) {
                    tvDateTimeSelected.setText(date_time);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private void datePicker() {

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateSelected = DateUtils.getInstance().getFormattedStringDate(year, month + 1, day);

                SlotPicker.pickTime(MainActivity.this, dateSelected,scheduledtimes,LAUNCH_SECOND_ACTIVITY);
            }
        };
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (dpDialog == null) {
                    dpDialog = new DatePickerDialog(MainActivity.this, R.style.DatePickerTheme, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    dpDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    dpDialog.getDatePicker().setEnabled(false);
                    final Calendar calendarMax = Calendar.getInstance();
                    int totalDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
                    String day = (String) DateFormat.format("dd", new Date());
                    Log.d("currentDay", day);
                    calendarMax.add(Calendar.DAY_OF_MONTH, totalDays - Integer.valueOf(day));
//                    dpDialog.getDatePicker().setMaxDate(calendarMax.getTimeInMillis());
                }
                if (!dpDialog.isShowing()) {
                    dpDialog.show();
                }
            }
        });
    }

}
