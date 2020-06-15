package com.saad.youssif.e3rfmodrsk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentLogin {
    @SerializedName("result")
    @Expose
    private List<Student> studentList = null;

    public List<Student> getStudent() {
        return studentList;
    }

    public void setStudent(List<Student> studentList) {
        this.studentList = studentList;
    }
}
