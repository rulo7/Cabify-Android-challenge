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
    private Double totalPrice;
    @SerializedName("formatted_price")
    @Expose
    private String formattedPrice;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("currency_symbol")
    @Expose
    private String currencySymbol;
    @SerializedName("eta")
    @Expose
    private Eta eta;

    public void setEta(Integer min, Integer max, String formatted) {
        eta = new Eta();
        eta.setMin(min);
        eta.setMax(max);
        eta.setFormatted(formatted);
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
