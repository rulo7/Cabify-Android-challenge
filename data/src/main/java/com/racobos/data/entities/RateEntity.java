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
    public VehicleTypeEntity vehicleType;
    @SerializedName("total_price")
    @Expose
    public Integer totalPrice;
    @SerializedName("formatted_price")
    @Expose
    public String formattedPrice;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("currency_symbol")
    @Expose
    public String currencySymbol;
    @SerializedName("eta")
    @Expose
    public Eta eta;

    public class Eta {
        @SerializedName("min")
        @Expose
        public Integer min;
        @SerializedName("max")
        @Expose
        public Integer max;
        @SerializedName("formatted")
        @Expose
        public String formatted;
    }
}
