package com.lpnu.swqm.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Wheels implements SpareParts {

    private final String serialNumber;
    private final Type type;

    @Builder
    public Wheels(String serialNumber, Type type) {
        this.serialNumber = serialNumber;
        this.type = type;
    }

    public enum Type {
        STEEL, ALLOY, MULTI_PIECE, CHROME, DIAMOND, FORGED, REPLICA_OEM
    }
}
