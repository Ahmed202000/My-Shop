
package com.hegazy.myshop.data.model.settingModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("terms")
    @Expose
    private String terms;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

}
