package task2.builder;

import  task2.entity.*;

import java.time.LocalDate;

public class FlowerBuilder {
    protected Flower flower;

    public FlowerBuilder() {
        flower = new Flower();
    }

    public FlowerBuilder id(String id) {
        flower.setId(id);
        return this;
    }

    public FlowerBuilder name(String name) {
        flower.setName(name);
        return this;
    }

    public FlowerBuilder soilType(Soil soilType) {
        flower.setSoilType(soilType);
        return this;
    }

    public FlowerBuilder origin(String origin) {
        flower.setOrigin(origin);
        return this;
    }

    public FlowerBuilder visualDescription(VisualDescription visualDescription) {
        flower.setVisualDescription(visualDescription);
        return this;
    }

    public FlowerBuilder growingTip(GrowingTip growingTip) {
        flower.setGrowingTip(growingTip);
        return this;
    }

    public FlowerBuilder multiplyingType(MultiplyingType multiplyingType) {
        flower.setMultiplyingType(multiplyingType);
        return this;
    }

    public FlowerBuilder date(LocalDate date) {
        flower.setDate(date);
        return this;
    }

    public Flower build() {
        return flower;
    }
}
