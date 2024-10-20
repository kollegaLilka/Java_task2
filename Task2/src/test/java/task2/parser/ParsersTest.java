package task2.parser;

import task2.builder.FlowerBuilder;
import task2.builder.PoisonousFlowerBuilder;
import task2.entity.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ParsersTest {
    private Set<Flower> expectedSet;
    private String FILE_PATH_VALID = "valid.xml";

    @BeforeClass
    public void setUp() {
        expectedSet = new HashSet<>();

        Flower flower = new FlowerBuilder()
                .id("ID1")
                .name("Rose")
                .soilType(Soil.GROUND)
                .origin("England")
                .visualDescription(new VisualDescription(Colour.GREEN, Colour.VARIOUS, 70))
                .growingTip(new GrowingTip(true, 22, 700))
                .multiplyingType(MultiplyingType.STALK)
                .date(LocalDate.parse("2021-06-15"))
                .build();

        PoisonousFlower poisonousFlower = new PoisonousFlowerBuilder()
                .id("ID2")
                .dangerLevel(DangerLevel.MEDIUM)
                .name("Meconopsis")
                .soilType(Soil.GROUND)
                .origin("Chile")
                .visualDescription(new VisualDescription(Colour.GREEN, Colour.PURPLE, 50))
                .growingTip(new GrowingTip(true, 27, 500))
                .multiplyingType(MultiplyingType.STALK)
                .date(LocalDate.parse("2020-07-16"))
                .build();

        expectedSet.add(flower);
        expectedSet.add(poisonousFlower);
    }

    @Test
    public void DOMBuildSetFlowersPositiveTest() {
        FlowersDomBuilder builder = new FlowersDomBuilder();
        builder.buildSetFlowers(FILE_PATH_VALID);
        Set<Flower> actualSet = builder.getFlowers();
        Assert.assertEquals(actualSet, expectedSet);
    }

    @Test
    public void SAXBuildSetFlowersPositiveTest() {
        FlowersSaxBuilder builder = new FlowersSaxBuilder();
        builder.buildSetFlowers(FILE_PATH_VALID);
        Set<Flower> actualSet = builder.getFlowers();
        Assert.assertEquals(actualSet, expectedSet);
    }

    @Test
    public void StAXBuildSetFlowersPositiveTest() {
        FlowersStaxBuilder builder = new FlowersStaxBuilder();
        builder.buildSetFlowers(FILE_PATH_VALID);
        Set<Flower> actualSet = builder.getFlowers();
        Assert.assertEquals(actualSet, expectedSet);
    }
}
