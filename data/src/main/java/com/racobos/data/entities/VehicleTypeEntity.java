package com.racobos.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by rulo7 on 11/10/2016.
 */
@Data
public class VehicleTypeEntity {
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("short_name")
    @Expose
    public String shortName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icons")
    @Expose
    public Icons icons;
    @SerializedName("reserved_only")
    @Expose
    public Boolean reservedOnly;
    @SerializedName("asap_only")
    @Expose
    public Boolean asapOnly;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("icon")
    @Expose
    public String icon;

    public class Icons {
        @SerializedName("regular")
        @Expose
        public String regular;
    }
}
