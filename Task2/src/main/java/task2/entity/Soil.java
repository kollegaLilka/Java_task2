package task2.entity;

import task2.exception.CustomException;

public enum Soil {
    HUMUS("humus"),
    PODZOLIC("podzolic"),
    SOD_PODZOLIC("sod-podzolic"),
    GROUND("ground");

    String value;

    Soil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Soil getSoilByValue(String value) throws CustomException {
        Soil[] values = Soil.values();
        for (Soil soil : values) {
            if (soil.value.equals(value)) {
                return soil;
            }
        }
        throw new CustomException("Illegal argument!");
    }
}
