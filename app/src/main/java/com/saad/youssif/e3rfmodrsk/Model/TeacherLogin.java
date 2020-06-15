package com.saad.youssif.e3rfmodrsk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherLogin {
    @SerializedName("result")
    @Expose
    private List<Teacher> teacherList = null;

    public List<Teacher> getTeacherResult() {
        return teacherList;
    }

    public void setTeacherResult(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}
