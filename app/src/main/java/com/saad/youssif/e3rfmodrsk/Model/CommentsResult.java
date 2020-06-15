package com.saad.youssif.e3rfmodrsk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsResult {
    @SerializedName("tch_result")
    @Expose
    private List<Comment> cmtResult = null;

    public List<Comment> getTchResult() {
        return cmtResult;
    }

    public void setTchResult(List<Comment> tchResult) {
        this.cmtResult = tchResult;
    }
}
