package com.racobos.data.entities;

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
}
