package task2.entity;

import task2.exception.CustomException;

public enum DangerLevel {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private String value;

    DangerLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DangerLevel getDangerLevelByValue(String value) throws CustomException {
        DangerLevel[] values = DangerLevel.values();
        for (DangerLevel dangerLevel : values) {
            if (dangerLevel.value.equals(value)) {
                return dangerLevel;
            }
        }
        throw new CustomException("Illegal argument!");
    }
}