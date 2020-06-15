package com.saad.youssif.e3rfmodrsk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherProfileResult {

    @SerializedName("tch_result")
    @Expose
    private List<Teacher> tchResult = null;

    public List<Teacher> getTchResult() {
        return tchResult;
    }

    public void setTchResult(List<Teacher> tchResult) {
        this.tchResult = tchResult;
    }

}
