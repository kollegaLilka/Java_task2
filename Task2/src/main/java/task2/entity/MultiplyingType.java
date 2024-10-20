package task2.entity;

import task2.exception.CustomException;

public enum MultiplyingType {
    STALK("stalk"),
    SEED("seed"),
    LEAF("leaf");

    private String value;

    MultiplyingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MultiplyingType getMultiplyingTypeByValue(String value) throws CustomException {
        MultiplyingType[] values = MultiplyingType.values();
        for (MultiplyingType multiplyingType : values) {
            if (multiplyingType.value.equals(value)) {
                return multiplyingType;
            }
        }
        throw new CustomException("Illegal argument!");
    }
}
