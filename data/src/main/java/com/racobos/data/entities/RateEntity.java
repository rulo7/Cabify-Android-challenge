package com.racobos.data.entities;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by rulo7 on 11/10/2016.
 */
@Data
public class RateEntity {
    @SerializedName("vehicle_type")
    @Expose
    private VehicleTypeEntity vehicleType;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("price_formatted")
    @Expose
    private String formattedPrice;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;

    public static RateEntity getMockObject() {
        String jsonOnject = "{\n" +
                "    \"vehicle_type\": {\n" +
                "      \"_id\": \"executive_id\",\n" +
                "      \"name\": \"Executive Class\",\n" +
                "      \"short_name\": \"Executive\",\n" +
                "      \"description\": \"A very large vehicle with comfortable seats\",\n" +
                "      \"icons\": {\n" +
                "        \"regular\": \"https://cabify.com/images/icons/vehicle_type/executive_27.png\"\n" +
                "      },\n" +
                "      \"reserved_only\": false,\n" +
                "      \"asap_only\": false,\n" +
                "      \"currency\": \"EUR\",\n" +
                "      \"icon\": \"executive\"\n" +
                "    },\n" +
                "    \"total_price\": 1234,\n" +
                "    \"formatted_price\": \"12.34€\",\n" +
                "    \"currency\": \"EUR\",\n" +
                "    \"currency_symbol\": \"€\",\n" +
                "    \"eta\": {\n" +
                "      \"min\": 100,\n" +
                "      \"max\": 1000,\n" +
                "      \"formatted\": \">2 min\"\n" +
                "    }\n" +
                "  }";

        return new Gson().fromJson(jsonOnject, RateEntity.class);
    }
}
