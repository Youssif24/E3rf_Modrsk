package com.saad.youssif.e3rfmodrsk.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeachersListResult {

    @SerializedName("tch_list_result")
    @Expose
    private List<Teacher> tchListResult = null;

    public List<Teacher> getTchListResult() {
        return tchListResult;
    }

    public void setTchListResult(List<Teacher> tchListResult) {
        this.tchListResult = tchListResult;
    }

}
