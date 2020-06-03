package com.emsyne.timeslotpicker.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;

import com.emsyne.timeslotpicker.activity.TimeSlotPickerActivity;

import java.util.ArrayList;

public class SlotPicker {

    public static void pickTime(Activity mainActivity, String dateSelected, ArrayList<String> scheduledtimes, int REQ_CODE){

        Intent intent = new Intent(mainActivity, TimeSlotPickerActivity.class);
        intent.putExtra("DATE_SELECTED", dateSelected);
        intent.putStringArrayListExtra("PREV_SCHEDULES", scheduledtimes);
        mainActivity.startActivityForResult(intent, REQ_CODE);

    }


}
