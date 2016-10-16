package com.racobos.data.entities;

import com.google.gson.Gson;
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
    @SerializedName("eta")
    @Expose
    private Eta eta;

    public static VehicleTypeEntity getMockObject() {
        String jsonOnject = "{\n" +
                "  \"_id\": \"executive_id\",\n" +
                "  \"name\": \"Executive Class\",\n" +
                "  \"short_name\": \"Executive\",\n" +
                "  \"description\": \"A very large vehicle with comfortable seats\",\n" +
                "  \"icons\": {\n" +
                "    \"regular\": \"https://cabify.com/images/icons/vehicle_type/executive_27.png\"\n" +
                "  },\n" +
                "  \"reserved_only\": false,\n" +
                "  \"asap_only\": false,\n" +
                "  \"currency\": \"EUR\",\n" +
                "  \"icon\": \"executive\",\n" +
                "  \"eta\": {\n" +
                "        \"min\": 100,\n" +
                "        \"max\": 1000,\n" +
                "        \"formatted\": \">2 min\"\n" +
                "      }\n" +
                "}";

        return new Gson().fromJson(jsonOnject, VehicleTypeEntity.class);
    }

    public void setEta(Integer min, Integer max, String formatted) {
        eta = new Eta();
        eta.setMin(min);
        eta.setMax(max);
        eta.setFormatted(formatted);
    }

    @Data
    public class Icons {
        @SerializedName("regular")
        @Expose
        private String regular;
    }

    @Data
    public class Eta {
        @SerializedName("min")
        @Expose
        private Integer min;
        @SerializedName("max")
        @Expose
        private Integer max;
        @SerializedName("formatted")
        @Expose
        private String formatted;
    }
}
