package com.carersystem.carer.Model;

public class Modalcarerview {
    String carers_id;
    String carer_name;
    String carer_email;
    String carer_phone;
    String start_date;
    String start_time;
    String end_time;

    public Modalcarerview() {
        this.carers_id = carers_id;
        this.carer_name = carer_name;
        this.carer_email= carer_email;
        this.carer_phone = carer_phone;
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getCarers_id() {
        return carers_id;
    }

    public void setCarers_id(String carers_id) {
        this.carers_id = carers_id;
    }

    public String getCarer_name() {
        return carer_name;
    }

    public void setCarer_name(String carer_name) {
        this.carer_name = carer_name;
    }


    public String getCarer_email() {
        return carer_email;
    }

    public void setCarer_email(String carer_email) {
        this.carer_email = carer_email;
    }


    public String getCarer_phone() {
        return carer_phone;
    }

    public void setCarer_phone(String carer_phone) {
        this.carer_phone = carer_phone;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
