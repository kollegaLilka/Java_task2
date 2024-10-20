package task2.parser;

public enum FlowerXmlTag {
    FLOWERS("flowers"),

    ID("id"),

    DANGER_LEVEL("danger_level"),

    FLOWER("flower"),

    POISONOUS_FLOWER("poisonous_flower"),

    NAME("name"),

    ORIGIN("origin"),

    SOIL("soil"),

    VISUAL_PARAMETERS("visual_parameters"),

    PETALS_COLOUR("petals_colour"),

    STEM_COLOUR("stem_colour"),

    AVERAGE_SIZE("average_size"),

    GROWING_TIPS("growing_tips"),

    MULTIPLYING("multiplying"),

    RECEIPT_DATE("receipt_date"),

    WEEKLY_WATERING("weekly_watering"),

    GROWTH_TEMPERATURE("growth_temperature"),

    IS_PHOTOPHILOUS("is_photophilous");

    private String value;

    FlowerXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }




}
