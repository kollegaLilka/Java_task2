package task2.builder;

import task2.entity.*;

import java.time.LocalDate;

public class PoisonousFlowerBuilder {
    private PoisonousFlower flower;

    public PoisonousFlowerBuilder() {
        this.flower = new PoisonousFlower();
    }


    public PoisonousFlowerBuilder id(String id) {
        flower.setId(id);
        return this;
    }

    public PoisonousFlowerBuilder name(String name) {
        flower.setName(name);
        return this;
    }

    public PoisonousFlowerBuilder soilType(Soil soilType) {
        flower.setSoilType(soilType);
        return this;
    }

    public PoisonousFlowerBuilder origin(String origin) {
        flower.setOrigin(origin);
        return this;
    }

    public PoisonousFlowerBuilder visualDescription(VisualDescription visualDescription) {
        flower.setVisualDescription(visualDescription);
        return this;
    }

    public PoisonousFlowerBuilder growingTip(GrowingTip growingTip) {
        flower.setGrowingTip(growingTip);
        return this;
    }

    public PoisonousFlowerBuilder multiplyingType(MultiplyingType multiplyingType) {
        flower.setMultiplyingType(multiplyingType);
        return this;
    }

    public PoisonousFlowerBuilder date(LocalDate date) {
        flower.setDate(date);
        return this;
    }

    public PoisonousFlowerBuilder dangerLevel(DangerLevel dangerLevel) {
        flower.setDangerLevel(dangerLevel);
        return this;
    }

    public PoisonousFlower build() {
        return flower;
    }
}

