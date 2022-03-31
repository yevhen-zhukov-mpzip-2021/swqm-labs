package com.lpnu.swqm.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Abs implements SpareParts {

    private final String serialNumber;
    private final Type type;

    @Builder
    public Abs(String serialNumber, Type type) {
        this.serialNumber = serialNumber;
        this.type = type;
    }

    public enum Type {
        FOUR_CHANNEL_SIGNAL,
        THREE_CHANNEL_SIGNAL,
        ONE_CHANNEL_SIGNAL
    }
}
