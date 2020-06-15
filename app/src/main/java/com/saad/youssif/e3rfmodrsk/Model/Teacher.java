package com.saad.youssif.e3rfmodrsk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teacher {
    @SerializedName("tch_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tch_phone")
    @Expose
    private String tchPhone;
    @SerializedName("tch_address")
    @Expose
    private String tchAddress;
    @SerializedName("tch_specification")
    @Expose
    private String tchSpecification;
    @SerializedName("tch_photo")
    @Expose
    private String tchPhoto;
    @SerializedName("tch_rate")
    @Expose
    private String tchRate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTchPhone() {
        return tchPhone;
    }

    public void setTchPhone(String tchPhone) {
        this.tchPhone = tchPhone;
    }

    public String getTchAddress() {
        return tchAddress;
    }

    public void setTchAddress(String tchAddress) {
        this.tchAddress = tchAddress;
    }

    public String getTchSpecification() {
        return tchSpecification;
    }

    public void setTchSpecification(String tchSpecification) {
        this.tchSpecification = tchSpecification;
    }

    public String getTchPhoto() {
        return tchPhoto;
    }

    public void setTchPhoto(String tchPhoto) {
        this.tchPhoto = tchPhoto;
    }

    public String getTchRate() {
        return tchRate;
    }

    public void setTchRate(String tchRate) {
        this.tchRate = tchRate;
    }


}
