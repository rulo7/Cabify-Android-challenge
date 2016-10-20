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
    private List<Double> loc;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("num")
    @Expose
    private String num;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
}
