package com.emsyne.timeslotpicker;

public interface CommonInterface {

    default void onTimeGapSelected(String time1, String time2) {}

    default void onTimeSelected(String time) { }
}
