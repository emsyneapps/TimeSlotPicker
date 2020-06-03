package com.emsyne.timeslotpicker.model;

public class PrevScheduleModel {
    private String time;
    private String person_name;
    private String address;
    public PrevScheduleModel(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
