package com.challenge.spo.workforcecalculator.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contract {
    @JsonProperty(value = "rooms", required = true)
    private final int[] rooms;

    @JsonProperty(value = "senior", required = true)
    private final int seniorCapacity;

    @JsonProperty(value = "junior", required = true)
    private final int juniorCapacity;
}
