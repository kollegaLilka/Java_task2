package task2.parser;

import task2.entity.*;
import task2.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FlowersStaxBuilder extends AbstractFlowersBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public FlowersStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        flowers = new HashSet<>();
    }

    public FlowersStaxBuilder(Set<Flower> flowers) {
        super(flowers);
    }

    @Override
    public void buildSetFlowers(String fileName) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerXmlTag.FLOWER.getValue())) {
                        Flower flower = new Flower();
                        buildFlower(reader, flower);
                        flowers.add(flower);
                    } else if (name.equals(FlowerXmlTag.POISONOUS_FLOWER.getValue())) {
                        PoisonousFlower flower = new PoisonousFlower();
                        buildPoisonousFlower(reader, flower);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException | CustomException | IOException e) {
            e.printStackTrace();
            LOGGER.error("Ex: ", e);
        }
    }

    private Flower buildFlower(XMLStreamReader reader, Flower flower)
            throws XMLStreamException, CustomException {
        flower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.getValue()));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case NAME:
                            flower.setName(getXMLText(reader));
                            break;
                        case ORIGIN:
                            flower.setOrigin(getXMLText(reader));
                            break;
                        case SOIL:
                            flower.setSoilType(Soil.getSoilByValue(getXMLText(reader)));
                            break;
                        case VISUAL_PARAMETERS:
                            flower.setVisualDescription(buildVisualDescription(reader));
                            break;
                        case MULTIPLYING:
                            flower.setMultiplyingType(MultiplyingType.getMultiplyingTypeByValue(getXMLText(reader)));
                            break;
                        case RECEIPT_DATE:
                            flower.setDate(LocalDate.parse(getXMLText(reader)));
                            break;
                        case GROWING_TIPS:
                            flower.setGrowingTip(buildGrowingTip(reader));
                            break;
                        default:
                            throw new CustomException("Exception");
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerXmlTag.valueOf(name.toUpperCase()) == FlowerXmlTag.FLOWER
                            || FlowerXmlTag.valueOf(name.toUpperCase()) == FlowerXmlTag.POISONOUS_FLOWER) {
                        return flower;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <student>");
    }

    private PoisonousFlower buildPoisonousFlower(XMLStreamReader reader, PoisonousFlower flower) throws XMLStreamException, CustomException {
        flower.setDangerLevel(DangerLevel.getDangerLevelByValue(reader.getAttributeValue(null, FlowerXmlTag.DANGER_LEVEL.getValue()))); // null check;
        return (PoisonousFlower) buildFlower(reader, flower);
    }

    private VisualDescription buildVisualDescription(XMLStreamReader reader) throws XMLStreamException, CustomException {
        VisualDescription visualDescription = new VisualDescription();
        FlowerXmlTag tag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case STEM_COLOUR:
                            visualDescription.setStemColour(Colour.getColourByValue(getXMLText(reader)));
                            break;
                        case PETALS_COLOUR:
                            visualDescription.setPetalsColour(Colour.getColourByValue(getXMLText(reader)));
                            break;
                        case AVERAGE_SIZE:
                            visualDescription.setAverageSize(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    if (tag == FlowerXmlTag.VISUAL_PARAMETERS) {
                        return visualDescription;
                    }
            }
        }
        throw new CustomException("vjkadsbvkjabv");
    }

    private GrowingTip buildGrowingTip(XMLStreamReader reader) throws XMLStreamException, CustomException {
        GrowingTip growingTip = new GrowingTip();
        FlowerXmlTag tag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    switch (tag) {
                        case WEEKLY_WATERING:
                            growingTip.setWeeklyWatering(Integer.parseInt(getXMLText(reader)));
                            break;
                        case GROWTH_TEMPERATURE:
                            growingTip.setGrowthTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        case IS_PHOTOPHILOUS:
                            growingTip.setPhotophilous(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    tag = FlowerXmlTag.valueOf(name.toUpperCase());
                    if (tag == FlowerXmlTag.GROWING_TIPS) {
                        return growingTip;
                    }
            }
        }
        throw new CustomException("vjkadsbvkjabv");
    }


    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

