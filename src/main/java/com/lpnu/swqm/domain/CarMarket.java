package com.lpnu.swqm.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CarMarket {

    private final List<SpareParts> spareParts;

    @Builder
    public CarMarket(List<SpareParts> spareParts) {
        this.spareParts = spareParts;
    }
}
