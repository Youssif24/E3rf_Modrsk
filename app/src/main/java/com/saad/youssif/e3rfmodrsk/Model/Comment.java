package com.saad.youssif.e3rfmodrsk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("std_name")
    @Expose
    private String stdName;
    @SerializedName("photo_path")
    @Expose
    private String photoPath;
    @SerializedName("cmt_details")
    @Expose
    private String cmtDetails;
    @SerializedName("cmt_date")
    @Expose
    private String cmtDate;

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCmtDetails() {
        return cmtDetails;
    }

    public void setCmtDetails(String cmtDetails) {
        this.cmtDetails = cmtDetails;
    }

    public String getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(String cmtDate) {
        this.cmtDate = cmtDate;
    }
}
