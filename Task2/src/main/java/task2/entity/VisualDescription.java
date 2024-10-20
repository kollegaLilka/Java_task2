package task2.entity;

import task2.exception.CustomException;

import java.util.Objects;

public class VisualDescription {
    private Colour stemColour;
    private Colour petalsColour;
    private int averageSize;

    public VisualDescription() {
    }

    public VisualDescription(Colour stemColour, Colour petalsColour, int averageSize) {
        this.stemColour = stemColour;
        this.petalsColour = petalsColour;
        this.averageSize = averageSize;
    }

    public Colour getStemColour() {
        return stemColour;
    }

    public void setStemColour(Colour stemColour) {
        this.stemColour = stemColour;
    }

    public Colour getPetalsColour() {
        return petalsColour;
    }

    public void setPetalsColour(Colour petalsColour) {
        this.petalsColour = petalsColour;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) throws CustomException {
        if (averageSize < 0) {
            throw new CustomException("Size can't be negative!");
        }
        this.averageSize = averageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualDescription that = (VisualDescription) o;
        return averageSize == that.averageSize &&
                Objects.equals(stemColour, that.stemColour) &&
                Objects.equals(petalsColour, that.petalsColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stemColour, petalsColour, averageSize);
    }

    @Override
    public String toString() {
        return "VisualDescription{" +
                "stemColour=" + stemColour +
                ", leavesColour=" + petalsColour +
                ", averageSize=" + averageSize +
                '}';
    }
}