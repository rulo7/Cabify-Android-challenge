package com.racobos.domain.models;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by rulo7 on 09/10/2016.
 */
@Data
public class Vehicle implements Serializable {
    private String id;
    private String name;
    private String shortName;
    private String description;
    private String image;
    private Boolean reservedOnly;
    private Boolean asapOnly;
}
