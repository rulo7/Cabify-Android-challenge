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
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icons")
    @Expose
    private Icons icons;
    @SerializedName("reserved_only")
    @Expose
    private Boolean reservedOnly;
    @SerializedName("asap_only")
    @Expose
    private Boolean asapOnly;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("icon")
    @Expose
    private String icon;

    @Data
    public class Icons {
        @SerializedName("regular")
        @Expose
        private String regular;
    }
}
