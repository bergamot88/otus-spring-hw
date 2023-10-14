package com.tokmakov.fuzzing_pantry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Statuses {
    NEW ("new", 172800000),
    AVERAGE ("average", 432000000),
    OLD ("old", 864000000);

    private final String value;
    private final long days;
}
