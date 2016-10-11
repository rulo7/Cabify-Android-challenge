package com.racobos.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * Created by raulcobos on 11/10/16.
 */
@Data
public class StopEntity {
    @SerializedName("loc")
    @Expose
    public List<Double> loc;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("addr")
    @Expose
    public String addr;
    @SerializedName("num")
    @Expose
    public String num;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("country")
    @Expose
    public String country;
}
