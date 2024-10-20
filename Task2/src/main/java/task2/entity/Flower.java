package task2.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Flower {
    protected String id;
    protected String name;
    protected Soil soilType;
    protected String origin;
    protected VisualDescription visualDescription;
    protected GrowingTip growingTip;
    protected MultiplyingType multiplyingType;
    protected LocalDate date;

    public Flower() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoilType() {
        return soilType;
    }

    public void setSoilType(Soil soilType) {
        this.soilType = soilType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualDescription getVisualDescription() {
        return visualDescription;
    }

    public void setVisualDescription(VisualDescription visualDescription) {
        this.visualDescription = visualDescription;
    }

    public GrowingTip getGrowingTip() {
        return growingTip;
    }

    public void setGrowingTip(GrowingTip growingTip) {
        this.growingTip = growingTip;
    }

    public MultiplyingType getMultiplyingType() {
        return multiplyingType;
    }

    public void setMultiplyingType(MultiplyingType multiplyingType) {
        this.multiplyingType = multiplyingType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id.equals(flower.id) &&
                name.equals(flower.name) &&
                Objects.equals(soilType, flower.soilType) &&
                Objects.equals(origin, flower.origin) &&
                Objects.equals(visualDescription, flower.visualDescription) &&
                Objects.equals(growingTip, flower.growingTip) &&
                Objects.equals(multiplyingType, flower.multiplyingType) &&
                Objects.equals(date, flower.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, soilType, origin, visualDescription, growingTip, multiplyingType, date);
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", soilType=" + soilType +
                ", origin='" + origin + '\'' +
                ", visualDescription=" + visualDescription +
                ", growingTip=" + growingTip +
                ", multiplyingType=" + multiplyingType +
                ", date=" + date +
                '}';
    }
}
