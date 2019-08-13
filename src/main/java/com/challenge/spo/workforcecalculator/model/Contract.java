package com.challenge.spo.workforcecalculator.model;


import lombok.Data;

@Data
public class Contract {
    private final int[] rooms;
    private final int seniorCapacity;
    private final int juniorCapacity;
}
