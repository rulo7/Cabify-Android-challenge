package com.racobos.data.errors;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by rulo7 on 15/10/2016.
 */

@Data
public class CabifyErrorEntity {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private Errors errors;

    public static CabifyErrorEntity getMockObject() {
        String json = "{\n" +
                "  \"message\": \"You provided incomplete information\",\n" +
                "  \"errors\": {\n" +
                "    \"some_attribute\": [\n" +
                "      \"Hello, world!\",\n" +
                "      \"is too small\",\n" +
                "      \"needs to be odd\"\n" +
                "    ],\n" +
                "    \"some_other\": [\n" +
                "      \"Hello, world!\",\n" +
                "      \"is required\"\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        return new Gson().fromJson(json, CabifyErrorEntity.class);
    }

    public class Errors {
        @SerializedName("some_attribute")
        @Expose
        private List<String> someAttribute = new ArrayList<>();
        @SerializedName("some_other")
        @Expose
        private List<String> someOther = new ArrayList<>();
    }
}
