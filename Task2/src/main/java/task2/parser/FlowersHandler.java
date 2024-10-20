package task2.parser;

import task2.entity.*;
import task2.exception.CustomException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static task2.parser.FlowerXmlTag.*;

public class FlowersHandler extends DefaultHandler {
    private Set<Flower> flowers;
    private Flower currentFlower;
    private VisualDescription currentVisualDescription;
    private GrowingTip currentGrowingTip;
    private FlowerXmlTag currentXmlTag;
    private EnumSet<FlowerXmlTag> withText;

    public FlowersHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.NAME, FlowerXmlTag.IS_PHOTOPHILOUS);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (FlowerXmlTag.valueOf(qName.toUpperCase())) {
            case FLOWER:
                currentFlower = new Flower();
                currentFlower.setId(attrs.getValue(0));
                break;
            case VISUAL_PARAMETERS:
                currentVisualDescription = new VisualDescription();
                currentFlower.setVisualDescription(currentVisualDescription);
                break;
            case GROWING_TIPS:
                currentGrowingTip = new GrowingTip();
                currentFlower.setGrowingTip(currentGrowingTip);
                break;
            case POISONOUS_FLOWER:
                currentFlower = new PoisonousFlower();
                currentFlower.setId(attrs.getValue(0));
                ((PoisonousFlower) currentFlower).setDangerLevel(DangerLevel.valueOf(attrs.getValue(1).toUpperCase()));
                break;
            default:
                FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase());
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (FLOWER.getValue().equals(qName) || POISONOUS_FLOWER.getValue().equals(qName)) {
            flowers.add(currentFlower);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null) {
            try {
                switch (currentXmlTag) {
                    case NAME:
                        currentFlower.setName(data);
                        break;
                    case ORIGIN:
                        currentFlower.setOrigin(data);
                        break;
                    case SOIL:
                        currentFlower.setSoilType(Soil.getSoilByValue(data));
                        break;
                    case PETALS_COLOUR:
                        currentVisualDescription.setPetalsColour(Colour.getColourByValue(data));
                        break;
                    case STEM_COLOUR:
                        currentVisualDescription.setStemColour(Colour.getColourByValue(data));
                        break;
                    case AVERAGE_SIZE:
                        currentVisualDescription.setAverageSize(Integer.parseInt(data));
                        break;
                    case MULTIPLYING:
                        currentFlower.setMultiplyingType(MultiplyingType.getMultiplyingTypeByValue(data));
                        break;
                    case RECEIPT_DATE:
                        currentFlower.setDate(LocalDate.parse(data));
                        break;
                    case WEEKLY_WATERING:
                        currentGrowingTip.setWeeklyWatering(Integer.parseInt(data));
                        break;
                    case GROWTH_TEMPERATURE:
                        currentGrowingTip.setGrowthTemperature(Integer.parseInt(data));
                        break;
                    case IS_PHOTOPHILOUS:
                        currentGrowingTip.setPhotophilous(Boolean.parseBoolean(data));
                        break;
                    default:
                        throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
        currentXmlTag = null;
    }
}
