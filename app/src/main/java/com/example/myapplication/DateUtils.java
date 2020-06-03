package com.example.myapplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static DateUtils instance;

    public static DateUtils getInstance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }

    // convert date to string format
    public String dateToString(Date dob) {
        Calendar c = Calendar.getInstance();
        c.setTime(dob);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);

        return getFormattedStringDate(year, month, day);

    }


    // convert string to date format
    public Date stringToDate(String s) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return df.parse(s);
    }


    // return formatted date as string
    public String getFormattedStringDate(int year, int month, int day) {

        String d, m;
        d = day + "";
        m = month + "";
        if (day < 10) {
            d = "0" + day + "";

        }

        if (month < 10) {
            m = "0" + month + "";
        }
        return d + "/" + m + "/" + year;
    }

    // return formatted date as string
    public String getFormattedStringDateNew(int year, int month, int day) {

        String d, m;
        d = day + "";
        m = month + "";
        if (day < 10) {
            d = "0" + day + "";

        }

        if (month < 10) {
            m = "0" + month + "";
        }
        return year + "-" + m + "-" + d;
    }
}
