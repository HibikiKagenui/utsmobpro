package com.muhammadnabillah1603631.utsmobpro.Model;

import com.google.gson.annotations.SerializedName;

public class Crew {
    @SerializedName("name")
    String name;
    @SerializedName("department")
    String department;
    @SerializedName("job")
    String job;

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getJob() {
        return job;
    }

}
