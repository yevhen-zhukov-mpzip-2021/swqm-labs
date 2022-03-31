package com.lpnu.swqm.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Engine implements SpareParts {

    private final String serialNumber;
    private final float capacity;

    @Builder
    public Engine(String serialNumber, float capacity) {
        this.serialNumber = serialNumber;
        this.capacity = capacity;
    }
}
